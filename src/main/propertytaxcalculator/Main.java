package main.propertytaxcalculator;

import com.vtence.molecule.WebServer;
import main.propertytaxcalculator.server.ServerController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerController serverController = new ServerController();
        WebServer webServer = WebServer.create("127.0.0.1", 8088);
        serverController.run(webServer);
        System.out.println("Access at " + webServer.uri() + "/");
    }
}