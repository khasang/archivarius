Тестирование в Java с помощью Mockito  <i class="icon-hdd"></i>
============================================

А Вы знаете что такое mock-объект? Нет?

Что же говорит по этому поводу википедия: «В объектно-ориентированном программировании mock-объект имитирует поведение реального объекта заданным образом… ». Казалось бы зачем? Википедия продолжает: «Во время unit-тестирования mock-объекты могут симулировать поведение бизнес-объектов и бизнес-логику, что иногда необходимо из-за сложности реального поведения»

**Mockito** - очень интересная и крайне полезная библиотека для юнит тестирования кода программы в отделении от всей системы. С помощью этой библиотеки можно проводить интеграционные тесты без нужно для этого окружения.  

Например, легко представить себе систему построенную по классической архитектуре:  тонкий клиент, серверная часть (основная бизнес логика) плюс слой персистентности т.е. непосредственно база данных в которой хранятся все данные с которыми работает код программы. 


Для того что бы выполнялись "обычные" юнит тесты для некоторого метода  обновления данных в БД нам придется держать базу данных включенной. К тому же, нам нужно либо иметь правильно настроенную базу - соответствующую тестовому системному окружению, или в лучшем случае встроенную базу которая бы стартовала на заранее определенном порту, например в методе JUnit с аннотацией @Before и глушилась бы в методе @After. Так же необходимо соблюдать определенные условия по целостности данных.  Либо блюсти соглашения, либо для каждого теста выполнять определенную откатываемую транзакцию. Это обусловлено тем, что может быть так, что при одинаковых вводных возвращаемые результаты методов будут различаться.



----------
Для начала, скачайте mockito с http://mockito.org/ 
Или просто добавьте это в зависимости в pom maven-проекта:
```xml
<dependency>
  <groupId>org.mockito</groupId>
  <artifactId>mockito-core</artifactId>
  <version>RELEASE</version>
  <scope>test</scope>
</dependency>
```
Почти всё самое интересное может быть взято из класса org.mockito.Mockito (или можно статически импортировать его методы, что я и использую в этой статье). Итак, начнём.

Чтобы создать заглушку (или мок), используйте mock(class). Затем используйте when(mock).thenReturn(value), чтобы указать возвращаемое значение для метода. Если Вы укажете более одного возвращаемого значения, они будут возвращены методом последовательно, пока не вернётся последнее, после этого при последующих вызовах будет возвращаться только последнее значение (таким образом, чтобы метод всегда возвращал одно и то же значение, просто укажите его единожды). Например:
```java
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Test;

	@Test
	public void iterator_will_return_hello_world() {
		//подготавливаем
		Iterator i = mock(Iterator.class);
		when(i.next()).thenReturn("Hello").thenReturn("World");
		//выполняем
		String result = i.next()+" "+i.next();
		//сравниваем
		assertEquals("Hello World", result);
	}
```
Этот пример демонстрирует создание мок-итератора и «заставляет» его возвращать «Hello» при первом вызове метода **next()**. Последующие вызовы этого метода будут возвращать «World». После этого мы можем выполнять обычные assert'ы.
Заглушки также могут возвращать различные значения в зависимости от передаваемых в метод аргументов. Пример:
```java
@Test
	public void with_arguments() {
		Comparable c = mock(Comparable.class);
		when(c.compareTo("Test")).thenReturn(1);
		assertEquals(1, c.compareTo("Test"));
	}
```
Здесь мы создаём объект-заглушку Comparable, и возвращаем 1 в случае, если он сравнивается с определённым String-значением («Test», в данном случае).
Если метод имеет какие-то аргументы, но Вам всё равно, что будет в них передано или предсказать это невозможно, то используйте **anyInt()** (и альтернативные значения для других типов). Пример:
```java
@Test
	public void with_unspecified_arguments() {
		Comparable c = mock(Comparable.class);
		when(c.compareTo(anyInt())).thenReturn(-1);
		assertEquals(-1, c.compareTo(5));
	}
```
Эта заглушка возвращает -1 вне зависимости от переданного аргумента. Void-методы составляют некоторую проблему, так как Вы не можете использовать их в методе **when()**.
Альтернативным синтаксисом в этой ситуации будет **doReturn(result).when(mock_object).void_method_call()**. Вместо возврата результата Вы также можете использовать **thenThrow()** или **doThrow()** для void-методов. Пример:
```java
@Test(expected=IOException.class)
	public void OutputStreamWriter_rethrows_an_exception_from_OutputStream() 
			throws IOException {
		OutputStream mock = mock(OutputStream.class);
		OutputStreamWriter osw = new OutputStreamWriter(mock);
		doThrow(new IOException()).when(mock).close();
		osw.close();
	}
```
В этом примере выбрасывается IOException, когда в заглушке OutputStream вызывается метод close. Мы с лёгкостью проверяем, что OutputStreamWriter пробрасывает такой эксепшн наружу.
Чтобы проверить, что метод действительно был вызван (типичное использование объектов-заглушек), мы можем использовать **verify(mock_object).method_call**. Пример:
```java
@Test
	public void OutputStreamWriter_Closes_OutputStream_on_Close()
			throws IOException {
		OutputStream mock = mock(OutputStream.class);
		OutputStreamWriter osw = new OutputStreamWriter(mock);
		osw.close();
		verify(mock).close();
	}
```
В этом примере мы проверяем, что OutputStreamWriter совершает вызов метода **close()** во вложенном OutputStream.
Вы можете использовать аргументы в методах и подстановки для них, такие как **anyInt()**, как в одном из предыдущих примеров. Стоит отметить, что Вы не можете смешивать литералы и матчеры. Используйте матчер **eq(value)** для конвертирования литерала в матчер, который сравнит значение. Mockito предоставляет уйму уже готовых матчеров, но иногда Вам может потребоваться более гибкий подход. К примеру, OutputStreamWriter будет буферизовать вывод и затем передавать его обёрнутому объекту при заполнении буфера, но мы не знаем, насколько длинный буфер нам собираются передать. Здесь мы не можем использовать сравнение на равенство. Однако, мы можем запилить собственный матчер:

```java
@Test
	public void OutputStreamWriter_Buffers_And_Forwards_To_OutputStream() 
			throws IOException {		
		OutputStream mock = mock(OutputStream.class);
		OutputStreamWriter osw = new OutputStreamWriter(mock);
		osw.write('a');
		osw.flush();
		// не можем делать так, потому что мы не знаем,
		// насколько длинным может быть массив
		// verify(mock).write(new byte[]{'a'}, 0, 1);

		BaseMatcher<byte[]> arrayStartingWithA = new BaseMatcher<byte[]>() {
			@Override
			public void describeTo(Description description) {
				// пустота
			}
			// Проверяем, что первый символ - это A
			@Override
			public boolean matches(Object item) {
				byte[] actual = (byte[]) item;
				return actual[0] == 'a';
			}
		};
		// проверяем, что первый символ массива - это A, и что другие два аргумента равны 0 и 1.
		verify(mock).write(argThat(arrayStartingWithA), eq(0), eq(1));	
	}
```
И это всё, что Вам потребуется, чтобы начать! 
Весь код класса с тестами
```java
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;

public class MockitoTests {
    @Test
    public void iterator_will_return_hello_world() {
        //подготавливаем
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //выполняем
        String result = i.next() + " " + i.next();
        //сравниваем
        assertEquals("Hello World", result);
    }

    @Test
    public void with_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo("Test")).thenReturn(1);
        assertEquals(1, c.compareTo("Test"));
    }

    @Test
    public void with_unspecified_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        assertEquals(-1, c.compareTo(5));
    }

    @Test(expected = IOException.class)
    public void OutputStreamWriter_rethrows_an_exception_from_OutputStream()
            throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        doThrow(new IOException()).when(mock).close();
        osw.close();
    }

    @Test
    public void OutputStreamWriter_Closes_OutputStream_on_Close()
            throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        osw.close();
        verify(mock).close();
    }

    @Test
    public void OutputStreamWriter_Buffers_And_Forwards_To_OutputStream()
            throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        osw.write('a');
        osw.flush();
        // не можем делать так, потому что мы не знаем,
        // насколько длинным может быть массив
        // verify(mock).write(new byte[]{'a'},0,1);

        BaseMatcher<byte[]> arrayStartingWithA = new BaseMatcher<byte[]>() {
            @Override
            public void describeTo(Description description) {
                // пустота
            }

            // Проверяем, что первый символ - это A
            @Override
            public boolean matches(Object item) {
                byte[] actual = (byte[]) item;
                return actual[0] == 'a';
            }
        };
        // проверяем, что первый символ массива - это A, и что другие два аргумента равны 0 и 1.
        verify(mock).write(argThat(arrayStartingWithA), eq(0), eq(1));
    }
}
```

Спасибо habrahabr.ru и @denixx за перевод статьи.


	

