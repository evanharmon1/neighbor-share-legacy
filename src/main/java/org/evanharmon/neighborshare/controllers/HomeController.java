package org.evanharmon.neighborshare.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("test")
@Controller
public class HomeController {

    @RequestMapping(value = "")
    public String index(Model model) {
        return "index";

    }
}