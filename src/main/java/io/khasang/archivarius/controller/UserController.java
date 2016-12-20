package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.Role;
import io.khasang.archivarius.entity.User;
import io.khasang.archivarius.service.RoleService;
import io.khasang.archivarius.service.UserService;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger log = Logger.getLogger(UserController.class);

    @RequestMapping("/users/")
    public String userList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "userList";
    }

    @RequestMapping(value = {"/user/{id}"}, method = RequestMethod.GET)
    public String userGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("user", userService.getUserById(intId));
        return "usergetId";
    }

    @RequestMapping(value = {"user/{id}/edit"}, method = RequestMethod.GET)
    public String userForm(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        User user = userService.getUserById(intId);
        user.setId(user.getId());
        model.addAttribute("roles", getDropboxList());
        model.addAttribute("user", user);
        return "userForm";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        model.addAttribute("roles", getDropboxList());
        model.addAttribute("user", new User());
        return "userForm";
    }

    @RequestMapping(value = "/users/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("user") User user,
                         BindingResult result, ModelMap model) {
        Set<Role> roles = new HashSet<>();
        String values = (String) result.getFieldValue("roles");
        String rolesArr[] = values.split(",");
        for (int i = 0; i < rolesArr.length; i++) {
            Role role = roleService.getRoleById(Integer.valueOf(rolesArr[i]));
            roles.add(role);
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode((String)result.getFieldValue("password")));
        userService.updateUser(user);
        return "resultUserFormEdit";
    }

    @RequestMapping(value = "/users/", method = RequestMethod.POST, params = {"delete"})
    public String deny(@RequestParam int id, @RequestParam String delete, Model model) {
        userService.deleteUserById(id);
        return "redirect:/users/";
    }

    public Map<Integer, String> getDropboxList() {
        List<Role> rolesList = roleService.getRoleList();
        Map<Integer, String> roles = new HashMap<>();
        for (Role role : rolesList) {
            roles.put(role.getId(), role.getName());
        }
        return roles;
    }
}
