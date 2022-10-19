package propertytaxcalculator;

import com.vtence.molecule.WebServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new ServerController().process()); // FOR TESTING
        ServerController serverController = new ServerController();
        WebServer webServer = WebServer.create("127.0.0.1", 8088);
        serverController.run(webServer);
        System.out.println("Access at " + webServer.uri() + "/");
    }
}