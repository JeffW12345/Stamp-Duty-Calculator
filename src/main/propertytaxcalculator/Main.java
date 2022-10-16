package propertytaxcalculator;
import com.vtence.molecule.Response;
import com.vtence.molecule.WebServer;

import java.io.IOException;

public class Main {

    public void run(WebServer server) throws IOException {
        // Start the default web server and provide a single application, which
        // responds to all incoming requests.
        server.start(request -> Response.ok().done("Hello, World!"));
    }

    public static void main(String[] args) throws IOException {
        Main example = new Main();
        // Run the default web server
        WebServer webServer = WebServer.create("127.0.0.1", 8088);
        example.run(webServer);
        System.out.println("Access at " + webServer.uri());
    }
}
