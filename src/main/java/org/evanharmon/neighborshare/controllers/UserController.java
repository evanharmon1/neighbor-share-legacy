package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.User;
import org.evanharmon.neighborshare.models.repository.CategoryRepository;
import org.evanharmon.neighborshare.models.repository.ItemRepository;
import org.evanharmon.neighborshare.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String userDetails(@PathVariable int id, Model model) {

        Optional<User> optUser = userRepository.findById(id);
        User user = optUser.get();

        User currentUser = User.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("title", "NeighborShare");
        model.addAttribute("user", user);
        model.addAttribute("items", user.getItems());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "user/index";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editUser(@PathVariable int id, Model model) {

        Optional<User> optUser = userRepository.findById(id);
        User user = optUser.get();

        User currentUser = User.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("title", "NeighborShare");
        model.addAttribute("user", user);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "user/edit";

    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditUser(@ModelAttribute @Valid User user, Errors errors, Model model, String firstName, String lastName, String username, String email, String password, String verifyPassword, int userId) {

        User currentUser = User.getCurrentUser();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit User");
            Optional<User> optUser = userRepository.findById(userId);
            User existingUser = optUser.get();
            model.addAttribute("existingUserId", existingUser.getId());
            model.addAttribute("user", user);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("currentUser", currentUser);
            return "user/edit";
        }

        if (!password.equals(verifyPassword)) {
            if (!password.equals("******")) {
                Optional<User> optUser = userRepository.findById(userId);
                User existingUser = optUser.get();
                model.addAttribute("title", "Edit User");
                model.addAttribute("verifyError", "Passwords do not match");
                model.addAttribute("user", existingUser);
                model.addAttribute("categories", categoryRepository.findAll());
                model.addAttribute("users", userRepository.findAll());
                model.addAttribute("currentUser", currentUser);
                return "user/edit";
            }
        }

        Optional<User> optUser = userRepository.findById(userId);
        User existingUser = optUser.get();

        existingUser.setFirstName(firstName);
        existingUser.setLastName(lastName);
        existingUser.setUsername(username);
        existingUser.setEmail(email);
        if (!password.equals("******")) {
            existingUser.setPassword(User.passwordEncoder.encode(password));
        }
        userRepository.save(existingUser);

        return "redirect:/user/" + userId;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id, Model model) {

        User currentUser = User.getCurrentUser();
        int currentUserId = currentUser.getId();

        if(currentUserId == id) {
            Optional<User> optUser = userRepository.findById(id);
            User user = optUser.get();
            List<Item> items = itemRepository.findAll();
            for (Item item : items) {
                if (item.getUser().getId() == id) {
                    itemRepository.delete(item);
                }
            }
            userRepository.delete(user);
            SecurityContextHolder.clearContext();
        }

        return "redirect:/";

    }

    @RequestMapping(value = "currentuser", method = RequestMethod.GET)
    public String currentUser(Model model){

        User currentUser = User.getCurrentUser();
        int id = currentUser.getId();
        return "redirect:/user/" + id;
    }

}
