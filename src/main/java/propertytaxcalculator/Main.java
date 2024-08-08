package propertytaxcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws MalformedURLException {
        SpringApplication.run(Main.class, args);
        System.out.println("Click on " + new URL("http://localhost:8080/"));
    }
}