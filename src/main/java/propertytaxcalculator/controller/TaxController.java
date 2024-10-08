package propertytaxcalculator.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import propertytaxcalculator.service.TaxSummaryService;

@Controller
@Scope("prototype")

public class TaxController {
    private final TaxSummaryService taxSummaryService;

    public TaxController(TaxSummaryService taxSummaryService) {
        this.taxSummaryService = taxSummaryService;
    }

    @GetMapping("/")
    public String viewTaxDetailsForm() {
        return "home.html";
    }

    @GetMapping("/invalid-property-value")
    public String invalidPropertyValue() {
        return "invalid-property-value.html";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String processTaxDetails(
            @RequestParam String taxType,
            @RequestParam String propertyValue,
            Model model) throws Exception {
        if(!isNumeric(propertyValue))  {
            return "redirect:/invalid-property-value";
        }
        taxSummaryService.process(taxType, propertyValue);
        model.addAttribute("valueProvided", true);
        model.addAttribute("outputTaxType", taxSummaryService.getTaxName());
        model.addAttribute("outputPropertyValue", taxSummaryService.getPropertyValue());
        model.addAttribute("outputTaxAmount", taxSummaryService.getTaxAmount());
        return "home";
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
