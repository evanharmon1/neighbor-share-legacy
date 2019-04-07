package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.User;
import org.evanharmon.neighborshare.models.repository.CategoryRepository;
import org.evanharmon.neighborshare.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;


@Controller
public class RootController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "")
    public String index(Model model) {

         Object loggedInUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedInUser.equals("anonymousUser")) {
            model.addAttribute("title", "NeighborShare");
            return "index";
        }

        return "redirect:/view";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute(new User());

        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute @Valid User newUser, Errors errors, Model model) {

        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            model.addAttribute("title", "Register");
            model.addAttribute("existingUsernameError", "That username has already been registered");
            return "register";
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        if (!newUser.getPassword().equals(newUser.getVerifyPassword())) {
            model.addAttribute("title", "Register");
            model.addAttribute("verifyError", "Passwords do not match");
            return "register";
        }

        model.addAttribute("title", "User");
        newUser.setPassword(User.passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        return "redirect:/view";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processlogin(@ModelAttribute @Valid User user, Errors errors, Model model) {

        // Code below is never run due to Spring Security, I think
        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            return "login";
        }

        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.getUsername() == user.getUsername()) {
            if (existingUser.getPassword() == user.getPassword()) {
                return "redirect:/view";
            }
            else {
                model.addAttribute("title", "Login");
                model.addAttribute("errors", "Password not correct");
                return "login";
            }
        }
        else {
            model.addAttribute("title", "Login");
            model.addAttribute("errors", "User not found");
            return "login";
        }

    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout() {
        return "redirect:/login";
    }
}
