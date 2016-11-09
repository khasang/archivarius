package io.khasang.archivarius.model;

import org.springframework.jdbc.core.JdbcTemplate;

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

    public String tableTaskCreation() {
        try {
            jdbcTemplate.execute("CREATE TABLE TASK(\n" +
                    "   ID INT PRIMARY KEY NOT NULL,\n" +
                    "   COMPANY_ID INT NOT NULL,\n" +
                    "   TASK_NAME TEXT NOT NULL\n" +
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

    public String tableTaskInsert() {
        try {
            jdbcTemplate.execute("INSERT INTO TASK (ID, COMPANY_ID, TASK_NAME) VALUES\n" +
                    "(1, 1, 'Insert в таблицу'),\n" +
                    "(2, 2, 'Backup - сделать backup базы из Java. '),\n" +
                    "(3, 3, 'Backup - сделать backup базы из Java. '),\n" +
                    "(4, 4, 'вложенный select'),\n" +
                    "(5, 5, 'insert в таблицу(можно через pgadmin), Delete (Spring JDBC)')");
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
            return list;
        } catch (Exception e) {
            e.printStackTrace();
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
            return list;
        } catch (Exception e) {
            e.printStackTrace();
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
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String tableGetSchema() {
        try {
            String sql = "SELECT column_name, data_type, character_maximum_length " +
                    "FROM INFORMATION_SCHEMA.COLUMNS where table_name = 'company'";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            String str = "";
            for(Map row : rows) {
                str += row.get("column_name") + " " + row.get("data_type") + " " +
                        row.get("character_maximum_length");
            }
            return str;
        } catch (Exception e) {
            return "Error: " + e;
        }
    }
}
