package com.markethub.app.controller;

import com.markethub.app.service.imp.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryServicesController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = {"/secured/services","/onlinemarket/secured/services"})
    public String services(Model model) {
        model.addAttribute("currentUser",userDetailsService.getCurrentUser());
        return "secured/services";
    }

    @GetMapping(value = {"/secured/services/admin/usrmgmt/list","/elibrary/secured/services/admin/usrmgmt/list"})
    public String usrmgmtList() {
        return "secured/services/admin/usrmgmt/list";
    }
}
