package com.teliasonera.mts.mvelsimple.placeholders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Subscription extends Document {
    private static final Random RANDOM = new Random();
    public static Product product = new Product(ServiceClass.HARDWARE);

    public Object getAttributeValue(String attributeName) {
        //   System.out.printf("subscription attribute value: %s \n", attributeName);
        switch (attributeName) {
            case "isPrepaid":
                return false;
            case "user.fullName":
            case "legalOwner.fullName":
            case "billing.fullName":
                return getRandomElement(ENGLISH_NAMES);
            case "product":
                return product.getName();
            case "contractPeriod":
                return RANDOM.nextInt(36) + 1;
            case "legalOwner.fullStreet":
            case "billing.fullStreet":
                return "North sunset street";
            case "legalOwner.zip":
            case "billing.zip":
            case "user.zip":
                return "7080";
            case "legalOwner.city":
            case "user.city":
            case "billing.city":
                return getRandomElement(NORWEGIAN_CITIES);
            case "user.contactPhone":
            case "legalOwner.contactPhone":
            case "npContact":
                return String.format("47%07d", RANDOM.nextInt(10000000));
            case "user.email":
            case "legalOwner.email":
            case "billing.invoiceEmailAddress":
                return String.format("user%d@domain.com", RANDOM.nextInt(1000));
            case "msisdn":
                return "22334455";
            case "icc":
                return String.valueOf(RANDOM.nextInt(900000000) + 1000);
            case "product.monthlyCharge.inVat":
                return product.getMonthlyChargeInVat();
            case "product.disclaimers":
                return "product.disclaimers";
            case "product.monthlyDiscount.inVat":
                return product.getMonthlyDiscount().getInVat();
            case "product.monthlyDiscount.durationInMonths":
                return product.getMonthlyDiscount().getDurationInMonths();
            case "product.offeringDescriptionLong":
                return Product.generateRandomProductDescription();
            case "services":
                List<IPlaceholder> serviceList = new ArrayList<>();
                for (int i = 0; i < RANDOM.nextInt(100) + 10; i++) {
                    serviceList.add(new Product( ServiceClass.DISCOUNT, ServiceClass.SERVICE));
                }
                return serviceList;
        }
        return String.format("subscription's value %s: ", attributeName);
    }
}
