package ru.kds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kds.domain.Role;
import ru.kds.service.RoleService;

/**
 *
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roles")
    public String index(ModelMap model) {

        Iterable<Role> roles = roleService.findAll();

        model.addAttribute("roles", roles);

        return "roles/index";
    }
}
