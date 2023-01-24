package com.informationengineering.internetapplicationproject.Controllers;

import com.informationengineering.internetapplicationproject.Models.Email;
import com.informationengineering.internetapplicationproject.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/sendEmailPage", method = RequestMethod.GET)
    String sendEmailPage() {
        return "Email";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    String sendEmail(@ModelAttribute Email email) {
        emailService.sendEmail(email);
        return "index";
    }


}
