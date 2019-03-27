package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.Category;
import org.evanharmon.neighborshare.models.User;
import org.evanharmon.neighborshare.models.repository.CategoryRepository;
import org.evanharmon.neighborshare.models.repository.ItemRepository;
import org.evanharmon.neighborshare.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
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

//        if (errors.hasErrors() || categoryId == null) {
//            model.addAttribute("title", "Add Item");
//            model.addAttribute("categories", categoryRepository.findAll());
//            if (categoryId == null) {
//                model.addAttribute("categoryError", "You must choose a category");
//            }
//            return "item/add";
//        }

        //Test Item add
        User testUser = userRepository.findByUsername("alby");
        Category testCategory = categoryRepository.getOne(2);
        Item testItem = new Item(99, "testName", "testDescription", testCategory, testUser);
        itemRepository.save(testItem);

        Optional<Category> cat = categoryRepository.findById(categoryId);
        Category category = cat.get();
        newItem.setCategory(category);

        User currentUser = User.getCurrentUser();
        newItem.setUser(currentUser);

        //Error here I think
        //There was an unexpected error (type=Internal Server Error, status=500).
        //failed to lazily initialize a collection of role: org.evanharmon.neighborshare.models.User.items, could not initialize proxy - no Session
        //org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: org.evanharmon.neighborshare.models.User.items, could not initialize proxy - no Session
        currentUser.addItem(newItem);
        itemRepository.save(newItem);

        model.addAttribute("title", "Item");
        model.addAttribute("items", itemRepository.findAll());
        return "redirect:/item";
    }
}
