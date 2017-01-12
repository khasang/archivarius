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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    private static final Logger log = Logger.getLogger(RoleController.class);

    @RequestMapping("/")
    public String roleList(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("roles", roleService.getRoleList());
        model.addAttribute("message", message);
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
                         BindingResult result, ModelMap model,
                         RedirectAttributes redirectAttributes) {
        roleService.updateRole(role);
        redirectAttributes.addFlashAttribute("message", "Роль " + role.getName() + " создана");
        return "redirect:/role/";
    }

    @PostMapping(value="/", params = {"delete"})
    public String delete(final Role role, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Роль " + role.getName() + " удалена");
        roleService.deleteRole(role);
        return "redirect:/role/";
    }
}
