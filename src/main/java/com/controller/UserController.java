package com.controller;

import com.domin.Role;
import com.domin.User;
import com.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
 class userController {
    @Autowired
    private UserRepo userRepo;
    
    @GetMapping("/user")
    public String user() {
        return "user";
    }
    
    @PostMapping("/user")
    public String addUser( User user, Map<String, Object> model) {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
        return "redirect:/user";
    }
    
    @GetMapping("info")
    public String info(Map<String,Object>model){
    Iterable<User> users;
    users = userRepo.findAll();
    model.put("user",users);
    return "info";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String username, Map<String,Object>model){
        User user = userRepo.findByUsername(username);
        userRepo.delete(user);
        Iterable<User> user1;
        user1 = userRepo.findAll();
        model.put("user",user1);
        return "/info";
    }

}
