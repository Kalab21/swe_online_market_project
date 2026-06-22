package edu.miu.cs.cs425.swe_online_market_project.controller;
import edu.miu.cs.cs425.swe_online_market_project.model.User;
import edu.miu.cs.cs425.swe_online_market_project.service.UserService;
import edu.miu.cs.cs425.swe_online_market_project.service.imp.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/onlinemarket/secured/services/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @GetMapping("/users")
    public String loadAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @PostMapping("/users")
    public String addUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("errors", result.getAllErrors());
            return "user/user-form";
        }
        userService.addUser(user);
        return "redirect:/user/list";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUserById(id);
        return "redirect:/user/list";
    }

    @GetMapping("/sellers")
    public String getAllSellers(Model model){
        model.addAttribute("sellers", userService.getSellers());
        model.addAttribute("currentUser", userDetailsServiceImpl.getCurrentUser());
        return "secured/services/admin/usrmgmt/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("update/{sellerId}")
    public String approveSeller(@PathVariable("sellerId") long sellerId){
        userService.approveSeller(sellerId);
        return "redirect:/onlinemarket/secured/services/users/sellers";
    }

}
