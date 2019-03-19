package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.Category;
import org.evanharmon.neighborshare.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("item")
@Controller
public class ItemController {

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("items", Item.getAllItems());
        model.addAttribute("categories", Category.values());
        model.addAttribute("users", User.getAllUsers());
        return "item/index";
    }


    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Item");
        model.addAttribute(new Item());
        model.addAttribute("categories", Category.values());
        return "item/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Item newItem, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Item");
            model.addAttribute("categories", Category.values());
            return "item/add";
        }


        Item.addAllItems(newItem);
        ArrayList<User> users = User.getAllUsers();
        User protoUser = users.get(0);
        protoUser.addItem(newItem);
        newItem.setOwner(protoUser);
        model.addAttribute("title", "Item");
        model.addAttribute("items", Item.getAllItems());
        return "redirect:/item";
    }
}
