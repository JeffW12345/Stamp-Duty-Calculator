package propertytaxcalculator.server;

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

    protected String taxDueHTML(String taxType, String propertyValue, String taxDue) {
        System.out.println(taxDue);
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Context ctx = new Context();
        ctx.setVariable("tax-type", taxType);
        ctx.setVariable("property-value", propertyValue);
        ctx.setVariable("tax-due", propertyValue);
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("src/main/propertytaxcalculator/resources/templates/");
        resolver.setSuffix(".html");
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
