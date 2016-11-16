package io.khasang.archivarius.model;

import io.khasang.archivarius.controller.EmployerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                    "country_name TEXT NOT NULL,\n" +
                    "user_id INT REFERENCES workers\n" +
                    ");");
            return "tables created and fill";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    public String tableFill() {
        try {
            jdbcTemplate.execute("INSERT INTO workers (id, name, age, address, salary) VALUES \n" +
                    "\t\t('1', 'Victor', 21, 'Moscow', '5000'),\n" +
                    "\t\t('2', 'Petr', 22, 'London', '4000'),\n" +
                    "\t\t('3', 'Misha', 23, 'Paris', '633'),\n" +
                    "\t\t('4', 'Vasya', 34, 'Khabarovsk', '212'),\n" +
                    "\t\t('5', 'John', 32, 'Vladimir', '2312');\n" +
                    "INSERT INTO company (id, country_name, user_id) VALUES \n" +
                    "\t\t('1', 'Apple', '1'),\n" +
                    "\t\t('2', 'Microsoft', '5'),\n" +
                    "\t\t('3', 'Sony', '2'),\n" +
                    "\t\t('4', 'Philips', '4'),\n" +
                    "\t\t('5', 'Lenovo', '3');");
            return "tables fill successfully";
        } catch (Exception e) {
            return "Error " + e;
        }
    }


    public List<JoinResponse> doJoin() {
        List<JoinResponse> joinResponses = new ArrayList<>();
        String query = "SELECT w.name, c.country_name FROM workers w INNER JOIN company c ON w.id = c.user_id;";
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
            for (Map<String, Object> row : rows) {
                String workerName = row.get("name").toString();
                String companyName = row.get("country_name").toString();
                System.out.println(workerName + " " + companyName);
                joinResponses.add(new JoinResponse(workerName, companyName));
            }
            return joinResponses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
