package com.teliasonera.mts.mvelsimple.placeholders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Document implements IPlaceholder {

    private static final List<String> SCANDINAVIAN_NAMES = Arrays.asList("Lars Erik", "Anna Karlsen", "Bjorn Hansen", "Emma Olsson");
    public static final List<String> PORTUGUESE_NAMES = Arrays.asList("João Silva", "Maria Oliveira", "Pedro Costa", "Ana Santos");
    public static final List<String> ENGLISH_NAMES = Arrays.asList("John Smith", "Jane Doe", "William Brown", "Emily Davis");
    public static final List<String> NORWEGIAN_CITIES = Arrays.asList("Oslo", "Bergen", "Trondheim", "Stavanger");
    private static final List<String> NORWEGIAN_STREETS = Arrays.asList("Karl Johans gate", "Storgata", "Sørkedalsveien", "Dronningens gate");
    private static final Random RANDOM = new Random();

    public static String getRandomElement(List<String> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    public boolean hasAttributeValue(String attributeName) {
        //System.out.printf("check boolean attribute: %s \n", attributeName);
        return true;
    }

    public Object getAttributeValue(String attributeName) {
        //System.out.printf("attribute value: %s \n", attributeName);
        switch (attributeName) {
            case "legalOwner.fullName":
            case "user.fullName":
            case "billing.fullName":
                return getRandomElement(ENGLISH_NAMES);
            case "salesAgent.firstNameLastInitial":
            case "npAddress.fullName":
                return getRandomElement(SCANDINAVIAN_NAMES);
            case "legalOwner.fullStreet":
            case "user.fullStreet":
            case "billing.fullStreet":
                return getRandomElement(NORWEGIAN_STREETS);
            case "legalOwner.zip":
            case "user.zip":
            case "billing.zip":
                return String.format("%04d", RANDOM.nextInt(9999));
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
            case "product.monthlyCharge.inVat":
                return RANDOM.nextInt(1000) + 500;
            case "product.monthlyDiscount.inVat":
                return RANDOM.nextInt(500);
            case "product.monthlyDiscount.durationInMonths":
                return RANDOM.nextInt(12) + 1;
            case "contractPeriod":
                return RANDOM.nextInt(36) + 1;
            case "isPrepaid":
                return false;
            case "msisdn":
                return String.format("+47%d", RANDOM.nextInt(90000000) + 10000000);
            case "imei":
                return String.format("+47%d", RANDOM.nextInt(90000000) + 10000000);
            case "icc":
                return String.valueOf(RANDOM.nextInt(900000000) + 1000);
            case "orderer.identificationMethodType":
                return "Kjent av selger";
            case "product.offeringDescriptionLong":
                return Product.generateRandomProductDescription();
            case "order.orderId":
                return String.valueOf(RANDOM.nextInt(900000) + 1000);
            case "dealer.name":
                return "Mcdonalds";
            case "billing.invoiceType":
                return "EE";
            case "isPortingSubscription":
                return RANDOM.nextBoolean() ? "true" : "false";
            case "product.disclaimers":
                return "product.disclaimers";
            case "legal.signature":
                return "legal.signature";
            case "user.communicationLevel":
                return new String[]{"NoTelemarketingCommunication", "NoEmailTelemarketingCommunication", "NoCommunication", "NoSMS_MMS_Communication", "NoSMS_MMS_TelemarketingCommunication", "AllCommunication", "NoEmailCommunication", "NoEmail_SMS_MMS_Communication", "NoEmail_SMS_MMS_TelemarketingCommunication"}[new Random().nextInt(9)];

            case "user.directoryListingLevel":
                return new String[]{"Z", "H", "X", "W"}[new Random().nextInt(4)];
            case "credit.decision":
                return new String[]{"NONE", "CONTROL", "OK", "DENIED", "MANUAL_OK", "MANUAL_DENIED", "OK_WITH_DEPOSIT", "OK_WITH_CREDITLIMIT", "CREDITCHECK_DISABLED", "MANUAL_CONTROL_WITH_ADDRESS_DOCUMENTATION", "MANUAL_CONTROL_WITH_INCOME_AND_ADDRESS_DOCUMENTATION", "MANUAL_OK_WITH_DOCUMENTATION", "MANUAL_OK_WITH_INCOME_DOCUMENTATION", "MANUAL_CONTROL_WITH_INCOME_DOCUMENTATION", "MANUAL_OK_WITH_DEPOSIT"}[new Random().nextInt(15)];


        }
        System.out.printf("unresolved attribute: %s \n", attributeName);
        return String.format("#%s#\n", attributeName);
    }

    public List<IPlaceholder> getAttributeCollection(String attributeName) {
        //System.out.printf("attribute collection: %s\n ", attributeName);
        switch (attributeName) {
            case "accounts":
            case "product.disclaimers":
            case "documentList.existingDocument":
                //accountList.add(new Account());
                return new ArrayList<>();
            case "subscriptions":
                List<IPlaceholder> subscritionList = new ArrayList<>();
                subscritionList.add(new Subscription());
                return subscritionList;
            case "services":
                List<IPlaceholder> serviceList = new ArrayList<>();
                for (int i = 0; i < RANDOM.nextInt(100) + 10; i++) {
                    serviceList.add(new Product(ServiceClass.DISCOUNT , ServiceClass.SERVICE ));
                }
                return serviceList;
        }
        System.out.printf("unresoved attribute collection: %s\n ", attributeName);
        return List.of();
    }

    public String getAttributeDateValue(String attributeName, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime randomDate = LocalDateTime.now()
                .minusDays((long) (Math.random() * 3650))
                .minusSeconds((long) (Math.random() * 86400));
        return randomDate.format(formatter);
    }

    public IPlaceholder getSignatoryParty(String attributeName) {
        //System.out.printf("signatory party: %s\n ", attributeName);
        return new LegalOwnerParty();
    }


}
