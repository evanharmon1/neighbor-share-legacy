package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.Category;
import org.evanharmon.neighborshare.models.User;
import org.evanharmon.neighborshare.models.repository.CategoryRepository;
import org.evanharmon.neighborshare.models.repository.ItemRepository;
import org.evanharmon.neighborshare.models.repository.UserRepository;
import org.evanharmon.neighborshare.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailServiceImpl sender;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "item/index";
    }


    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Item");
        model.addAttribute(new Item());
        model.addAttribute("categories", categoryRepository.findAll());
        return "item/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Item newItem, Errors errors, @RequestParam(value = "categoryId", required=false) Integer categoryId, Model model) {

        if (errors.hasErrors() || categoryId == null) {
            model.addAttribute("title", "Add Item");
            model.addAttribute("categories", categoryRepository.findAll());
            if (categoryId == null) {
                model.addAttribute("categoryError", "You must choose a category");
            }
            return "item/add";
        }


        Optional<Category> cat = categoryRepository.findById(categoryId);
        Category category = cat.get();
        newItem.setCategory(category);

        User currentUser = User.getCurrentUser();
        newItem.setUser(currentUser);
        currentUser.addItem(newItem);
        itemRepository.save(newItem);

        model.addAttribute("title", "Item");
        model.addAttribute("items", itemRepository.findAll());
        return "redirect:/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String itemDetails(@PathVariable int id, Model model) {

        Optional<Item> optItem = itemRepository.findById(id);
        Item item = optItem.get();

        model.addAttribute("item", item);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "item/item-detail";

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editItem(@PathVariable int id, Model model) {

        Optional<Item> optItem = itemRepository.findById(id);
        Item item = optItem.get();

        model.addAttribute("item", item);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("currentCategory", item.getCategory());
        return "item/edit";

    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditItem(int itemId, String name, String description, int category) {

        Optional<Item> optItem = itemRepository.findById(itemId);
        Item item = optItem.get();

        Optional<Category> cat = categoryRepository.findById(category);
        Category newCategory = cat.get();


        item.setName(name);
        item.setDescription(description);
        item.setCategory(newCategory);
        itemRepository.save(item);

        return "redirect:/item/" + itemId;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteItem(@PathVariable int id, Model model) {

        Optional<Item> optItem = itemRepository.findById(id);
        Item item = optItem.get();
        itemRepository.delete(item);

        User currentUser = User.getCurrentUser();
        int userId = currentUser.getId();

        return "redirect:/user/" + userId;

    }

    @RequestMapping(value = "/{id}/email", method = RequestMethod.GET)
    public String email(@PathVariable int id, Model model) {

        Optional<Item> optItem = itemRepository.findById(id);
        Item item = optItem.get();
        User currentUser = User.getCurrentUser();

        String requestedItem = item.getName();
        String to = item.getUser().getEmail();
        String from = currentUser.getEmail();
        String requester = currentUser.getFirstName();
        String body = requester + " would like to borrow your " + requestedItem + ". Please reply to this email to let them know if they can borrow it and how they should pick it up. Thanks!";

        sender.sendSimpleMessage(to,from, "NeighborShare Borrow Request", body);

        return "redirect:/item/" + id;

    }


}
