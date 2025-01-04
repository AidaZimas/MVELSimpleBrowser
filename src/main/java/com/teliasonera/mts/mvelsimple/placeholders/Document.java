package com.teliasonera.mts.mvelsimple.placeholders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document implements IPlaceholder {

    public boolean hasAttributeValue(String attributeName) {
        System.out.printf("check boolean attribute: %s \n", attributeName);
        return true;
    }

    public Object getAttributeValue(String attributeName) {
        System.out.printf("attribute value: %s \n", attributeName);
        switch (attributeName) {
            case "legalOwner.fullName":
            case "user.fullName":
            case "billing.fullName":
                return "Smith Altair";
            case "legalOwner.fullStreet":
            case "user.fullStreet":
            case "billing.fullStreet":
                return "North sunset street";
            case "legalOwner.zip":
            case "user.zip":
            case "billing.zip":
                return "7080";
            case "legalOwner.city":
            case "user.city":
            case "billing.city":
                return "Heimdal";
            case "user.contactPhone":
                return "47160088";
            case "user.email":
                return "mvel_template_test@telia.no";
            case "product.monthlyCharge.inVat":
                return 1000L;
            case "product.monthlyDiscount.inVat":
                return 522L;
            case "product.monthlyDiscount.durationInMonths":
                return 3L;
            case "contractPeriod":
                return 1;
            case "isPrepaid":
                return false;
            case "msisdn":
                return "+4791234567";

        }
        return String.format("value: %s\n", attributeName);
    }

    public List<IPlaceholder> getAttributeCollection(String attributeName) {
        System.out.printf("attribute collection: %s\n ", attributeName);
        if (attributeName.equals("accounts")) {
            List<IPlaceholder> accountList = new ArrayList<>();
            //accountList.add(new Account());
            return accountList;
        } else if (attributeName.equals("subscriptions")) {
            List<IPlaceholder> subscritionList = new ArrayList<>();
            subscritionList.add(new Account());
            return subscritionList;
        } else if (attributeName.equals("services")) {
            List<IPlaceholder> serviceList = new ArrayList<>();
            Service service1 = new Service();
            serviceList.add(service1);
            serviceList.add(new Service());
            serviceList.add(new Service());
            serviceList.add(new Service());
            serviceList.add(new Service());
            return serviceList;
        }
        return List.of();
    }

    public String getAttributeDateValue(String attributeName, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime randomDate = LocalDateTime.now().minusDays((long) (Math.random() * 3650)).minusSeconds((long) (Math.random() * 86400));
        return randomDate.format(formatter);
    }

    public IPlaceholder getSignatoryParty(String attributeName) {
        System.out.printf("signatory party: %s\n ", attributeName);
        if (attributeName.equals("legal_owner")) {
            return new LegalOwnerParty();
        } else {
            return new LegalOwnerParty();
        }
    }

}
