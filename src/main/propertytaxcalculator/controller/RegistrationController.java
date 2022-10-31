package main.propertytaxcalculator.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope("prototype")

public class RegistrationController {
    @RequestMapping("/register")
    public String viewRegistrationForm() {
        return "register.html";
    }
}
