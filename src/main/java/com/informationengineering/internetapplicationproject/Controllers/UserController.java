package com.informationengineering.internetapplicationproject.Controllers;


import com.informationengineering.internetapplicationproject.Models.User;
import com.informationengineering.internetapplicationproject.Services.UserService;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.Wildcard;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("user-details")
    @ResponseBody
    ResponseEntity<?> getUserDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUser(user.getUsername());
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @RequestMapping("login")
    String loginPage() {

        return "login";
    }

    @RequestMapping("index")
    String indexPage() {
        return "index";
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    String login(@ModelAttribute User user, HttpServletResponse response, Model model) {
        String token = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            token = userService.login(user);
        } catch (Exception exception) {
            exception.printStackTrace();
            model.addAttribute("error", "Invalid Username or Password");
            return loginPage();
        }
        Cookie authenticationCookie = new Cookie("Authorization", token);
        authenticationCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(authenticationCookie);

        return "index";
    }

    @RequestMapping(value = "/logoutUser", method = RequestMethod.GET)
    String logout(HttpServletResponse response) {
        SecurityContextHolder.getContext().setAuthentication(null);
        Cookie authenticationCookie = new Cookie("Authorization", null);
        authenticationCookie.setMaxAge(0);
        response.addCookie(authenticationCookie);
        return "index";
    }
}