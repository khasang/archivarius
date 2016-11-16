package io.khasang.archivarius.controller;

import io.khasang.archivarius.model.DatabaseBackup;
import io.khasang.archivarius.model.Message;
import io.khasang.archivarius.model.QueryExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    private static final Logger log = Logger.getLogger(AppController.class);
    @Autowired
    Message message;
    @Autowired
    QueryExample queryExample;
    @Autowired
    DatabaseBackup databaseBackup;

    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", message.getHelloMessage());
        log.debug("We receive following message: " + message.getHelloMessage());
        return "hello";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("create", queryExample.tableCreation());
        return "create";
    }

    @RequestMapping("/task_table_create")
    public String taskTableCreate(Model model){
        model.addAttribute("task_table_create", queryExample.tableTaskCreation());
        return "task_table_create";
    }

    @RequestMapping("/update")
    public String update(Model model) {
        model.addAttribute("update", queryExample.tableUpdate());
        return "update";
    }


    @RequestMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("insert", queryExample.tableInsert(42, "Vasya Pupkin", 33, "Moscow, Kremlin", 100500));
        return "insert";
    }

    @RequestMapping("/insert_task_table")
    public String insertTaskTable(Model model) {
        model.addAttribute("insert", queryExample.tableTaskInsert());
        return "insert_task_table";
    }
    @RequestMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("delete", queryExample.tableDelete());
        return "delete";
    }
    @RequestMapping("/select")
    public String select(Model model) {
        model.addAttribute("list", queryExample.tableSelect());
        return "select";
    }

    @RequestMapping("/select_all")
    public String selectAll(Model model) {
        model.addAttribute("list", queryExample.tableAllSelect());
        return "select";
    }

    @RequestMapping("/select_inner")
    public String selectInner(Model model) {
        model.addAttribute("list", queryExample.tableInnerSelect());
        return "select";
    }
    
    @RequestMapping("/admin/page")
    public String secure(Model model) {
        model.addAttribute("secure", "This is very secure page");
        return "secure";
    }

    @RequestMapping("/user/api")
    public String userApi(Model model) {
        model.addAttribute("api", "ты не сможешь сюда попасть");
        return "api";
    }

    @RequestMapping(value = {"hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }

    @RequestMapping("/backup")
    public String backup(Model model) {
        model.addAttribute("backup", databaseBackup.backup());
        return "backup";
    }
}
