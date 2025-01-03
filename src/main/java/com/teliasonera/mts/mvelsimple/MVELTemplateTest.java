package com.teliasonera.mts.mvelsimple;

import org.mvel2.templates.TemplateRuntime;
import java.util.*;

public class MVELTemplateTest {
    public static void main(String[] args) {
        // MVEL template as a string
        String template = "@if{rowsForOffering.size > 0}\n" +
                "<div>\n" +
                "    <div class=\"fieldset\">\n" +
                "        <div>\n" +
                "            <div class=\"main\" style=\"width: 972px;\">\n" +
                "                <div class=\"service-box-wider\" style=\"margin-left:0;\">\n" +
                "                    <div class=\"subcaption\">Abonnement</div>\n" +
                "                </div>\n" +
                "                <div class=\"service-box\">\n" +
                "                    <div class=\"subcaption\">Pris per måned</div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"clear\"><!----></div>\n" +
                "        </div>\n" +
                "        @foreach{ offeringRow : rowsForOffering}\n" +
                "        <div>\n" +
                "            <div class=\"main\" style=\"width: 972px;\">\n" +
                "                <div class=\"service-box-wider\" style=\"margin-left:0;\">\n" +
                "                    <div class=\"data\">\n" +
                "                        @if{offeringRow[\"offRowIsAdditionalText\"] == \"true\"}\n" +
                "                            <i>@{offeringRow[\"offRowText\"]}</i>\n" +
                "                        @else{}\n" +
                "                            @{offeringRow[\"offRowText\"]}\n" +
                "                        @end{}\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"service-box\">\n" +
                "                    <div class=\"data\">\n" +
                "                        @if{offeringRow[\"offRowIsAdditionalText\"] == \"true\"}\n" +
                "                            <i>@{offeringRow[\"offRowValue\"]}</i>\n" +
                "                        @else{}\n" +
                "                            @{offeringRow[\"offRowValue\"]}\n" +
                "                        @end{}\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"clear\"><!----></div>\n" +
                "        </div>\n" +
                "        @end{}\n" +
                "        <div class=\"service-box-wider\" style=\"margin-left:0; width: 80%;\">\n" +
                "            <div class=\"subcaption\" style=\"font-weight: bold; text-decoration: underline; margin-left:70%\">\n" +
                "                <div><b>Minste totalpris&nbsp;&nbsp;Å betale per måned</b></div>\n" +
                "                <div><b>@{minimumTotalPrice}&nbsp;;&nbsp;@{pricePerMonth}</b></div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "@end{}";

        // Test data
        Map<String, Object> data = new HashMap<>();
        List<Map<String, String>> rowsForOffering = new ArrayList<>();
        Map<String, String> row1 = new HashMap<>();
        row1.put("offRowIsAdditionalText", "true");
        row1.put("offRowText", "Sample Text 1");
        row1.put("offRowValue", "Value 1");
        rowsForOffering.add(row1);

        Map<String, String> row2 = new HashMap<>();
        row2.put("offRowIsAdditionalText", "false");
        row2.put("offRowText", "Sample Text 2");
        row2.put("offRowValue", "Value 2");
        rowsForOffering.add(row2);

        data.put("rowsForOffering", rowsForOffering);
        data.put("minimumTotalPrice", "100.00");
        data.put("pricePerMonth", "10.00");

        // Render template
        String result = (String) TemplateRuntime.eval(template, data);
        System.out.println(result);
    }
}
