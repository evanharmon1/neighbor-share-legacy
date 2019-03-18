package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RootController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("title", "Register");
        return "register";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processRegister(@RequestParam String email, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName) {
        User newUser = new User(email, password, firstName, lastName);
        User.addAllUsers(newUser);
        return "redirect:/user";
    }

}
