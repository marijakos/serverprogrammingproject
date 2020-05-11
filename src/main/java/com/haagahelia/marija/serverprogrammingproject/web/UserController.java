package com.haagahelia.marija.serverprogrammingproject.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haagahelia.marija.serverprogrammingproject.domain.SignupForm;
import com.haagahelia.marija.serverprogrammingproject.domain.User;
import com.haagahelia.marija.serverprogrammingproject.domain.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository repository; 

    @RequestMapping(value="/login")
    public String login() { 
        return "login";
    }    
    
    @RequestMapping(value = "signup")
    public String addStudent(Model model){
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }       
    
    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) { // validation errors
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match       
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);
    
                User newUser = new User();
                newUser.setFirstName(signupForm.getFirstName());
                newUser.setLastName(signupForm.getLastName());
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(signupForm.getUsername());
                newUser.setRole("USER");
                if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
                    repository.save(newUser);
                }
                else {
                    bindingResult.rejectValue("username", "err.username", "Username already exists");       
                    return "signup";                    
                }
            }
            else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");        
                return "signup";
            }
        }
        else {
            return "signup";
        }
        return "redirect:/createShoppingList";       
    }    
    
    // Show all users
    @RequestMapping(value="/userlist")
    public String userList(Model model) {    
        model.addAttribute("users", repository.findAll());
        return "userlist";
    }
    
    // RESTful service to get all users
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public @ResponseBody List<User> userListRest() {  
        return (List<User>) repository.findAll();
    }    

    // RESTful service to get user by id
    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<User> findUserRest(@PathVariable("id") Long userId) {    
        return repository.findById(userId);
    }       
    
    // Add new user
    @RequestMapping(value = "/user/add")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "adduser";
    }     
    
    // Save new user
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String save(User user){
        repository.save(user);
        return "redirect:userlist";
    }    

    // Delete user
    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long userId, Model model) {
        repository.deleteById(userId);
        return "redirect:../userlist";
    }     
}
