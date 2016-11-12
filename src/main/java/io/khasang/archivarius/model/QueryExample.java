package io.khasang.archivarius.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class QueryExample {
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
            return "Error: "  + e;
        }
    }

    public String tableUpdate(){
        try {
            jdbcTemplate.execute(("UPDATE COMPANY SET SALARY = 90000 WHERE id = 1"));
            return "table updated";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }
}
