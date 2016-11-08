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

    public String tableInsert() {
        try {
            jdbcTemplate.execute("INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) VALUES\n" +
                    "(1,'Kovin Artur',25,'Moscow',90000),\n" +
                    "(2,'Shamkin Eugeny',26,'Ekaterinburg',65000),\n" +
                    "(3,'Kanunnikov Viktor',27,'Moscow',59590),\n" +
                    "(4,'Vorobyeva Tatyana',18,'Moscow',51000),\n" +
                    "(5,'Stankevich Peter',34,'Moscow',57000)");
            return "rows inserted";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    public String tableDelete(){
        try {
            jdbcTemplate.execute(("DELETE FROM COMPANY WHERE id = 1"));
            return "row deleted";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }
}
