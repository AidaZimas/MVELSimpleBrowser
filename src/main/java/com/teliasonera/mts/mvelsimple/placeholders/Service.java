package com.teliasonera.mts.mvelsimple.placeholders;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class Service extends Document {
    private static final Random RANDOM = new Random();

    public Object getAttributeValue(String attributeName) {
        System.out.printf("attribute value: %s \n", attributeName);
        if (attributeName.equals("insuranceScreenPriceAfter.inVat")) {
            return 4654;
        } else if ("flexibleSwitch.includedInsuranceInVat".equals(attributeName)) {
            return 526;
        } else if ("flexibleSwitch.includedInsuranceExVat".equals(attributeName)) {
            return 466;
        } else {
            return String.format("Service's attribute value: %s\n", attributeName);
        }
    }

    public Object get(String attributeName) {
        if ("name".equals(attributeName)) {
            return PRODUCTS.get(RANDOM.nextInt(PRODUCTS.size()));
        } else if ("action".equals(attributeName)) {
            return "ADD";
        } else if ("monthlyCharge.inVat".equals(attributeName)) {
            return "-4226";
        } else if ("lifetimeInMonths".equals(attributeName)) {
            return "5";
        } else if ("flexibleSwitch.isTeliaFinance".equals(attributeName)) {
            return "false";
        } else if ("flexibleSwitch.numberOfInstallments".equals(attributeName)) {
            return 2;
        } else if ("flexibleSwitch.installmentAmountInVat".equals(attributeName)) {
            return 200;


        } else if ("customerRelevant".equals(attributeName)) {
            return "true";
        } else if ("insuranceClass".equals(attributeName)) {
            return "SWITCH";
        } else if ("category".equals(attributeName)) {
            return "GENERAL";
        } else {
            return String.format("Service's get value: %s", attributeName);
        }
    }


    private static final List<String> PRODUCTS = Arrays.asList(
            "Mobile Plan A", "Mobile Plan B", "Home Internet Plan",
            "5G Mobile Plan", "Fiber Optic Broadband", "Prepaid SIM Card",
            "Data Booster", "Business Internet Package", "Unlimited Talk Plan",
            "Smartphone X1", "Smartphone X2", "Smartphone X3",
            "Tablet with SIM", "Wi-Fi Router", "4G LTE Device",
            "Mobile Hotspot", "Home Security System", "VoIP Service",
            "International Calling Plan", "Telecom Satellite Service"
    );



}
