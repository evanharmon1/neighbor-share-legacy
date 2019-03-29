package org.evanharmon.neighborshare.controllers;

import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.User;
import org.evanharmon.neighborshare.models.repository.CategoryRepository;
import org.evanharmon.neighborshare.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String userDetails(@PathVariable int id, Model model) {

        Optional<User> optUser = userRepository.findById(id);
        User user = optUser.get();

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

        model.addAttribute("user", user);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "user/edit";

    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditUser(int userId, String firstName, String lastName, String username, String email, String password, String verifyPassword) {

        Optional<User> optUser = userRepository.findById(userId);
        User user = optUser.get();


        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); //TODO Doesn't work
        userRepository.save(user);

        return "redirect:/user/" + userId;
    }

    @RequestMapping(value = "currentuser", method = RequestMethod.GET)
    public String currentUser(Model model){

        User currentUser = User.getCurrentUser();
        int id = currentUser.getId();
        return "redirect:/user/" + id;
    }

}
