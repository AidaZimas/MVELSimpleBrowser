package com.teliasonera.mts.mvelsimple;

import org.mvel2.templates.TemplateError;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.TemplateRuntime;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

public class MVELTemplateTest {

    public static void main(String[] args) {
        // Enable MVEL debugging
        System.setProperty("mvel.debug", "true");
        System.setProperty("mvel.debugger", "true");

        try {
            // Path to the template file
            String templatePath = "C:\\Users\\aida.zimas\\IdeaProjects\\document-manager-config\\src\\main\\resources\\templates\\netcom\\b2c\\order_contract_v4.html";

            // Read the template file content
            String template = new String(Files.readAllBytes(Paths.get(templatePath)));

            // Analyze the template to extract expressions
            Set<String> expressions = TemplateAnalyzer.analyzeTemplate(template);

            // Generate mock data based on extracted expressions
            Map<String, Object> data = DataGenerator.generateData(expressions);

            // Log the generated data
            System.out.println("Generated Mock Data:");
            data.forEach((key, value) -> {
                System.out.println("Key: " + key + " | Value: " + value);
            });

            // Render the template with the generated data
            String result = (String) TemplateRuntime.eval(template, data);

            // Print the rendered output
            System.out.println("\nRendered Output:\n" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
