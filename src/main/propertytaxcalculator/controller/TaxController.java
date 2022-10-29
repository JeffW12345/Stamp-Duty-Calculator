package main.propertytaxcalculator.controller;

import main.propertytaxcalculator.service.TaxSummaryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.DecimalFormat;

@Controller
@Scope("prototype")

public class TaxController {
    private final TaxSummaryService taxSummaryService;

    public TaxController(TaxSummaryService taxSummaryService) {
        this.taxSummaryService = taxSummaryService;
    }

    @RequestMapping("/")
    public String viewForm(Model model) {
        model.addAttribute("outputTaxType", "");
        model.addAttribute("outputPropertyValue", "");
        model.addAttribute("outputTaxAmount", "");
        model.addAttribute("valueProvided", false);
        return "home.html";
    }


    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String home(
            @RequestParam String taxType,
            @RequestParam String propertyValue,
            Model model) throws Exception {
        if(!isNumeric(propertyValue))  {
            return "invalid-input.html";
        }
        taxSummaryService.process(taxType, propertyValue);
        model.addAttribute("valueProvided", true);
        model.addAttribute("outputTaxType", taxSummaryService.getTaxName());
        model.addAttribute("outputPropertyValue", formatAsCurrency(propertyValue));
        model.addAttribute("outputTaxAmount", formatAsCurrency(taxSummaryService.getTaxAmount() + ""));
        return "home.html";
    }

    private String formatAsCurrency(String renderToCurrency) {
        DecimalFormat df = new DecimalFormat("0.00");
        double amount = Double.parseDouble(renderToCurrency);
        return df.format(amount);
    }

    // Taken from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    private boolean isNumeric(String name) {
        try {
            Double.parseDouble(name);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}