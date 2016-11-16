package io.khasang.archivarius.controller;

import io.khasang.archivarius.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    Message message;

    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", message.getHelloMessage());
        return "hello";
    }
<<<<<<< Updated upstream
=======

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("create", queryExample.tableCreation());
        return "create";
    }

    @RequestMapping("/update")
    public String update(Model model) {
        model.addAttribute("update", queryExample.tableUpdate());
        return "update";
    }

    @RequestMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("delete", queryExample.tableDelete());
        return "delete";
    }
    
    @RequestMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("insert", queryExample.tableInsert(42, "Vasya Pupkin", 33, "Moscow, Kremlin", 100500));
        return "insert";
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
>>>>>>> Stashed changes
}
