package com.teliasonera.mts.mvelsimple.placeholders;

public class Product extends Document{

    public Object getAttributeValue(String attributeName) {
        System.out.printf("product attribute value: %s \n", attributeName);
        if (attributeName.equals("product.monthlyCharge.inVat")) {
            return "1000";
        } else if (attributeName.equals("legalOwner.fullStreet")) {
            return "North sunset street";
        } else if (attributeName.equals("legalOwner.zip")) {
            return "7080";
        } else if (attributeName.equals("legalOwner.city")) {
            return "Heimdal";
        }
        return String.format("product's attribute value %s: ", attributeName);
    }

}
