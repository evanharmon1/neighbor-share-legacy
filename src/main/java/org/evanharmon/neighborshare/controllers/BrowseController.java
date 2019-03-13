package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("view")
@Controller
public class BrowseController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("items", Item.getAllItems());
        return "view/index";
    }
}