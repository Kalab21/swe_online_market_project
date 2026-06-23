package com.markethub.app.controller;

import com.markethub.app.service.UserService;
import com.markethub.app.service.imp.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryServicesController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/secured/services","/onlinemarket/secured/services"})
    public String services(Model model) {
        model.addAttribute("currentUser",userDetailsService.getCurrentUser());
        return "secured/services";
    }

    @GetMapping(value = {"/secured/services/admin/usrmgmt/list","/onlinemarket/secured/services/admin/usrmgmt/list"})
    public String usrmgmtList(Model model) {
        model.addAttribute("sellers", userService.getAllUsers());
        model.addAttribute("currentUser", userDetailsService.getCurrentUser());
        return "secured/services/admin/usrmgmt/list";
    }
}
