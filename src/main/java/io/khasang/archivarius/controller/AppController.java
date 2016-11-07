package io.khasang.archivarius.controller;

import io.khasang.archivarius.model.Message;
import io.khasang.archivarius.model.QueryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    Message message;
    @Autowired
    QueryExample queryExample;

    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", message.getHelloMessage());
        return "hello";
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("create", queryExample.tableCreation());
        return "create";
    }

    @RequestMapping("/admin/page")
    public String secure(Model model){
        model.addAttribute("secure", "This is very secure page");
        return "secure";
    }
}
