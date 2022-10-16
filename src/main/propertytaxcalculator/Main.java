package propertytaxcalculator;
import com.vtence.molecule.Response;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.middlewares.Failsafe;

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
    }


    public static void main(String[] args) throws IOException {
        Main example = new Main();
        WebServer webServer = WebServer.create("127.0.0.1", 8088);
        example.run(webServer);
        System.out.println("Access at " + webServer.uri() + "?encoding=utf-8");
    }
}