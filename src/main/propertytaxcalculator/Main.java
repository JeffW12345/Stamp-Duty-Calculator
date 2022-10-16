package propertytaxcalculator;
import com.vtence.molecule.Response;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.middlewares.Failsafe;
import com.vtence.molecule.routing.Routes;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

    public void run(WebServer server) throws IOException {
        server.add(new Failsafe())
                .start(request -> {
                    Charset encoding = Charset.forName(request.parameter("encoding"));
                    return Response.ok()
                            .contentType("text/html; charset=" + encoding.displayName().toLowerCase())
                            .done("<!DOCTYPE html>" +
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
                                    "</html>");
                });
        // Get requests take format http://127.0.0.1:8088/?property-value=234&tax-type=lbbt
        server.route(new Routes() {{
            // Redirect to http://127.0.0.1:8088/?encoding=utf-8&property-value=234&tax-type=lbbt to avoid error due
            // to lack of encoding utf 8 in parameter.
            post("/?property-value=:property-value&tax-type=:tax-type").to(request -> {
                String propertyValue = request.parameter("username");
                String taxType = request.parameter("tax-type");
                return Response.redirect("/?encoding=utf-8&property-value=" + propertyValue + "&tax-type=" + taxType)
                        .done();
            });
        }});
        // TODO - Write code that provides the taxable amount in response to the GET request.
        // Adapt from https://github.com/testinfected/molecule/blob/master/src/test/java/examples/routing/RoutingExample.java
    }


    public static void main(String[] args) throws IOException {
        Main example = new Main();
        WebServer webServer = WebServer.create("127.0.0.1", 8088);
        example.run(webServer);
        System.out.println("Access at " + webServer.uri() + "?encoding=utf-8");
    }
}