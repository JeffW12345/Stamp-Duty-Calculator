package propertytaxcalculator;
import com.vtence.molecule.Response;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.middlewares.Failsafe;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

    public void run(WebServer server) throws IOException {
        // The failsafe middleware captures internal server errors and renders a default 500 page,
        // showing a stack trace of the exception and its causes.
        server.add(new Failsafe())
                .start(request -> {
                    // An unsupported charset will cause an exception, which will in turn cause the failsafe middleware
                    // to render a 500 page
                    Charset encoding = Charset.forName(request.parameter("encoding"));
                    return Response.ok()
                            // The content-type charset will be used automatically to encode the response
                            .contentType("text/html; charset=" + encoding.displayName().toLowerCase())
                            // Our HTML page contains characters outside the ISO-8859-1 alphabet.
                            .done("<!DOCTYPE html>" +
                                    "<html>" +
                                    "<body>" +
                                    "<h2>" + "Property tax calculator" + "</h2>" +
                                    "<form action=\"/action_page.php\">" +
                                    "<label for=\"property-value\"> Value of property: </label> <br>" +
                                    "<input type=\"text\" id=\"fname\" name=\"property-value\">" + "<br>" +
                                    "<label for=\"cars\">" + "Choose a tax type: </label>" +
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
                    }


    public static void main(String[] args) throws IOException {
        Main example = new Main();
        // Run the default web server
        WebServer webServer = WebServer.create("127.0.0.1", 8088);
        example.run(webServer);
        System.out.println("Access at " + webServer.uri() + "?encoding=utf-8");
    }
}
