package main.propertytaxcalculator.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Controller
@Scope("prototype")

public class RegistrationController {

    @GetMapping("/invalid-email")
    public String invalidPropertyValue() {
        return "invalid-email.html";
    }

    @GetMapping("/register")
    public String viewRegistrationForm() {
        return "register.html";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String processTaxDetails(
            @RequestParam String emailAddress,
            Model model) throws Exception {
        if(!isValidEmailAddress(emailAddress))  {
            return "redirect:/invalid-email";
        }
        //TODO - Actions if valid email address provided.
        return "home.html";
    }

    // From https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
    private static boolean isValidEmailAddress(String email) {
        try {
            new InternetAddress().validate();
        } catch (AddressException ex) {
            return false;
        }
        return true;
    }
}

