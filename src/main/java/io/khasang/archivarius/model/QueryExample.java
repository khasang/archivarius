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
            jdbcTemplate.execute(
                    "drop table if exists company;\n" +
                    "drop table if exists workers;\n" +
                    "CREATE TABLE WORKERS(\n" +
                    " ID INT PRIMARY KEY     NOT NULL,\n" +
                    " NAME           TEXT    NOT NULL,\n" +
                    "AGE            INT     NOT NULL,\n" +
                    "ADDRESS        CHAR(50),\n" +
                    "SALARY         REAL\n" +
                    ");\n" +
                    "CREATE TABLE company(\n" +
                    "id INT PRIMARY KEY NOT NULL,\n" +
                    "name TEXT NOT NULL,\n" +
                    "user_id INT REFERENCES workers\n" +
                    ");\n");
            return "tables created";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }


}
