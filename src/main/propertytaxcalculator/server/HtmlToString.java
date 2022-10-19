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

    @Bean

    protected String taxDueHTML(String taxType, String propertyValue, String taxDue) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Context ctx = new Context(Locale.getDefault());
        ctx.setVariable("tax-type", taxType);
        ctx.setVariable("property-value", propertyValue);
        ctx.setVariable("tax-due", taxDue);
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("src/main/propertytaxcalculator/resources/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCacheable(false);
        resolver.setForceTemplateMode(true);
        templateEngine.addTemplateResolver(resolver);
        return templateEngine.process("result", ctx);
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
