package com.teliasonera.mts.mvelsimple;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataGenerator {
    public static Map<String, Object> generateData(Set<String> expressions) {
        Map<String, Object> data = new HashMap<>();
        if (expressions.stream().anyMatch(exp -> exp.contains("hasAttributeValue"))) {
          //  data.put("document", new Doc

        }

        for (String expression : expressions) {
            if (!expression.startsWith("document")) {
                data.put(expression, "MockValue");
            }

            if (expression.contains("hasAttributeValue")) {
                // Mock a document-like object for hasAttributeValue
              //  data.put("document", new Document());
            } else if (expression.contains(".")) {
                // Handle nested properties
                String[] parts = expression.split("\\.");
                Map<String, Object> nestedMap = new HashMap<>();
                nestedMap.put(parts[1], "MockValue");
                data.put(parts[0], nestedMap);
            } else {
                // Default mock value
                data.put(expression, "MockValue");
            }
        }
        return data;
    }
}

