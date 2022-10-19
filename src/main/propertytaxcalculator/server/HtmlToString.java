package main.propertytaxcalculator.server;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

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
