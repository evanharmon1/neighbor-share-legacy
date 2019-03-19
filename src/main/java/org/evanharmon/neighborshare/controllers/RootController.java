package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class RootController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "NeighborShare");
        return "index";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute(new User());
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute @Valid User newUser, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }
        User.addAllUsers(newUser);
        model.addAttribute("title", "User");
        return "redirect:/user";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processlogin(@ModelAttribute @Valid User newUser, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            return "login";
        }
        model.addAttribute("title", "login");
        return "redirect:/user";
    }
}
