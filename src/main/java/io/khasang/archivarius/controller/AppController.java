package io.khasang.archivarius.controller;

import io.khasang.archivarius.model.JoinResponse;
import io.khasang.archivarius.model.Message;
import io.khasang.archivarius.model.QueryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        model.addAttribute("create", queryExample.tableFill());
        return "create";
    }

    @RequestMapping("/join")
    public String renderTable(ModelMap model) {
        List<JoinResponse> joinResponses = queryExample.doJoin();
        model.addAttribute("joinResponses", joinResponses);
        return "join";
    }
}
