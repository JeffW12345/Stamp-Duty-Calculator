package propertytaxcalculator;

import com.vtence.molecule.Response;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.routing.Routes;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import propertytaxcalculator.tax.TaxNames;
import propertytaxcalculator.taxtype.TaxType;
import propertytaxcalculator.resources.templates.*;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerController {
    public void run(WebServer server) throws IOException {
        server.route(new Routes() {{
            get("/").to(
                    request -> Response.ok()
                            .contentType("text/html; charset=utf-8")
                            .done("<!DOCTYPE html>" +
                                    "<html>" +
                                    "<body>" +
                                    "<h2>" + "Property tax calculator" + "</h2>" +
                                    "<form enctype='multipart/form-data' action='/' method='post'>\n" +
                                    "<label for=\"property-value\"> Value of property: </label> <br>" +
                                    "<input type=\"text\" id=\"fname\" name=\"property-value\">" + "<br>" +
                                    "<label for=\"tax-type\">" + "Choose a tax type: </label>" +
                                    "<br>" +
                                    "<select name=\"tax-type\">" +
                                    "<option value=\"lbbt\"> Lbbt (Scottish property tax) </option>" +
                                    "</select>" +
                                    "<br>" +
                                    "<br>" +
                                    "<input type=\"submit\" value=\"Submit\">" +
                                    "</form>" +
                                    "</body>" +
                                    "</html>"));

            post("/").to(request -> {
                String taxType = request.part("tax-type").value();
                String propertyValue = request.part("property-value").value();
                String str = "";
                if(!isNumeric(propertyValue)){
                    str += ("You've not given me a number! Press the back button and try again.");
                }
                else
                {
                    str += "Value of property: " + propertyValue + "\n";
                    str += "Tax amount: " + taxDue(Double.parseDouble(propertyValue), taxType);
                }
                return Response.ok()
                        .contentType("text/plain; charset=utf-8")
                        .done(str);
            });
        }});
    }
    private double taxDue(double propertyValue, String taxType) {
        if(taxType.equals("lbbt")){
            TaxType tax = new TaxFactory().create(TaxNames.LBBT, propertyValue);
            tax.calculate();
            return tax.getTaxDue();
        }
        return 0;
    }

    // Stolen from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    private boolean isNumeric(String name) {
        try {
            Double.parseDouble(name);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public String process() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Context ctx = new Context();
        return templateEngine.process("home", ctx);
    }

}
