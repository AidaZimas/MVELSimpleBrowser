package com.teliasonera.mts.mvelsimple;

import com.teliasonera.mts.mvelsimple.imported.BarCodeUtil;
import com.teliasonera.mts.mvelsimple.placeholders.Document;
import com.teliasonera.mts.mvelsimple.placeholders.Orderer;
import com.teliasonera.mts.mvelsimple.placeholders.Product;
import org.apache.commons.lang.StringUtils;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;

import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class MVELTemplateTest {

    public static void main(String[] args) {
        System.setProperty("mvel.debug", "true");
        System.setProperty("mvel.debugger", "true");
        Map<String, Object> vars = new HashMap<>();

        try {
            String templatePath = "C:\\Users\\aida.zimas\\IdeaProjects\\document-manager-config\\src\\main\\resources\\templates\\netcom\\b2c\\order_contract_v4.html";
            String outputPath = "rendered_order_contract_v4.html";

            // Disable DTD validation
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);

            // Read template
            String resolvedTemplate = new String(Files.readAllBytes(Paths.get(templatePath)));

            // Prepare variables
            Document document = new Document();
            Orderer orderer = new Orderer();
            Product product = new Product();
            Method barCodeMethod = BarCodeUtil.class.getMethod("generateBarCode", String.class);
            Method barCodeMethodEx = BarCodeUtil.class.getMethod("generateBarCode", String.class, int.class, String.class);
            vars.put("document", document);
            vars.put("orderer", orderer);
            vars.put("product", product);
            vars.put("DecimalFormat", DecimalFormat.class);
            vars.put("StringUtils", StringUtils.class);
            vars.put("barCode", barCodeMethod);
            vars.put("barCodeEx", barCodeMethodEx);
            vars.put("logger", System.out);


            // Compile and render template
            CompiledTemplate compiledTemplate = TemplateCompiler.compileTemplate(StringUtils.defaultString(resolvedTemplate));
            String result = (String) TemplateRuntime.execute(compiledTemplate, vars);

            // Save rendered output to file
            Files.write(Paths.get(outputPath), result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Rendered file saved to: " + outputPath);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }
}
