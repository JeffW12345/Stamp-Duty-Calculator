package main.propertytaxcalculator.controller;

import main.propertytaxcalculator.service.TaxSummaryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope("prototype")

public class TaxController {
    private final TaxSummaryService taxSummaryService;

    public TaxController(TaxSummaryService taxSummaryService) {
        this.taxSummaryService = taxSummaryService;
    }

    @RequestMapping("/")
    public String viewTaxDetailsForm() {
        return "home.html";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String processTaxDetails(
            @RequestParam String taxType,
            @RequestParam String propertyValue,
            Model model) throws Exception {
        if(!isNumeric(propertyValue))  {
            return "invalid-property-value.html";
        }
        taxSummaryService.process(taxType, propertyValue);
        model.addAttribute("valueProvided", true);
        model.addAttribute("outputTaxType", taxSummaryService.getTaxName());
        model.addAttribute("outputPropertyValue", taxSummaryService.getPropertyValue());
        model.addAttribute("outputTaxAmount", taxSummaryService.getTaxAmount());
        return "home.html";
    }

    @GetMapping("/search")
    @ResponseBody
    public TaxSummaryService searchAPI(
            @RequestParam(required = true) String taxType,
            @RequestParam(required = true) String propertyValue) {
        taxSummaryService.process(taxType, propertyValue);
        return taxSummaryService;
    }

    private boolean isNumeric(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
