package com.teliasonera.mts.mvelsimple.placeholders;

public class Subscription extends Document{

    public Object getAttributeValue(String attributeName) {
        System.out.printf("subscription attribute value: %s \n", attributeName);
        if (attributeName.equals("isPrepaid")) {
            return false;
        } else if (attributeName.equals("legalOwner.fullStreet")) {
            return "North sunset street";
        } else if (attributeName.equals("legalOwner.zip")) {
            return "7080";
        } else if (attributeName.equals("legalOwner.city")) {
            return "Heimdal";
        } else if (attributeName.equals("product")) {
            return new Product();
        }
        return String.format("subscription's value %s: ", attributeName);
    }
}
