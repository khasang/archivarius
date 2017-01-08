package io.khasang.archivarius.controller;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    private static final Logger log = Logger.getLogger(AppController.class);

    @GetMapping(value = {"/", "help"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = {"hello/{name}"})
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "forms/login";
    }
}
