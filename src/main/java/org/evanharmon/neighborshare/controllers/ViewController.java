package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.User;
import org.evanharmon.neighborshare.models.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class ViewController {

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        User currentUser = User.getCurrentUser();

        List<Item> userItems = currentUser.getItems();

        model.addAttribute("items", userItems);

        return "view/index";
    }
}