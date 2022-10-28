package main.propertytaxcalculator.server;

import com.vtence.molecule.Response;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.routing.Routes;
import main.propertytaxcalculator.tax.TaxFactory;
import main.propertytaxcalculator.tax.TaxNames;
import main.propertytaxcalculator.tax.TaxType;

import java.io.IOException;

public class ServerController {
    public void run(WebServer server) throws IOException {
        server.route(new Routes() {{
            get("/").to(
                    request -> Response.ok()
                            .contentType("text/html; charset=utf-8")
                            .done(new HtmlToString().formHTML()));

            post("/").to(request -> {
                String taxType = request.part("tax-type").value();
                String propertyValue = request.part("property-value").value();
                String htmlAsStr = "";
                if(!isNumeric(propertyValue)){
                    htmlAsStr += new HtmlToString().tryAgainHTML();
                    return Response.ok()
                            .contentType("text/html; charset=utf-8")
                            .done(htmlAsStr);
                }
                else
                {
                    String message = "Tax type: " + taxType;
                    message += "\nProperty value: " + propertyValue;
                    message += "\nTaxable amount: " + Double.toString(taxDue(Double.parseDouble(propertyValue), taxType));
                    return Response.ok()
                            .contentType("text/plain; charset=utf-8")
                            .done(message);
                }
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



}
