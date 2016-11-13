package io.khasang.archivarius.model;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryExample {

    private static final Logger log = Logger.getLogger(QueryExample.class);

    private JdbcTemplate jdbcTemplate;

    public QueryExample(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public QueryExample() {
    }

    public String tableCreation() {
        try {
            jdbcTemplate.execute("CREATE TABLE COMPANY(\n" +
                    "   ID INT PRIMARY KEY     NOT NULL,\n" +
                    "   NAME           TEXT    NOT NULL,\n" +
                    "   AGE            INT     NOT NULL,\n" +
                    "   ADDRESS        CHAR(50),\n" +
                    "   SALARY         REAL\n" +
                    ");");
            log.debug("TableCreation company");
            return "table created";
        } catch (Exception e) {
            log.error("TableCreation company Error " + e);
            return "Error: "  + e;
        }
    }

    public String tableTaskCreation() {
        try {
            jdbcTemplate.execute("CREATE TABLE TASK(\n" +
                    "   ID INT PRIMARY KEY NOT NULL,\n" +
                    "   COMPANY_ID INT NOT NULL,\n" +
                    "   TASK_NAME TEXT NOT NULL\n" +
                    ");");
            log.debug("TableCreation task");
            return "table created";
        } catch (Exception e) {
            log.error("TableCreation task Error " + e);
            return "Error: "  + e;
        }
    }

    public String tableUpdate(){
        try {
            jdbcTemplate.execute(("UPDATE COMPANY SET SALARY = 90000 WHERE id = 1"));
            log.debug("Update company set salary");
            return "table updated";
        } catch (Exception e) {
            log.error("Error Update company " + e);
            return "Error: " + e;
        }
    }

    public String tableInsert() {
        try {
            jdbcTemplate.execute("INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) VALUES\n" +
                    "(1,'Kovin Artur',25,'Moscow',90000),\n" +
                    "(2,'Shamkin Eugeny',26,'Ekaterinburg',65000),\n" +
                    "(3,'Kanunnikov Viktor',27,'Moscow',59590),\n" +
                    "(4,'Vorobyeva Tatyana',18,'Moscow',51000),\n" +
                    "(5,'Stankevich Peter',34,'Moscow',57000)");
            log.debug("Insert into company 5 values");
            return "rows inserted";
        } catch (Exception e) {
            log.error("Error in insert into table compaye " + e);
            return "Error: " + e;
        }
    }

    public String tableTaskInsert() {
        try {
            jdbcTemplate.execute("INSERT INTO TASK (ID, COMPANY_ID, TASK_NAME) VALUES\n" +
                    "(1, 1, 'Insert в таблицу'),\n" +
                    "(2, 2, 'Backup - сделать backup базы из Java. '),\n" +
                    "(3, 3, 'Backup - сделать backup базы из Java. '),\n" +
                    "(4, 4, 'вложенный select'),\n" +
                    "(5, 5, 'insert в таблицу(можно через pgadmin), Delete (Spring JDBC)')");
            log.debug("Insert into table task 5 values");
            return "rows inserted";
        } catch (Exception e) {
            log.error("Error in insert of table task" + e);
            return "Error: " + e;
        }
    }

    public String tableDelete(){
        try {
            jdbcTemplate.execute(("DELETE FROM COMPANY WHERE id = 1"));
            log.debug("delete from table company");
            return "row deleted";
        } catch (Exception e) {
            log.error("Error in delete table company " + e);
            return "Error: " + e;
        }
    }

    public List<Employee> tableSelect(){
        try {
            String sql = "SELECT * FROM COMPANY";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            List<Employee> list = new ArrayList<>();
            for(Map row : rows) {
                final Employee employee = new Employee((int)row.get("id"),
                        (int)row.get("age"),
                        (String)row.get("name"),
                        (String)row.get("address"),
                        (Float)row.get("salary")
                );
                list.add(employee);
            }
            log.debug("Select all rows and columns in table company ");
            return list;
        } catch (Exception e) {
            log.error("Error in select rows and columns in table company " + e);
            return null;
        }
    }

    public List<String> tableAllSelect(){
        try {
            String sql = "SELECT c.name, t.task_name FROM COMPANY c JOIN TASK t ON t.company_id = c.id";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            List<String> list = new ArrayList<>();
            for(Map row : rows) {
                final String str = row.get("name") + " " + row.get("task_name");
                list.add(str);
            }
            log.debug("Concatenate tasks and company employees");
            return list;
        } catch (Exception e) {
            log.error("Error in join of company table and task table " + e);
            return null;
        }
    }

    public List<String> tableInnerSelect(){
        try {
            String sql = "SELECT name FROM COMPANY WHERE id in (" +
                    "SELECT company_id FROM task where id = 3)";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            List<String> list = new ArrayList<>();
            for(Map row : rows) {
                final String str = (String)row.get("name");
                list.add(str);
            }
            log.debug("Inner select in company table to task table");
            return list;
        } catch (Exception e) {
            log.error("Error in inner select company table to task table " + e);
            return null;
        }
    }

	private static final Logger log = Logger.getLogger(QueryExample.class);
	private JdbcTemplate jdbcTemplate;
	
	public QueryExample(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public QueryExample() {
	}
	
	public String tableCreation() {
		try {
			jdbcTemplate.execute("CREATE TABLE COMPANY(\n" +
					"   ID INT PRIMARY KEY     NOT NULL,\n" +
					"   NAME           TEXT    NOT NULL,\n" +
					"   AGE            INT     NOT NULL,\n" +
					"   ADDRESS        CHAR(50),\n" +
					"   SALARY         REAL\n" +
					");");
			return "table created";
		} catch (Exception e) {
			return "Error: " + e;
		}
	}
	
	/**
	 * Example method which can insert new employee into company database.
	 *
	 * @param id      Employee id.
	 * @param name    Name of employee.
	 * @param age     Age of employee.
	 * @param address Address of employee.
	 * @param salary  Monthly salary of employee.
	 */
	public String tableInsert(int id, String name, int age, String address, int salary) {
		try {
			jdbcTemplate.update(
					"INSERT INTO COMPANY(ID, NAME, AGE, ADDRESS, SALARY) VALUES (?, ?, ?, ?, ?);",
					id, name, age, address, salary
			);
			log.debug("Inserted record: ID=" + id + ", NAME=" + name + ", AGE=" + age + ", ADDRESS=" + address + ", SALARY=" + salary);
			return "Insert successful.";
		} catch (Exception e) {
			log.error("Insert failed. Error: " + e);
			return "Insert failed.";
		}
	}
	
	public String tableUpdate() {
		try {
			jdbcTemplate.execute(("UPDATE COMPANY SET SALARY = 90000 WHERE id = 1"));
			return "table updated";
		} catch (Exception e) {
			return "Error: " + e;
		}
	}

}
