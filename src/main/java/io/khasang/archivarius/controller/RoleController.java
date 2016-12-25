package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Role;
import io.khasang.archivarius.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    private static final Logger log = Logger.getLogger(RoleController.class);

    @RequestMapping("/")
    public String roleList(Model model) {
        model.addAttribute("roles", roleService.getRoleList());
        return "lists/roles";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String roleGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("role", roleService.getRoleById(intId));
        return "lists/role";
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public ModelAndView roleForm(@PathVariable("id") String id) {
        Integer intId = Integer.valueOf(id);
        Role role = roleService.getRoleById(intId);
        return new ModelAndView("forms/role", "role", role);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("forms/role", "role", new Role());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("role")Role role,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        roleService.updateRole(role);
        return "forms/success";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = { "delete" })
    public String deny(@RequestParam int id, @RequestParam String delete, Model model) {
        roleService.deleteRoleById(id);
        return "redirect:/role/";
    }
}
