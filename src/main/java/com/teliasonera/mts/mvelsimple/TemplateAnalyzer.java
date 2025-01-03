package com.teliasonera.mts.mvelsimple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Set;

public class TemplateAnalyzer {
    public static Set<String> analyzeTemplate(String template) {
        Set<String> expressions = new HashSet<>();
        Pattern pattern = Pattern.compile("@\\{([^}]+)\\}|@if\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                expressions.add(matcher.group(1).trim());
            } else if (matcher.group(2) != null) {
                expressions.add(matcher.group(2).trim());
            }
        }
        return expressions;
    }
}
