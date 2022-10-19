package propertytaxcalculator;

import com.vtence.molecule.Response;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.routing.Routes;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import propertytaxcalculator.tax.TaxNames;
import propertytaxcalculator.taxtype.TaxType;

import java.io.IOException;

public class ServerController {
    public void run(WebServer server) throws IOException {
        server.route(new Routes() {{
            get("/").to(
                    request -> Response.ok()
                            .contentType("text/html; charset=utf-8")
                            .done(homePageHTML()));

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

    public String homePageHTML() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Context ctx = new Context();
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("src/main/propertytaxcalculator/resources/templates/");
        resolver.setSuffix(".html");
        templateEngine.addTemplateResolver(resolver);
        return templateEngine.process("home", ctx);
    }

}
