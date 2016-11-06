package io.khasang.archivarius.model;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by yuri on 06.11.16.
 */
public class QueryExample {
    private JdbcTemplate jdbcTemplate;

    public QueryExample(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public QueryExample() {
    }

    public String tableCreation() {
        try {
            jdbcTemplate.execute("CREATE TABLE COMPANY(" +
                    "ID INT PRIMARY KEY NOT NULL, " +
                    "NAME TEXT NOT NULL, " +
                    "AGE INT NOT NULL, " +
                    "ADDRESS CHAR(50), " +
                    "SALARY REAL" +
                    ")");
            return "table created";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    /**
     * Update salary to one employee
     * @return String for wright update or Error
     */
    public String updateEmplyees() {
        try {
            jdbcTemplate.execute("UPDATE COMPANY SET SALARY = 65000 WHERE ID = 2");
            return "query updated";
        } catch (Exception e) {
            return "Error " + e;
        }
    }
}
