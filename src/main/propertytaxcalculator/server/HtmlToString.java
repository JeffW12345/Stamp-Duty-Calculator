package propertytaxcalculator.server;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.util.Locale;

public class HtmlToString {
    protected String tryAgainHTML() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Context ctx = new Context();
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("src/main/propertytaxcalculator/resources/templates/");
        resolver.setSuffix(".html");
        templateEngine.addTemplateResolver(resolver);
        return templateEngine.process("invalid-input", ctx);
    }

    protected String formHTML() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Context ctx = new Context();
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("src/main/propertytaxcalculator/resources/templates/");
        resolver.setSuffix(".html");
        templateEngine.addTemplateResolver(resolver);
        return templateEngine.process("home", ctx);
    }
}
