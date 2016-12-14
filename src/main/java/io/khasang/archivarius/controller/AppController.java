package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.model.DatabaseBackup;
import io.khasang.archivarius.model.Message;
import io.khasang.archivarius.model.QueryExample;
import io.khasang.archivarius.service.CompanyService;
import io.khasang.archivarius.service.ReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    ReportService reportService;
    @Autowired
    CompanyService companyService;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("companies", companyService.getCompanyList());
        return "index";
    }

    @GetMapping(value = "/comp/add")
    public ModelAndView showCompanyForm() {
        return new ModelAndView("comForm", "company", new Company());
    }

    @RequestMapping(value = "/inbox", method = RequestMethod.GET)
    public String inbox(Model model) {
        return "inbox";
    }

    @RequestMapping(value = "/outbox", method = RequestMethod.GET)
    public String outbox(Model model) {
        return "outbox";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model) {
        return "orders";
    }
    @RequestMapping(value = "/control", method = RequestMethod.GET)
    public String control(Model model) {
        return "control";
    }
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help(Model model) {
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
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

    @RequestMapping("/update")
    public String update(Model model) {
        model.addAttribute("update", queryExample.tableUpdate());
        return "update";
    }

    @RequestMapping("/rest")
    public String rest() {
        return "rest";
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

    /**
    *  Get information about current active session
    * */
    @RequestMapping(value = "currentUser", method = RequestMethod.GET)
    public String getCurrentUser(Model model){
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "curuser";
        }

     /** Get list of 'bad' users, who spent more time in site 'vk.com'
     * @param model
     * @return
     */
    @RequestMapping("/report/vkontakte")
    public String vkontakteList(Model model) {
        model.addAttribute("vklist", reportService.getReportVkontakteList());
        return "vklist";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
