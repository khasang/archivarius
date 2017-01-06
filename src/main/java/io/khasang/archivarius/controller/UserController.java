package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Role;
import io.khasang.archivarius.entity.User;
import io.khasang.archivarius.service.RoleService;
import io.khasang.archivarius.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

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
        model.addAttribute("users", userService.getUserList());
        return "lists/users";
    }

    @RequestMapping(value = {"/user/{id}"}, method = RequestMethod.GET)
    public String userGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("user", userService.getUserById(intId));
        return "lists/user";
    }

    @RequestMapping(value = {"user/{id}/edit"}, method = RequestMethod.GET)
    public String userForm(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        User user = userService.getUserById(intId);
        user.setId(user.getId());
        model.addAttribute("roles", roleService.getRoleList());
        model.addAttribute("user", user);
        return "forms/user";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        model.addAttribute("roles", roleService.getRoleList());
        model.addAttribute("user", new User());
        return "forms/user";
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
        return "forms/success";
    }

    @PostMapping(value = "/users/delete")
    public String delete(@RequestParam int id) {
        userService.deleteUserById(id);
        return "redirect:/users/";
    }
}
