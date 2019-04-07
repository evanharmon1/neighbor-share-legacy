package org.evanharmon.neighborshare.controllers;

import org.apache.commons.io.FilenameUtils;
import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.Category;
import org.evanharmon.neighborshare.models.User;
import org.evanharmon.neighborshare.models.repository.CategoryRepository;
import org.evanharmon.neighborshare.models.repository.ItemRepository;
import org.evanharmon.neighborshare.models.repository.UserRepository;
import org.evanharmon.neighborshare.services.AmazonS3ClientService;
import org.evanharmon.neighborshare.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model) {

        User currentUser = User.getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "item/index";
    }


    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String add(Model model) {

        User currentUser = User.getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("title", "Add Item");
        model.addAttribute(new Item());
        model.addAttribute("categories", categoryRepository.findAll());
        return "item/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Item newItem, Errors errors, @RequestParam(value = "categoryId", required=false) Integer categoryId, @RequestPart(value = "file") MultipartFile file, Model model) {

        User currentUser = User.getCurrentUser();

        if (errors.hasErrors() || categoryId == null) {
            model.addAttribute("title", "Add Item");
            model.addAttribute("categories", categoryRepository.findAll());
            if (categoryId == null) {
                model.addAttribute("categoryError", "You must choose a category");
            }
            model.addAttribute("currentUser", currentUser);
            return "item/add";
        }

        String awsFileName = this.amazonS3ClientService.uploadFileToS3Bucket(file, true);
        String filename = "https://s3-us-west-2.amazonaws.com/neighborshare-images/" + awsFileName;

        Optional<Category> cat = categoryRepository.findById(categoryId);
        Category category = cat.get();
        newItem.setCategory(category);
        newItem.setImage(filename);

        newItem.setUser(currentUser);
        currentUser.addItem(newItem);
        itemRepository.save(newItem);

        model.addAttribute("title", "Item");
        model.addAttribute("items", itemRepository.findAll());
        return "redirect:/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String itemDetails(@PathVariable int id, @RequestParam(value="email", required=false) String email, Model model) {

        Optional<Item> optItem = itemRepository.findById(id);
        Item item = optItem.get();

        User currentUser = User.getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        int currentItemOwnerId = item.getUser().getId();
        int currentUserId = currentUser.getId();
        boolean canEdit = (currentItemOwnerId == currentUserId);

        if (email != null) {
            model.addAttribute("email", "Your email to " + item.getUser().getFirstName() + " was sent successfully. " + item.getUser().getFirstName() + " will email you if they can lend you the item.");
            model.addAttribute("item", item);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            return "item/item-detail";
        }


        model.addAttribute("canEdit", canEdit);
        model.addAttribute("item", item);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "item/item-detail";

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editItem(@PathVariable int id, Model model) {

        Optional<Item> optItem = itemRepository.findById(id);
        Item item = optItem.get();

        User currentUser = User.getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("item", item);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("currentCategory", item.getCategory());
        return "item/edit";

    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditItem(@ModelAttribute @Valid Item item, Errors errors, Model model, int itemId, String name, String description, int category, @RequestPart(value = "file") MultipartFile file) {

        User currentUser = User.getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Item");
            Optional<Item> optItem = itemRepository.findById(itemId);
            Item existingItem = optItem.get();
            model.addAttribute("existingItemId", existingItem.getId());
            model.addAttribute("item", item);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            return "item/edit";
        }

        this.amazonS3ClientService.uploadFileToS3Bucket(file, true);

        Optional<Item> optItem = itemRepository.findById(itemId);
        Item existingItem = optItem.get();

        Optional<Category> cat = categoryRepository.findById(category);
        Category newCategory = cat.get();


        existingItem.setName(name);
        existingItem.setDescription(description);
        existingItem.setCategory(newCategory);
        itemRepository.save(existingItem);

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
        String subject = "NeighborShare Borrow Request for " + item.getName();
        String body = requester + " would like to borrow your " + requestedItem + ". Please reply to this email to let them know if they can borrow it and how they should pick it up. Thanks!";

        sender.sendSimpleMessage(to,from, subject, body);

        return "redirect:/item/" + id + "?email=sent";

    }


}
