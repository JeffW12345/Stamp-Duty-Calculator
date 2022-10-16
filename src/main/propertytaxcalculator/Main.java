package propertytaxcalculator;
import com.vtence.molecule.Response;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.routing.Routes;

import java.io.IOException;

public class Main {

    public void run(WebServer server) throws IOException {
        server.start(request -> Response.ok().contentType("text/html").done("<!DOCTYPE html>" +
                                    "<html>" +
                                    "<body>" +
                                    "<h2>" + "Property tax calculator" + "</h2>" +
                                    "<form method=\"get\">" +
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
        // Deal with GET request. Takes form http://127.0.0.1:8088/?property-value=234&tax-type=lbbt
        server.route(new Routes() {{

            get("/?property-value=:property-value&tax-type=:tax-type").to(
                    request -> Response.ok()
                            .contentType("text/html")
                            .done("<html>" +
                                    "<body>" +
                                    "<h3>Tax due is " +
                                    new TaxFactory().create(TaxNames.LBBT,
                                                    Double.parseDouble(request.parameter("property-value"))).
                                            getTaxDue() +
                                    "</body>" +
                                    "</html>"
                            ));
        }});

                };



    public static void main(String[] args) throws IOException {
        Main example = new Main();
        WebServer webServer = WebServer.create("127.0.0.1", 8088);
        example.run(webServer);
        System.out.println("Access at " + webServer.uri());
    }
}