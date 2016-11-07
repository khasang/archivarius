package io.khasang.archivarius.controller;

import io.khasang.archivarius.model.Message;
import io.khasang.archivarius.model.QueryExample;
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

    @RequestMapping("/update")
    public String update(Model model) {
        model.addAttribute("update", queryExample.tableUpdate());
        return "update";
    }

    @RequestMapping("/admin/page")
    public String secure(Model model){
        model.addAttribute("secure", "This is very secure page");
        return "secure";
    }

    @RequestMapping("/user/api")
    public String userApi(Model model){
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
}
