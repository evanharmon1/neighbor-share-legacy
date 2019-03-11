package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("item")
@Controller
public class ItemController {

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("items", Item.getAllItems());
        model.addAttribute("categories", Category.values());
        return "item/index";
    }


    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("categories", Category.values());
        return "item/add";
    }
}
