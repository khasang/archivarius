package io.khasang.archivarius.model;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

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
            return "table created";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    public String tableDelete() {
        try {
            jdbcTemplate.update("DELETE FROM COMPANY WHERE id=?", 42);
            return "table deleted";
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
