package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "user")
@Controller
public class UserController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", User.getAllUsers());
        return "user/index";
    }
}
