package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Books;

import java.util.List;

public interface BooksDAO {
    void addBooks(Books books);

    void updateBooks(Books books);

    void deleteBooks(Books books);

    Books getBooksById(int id);

    Books getBooksByTitle(String title);

    List<Books> getBooksList();
}
