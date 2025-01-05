package com.teliasonera.mts.mvelsimple.placeholders;

import com.teliasonera.mts.mvelsimple.imported.ProductAction;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Product extends Document {
    private static final Random RANDOM = new Random();
    private ServiceClass serviceClass = ServiceClass.SERVICE;
    private String name;
    private int monthlyChargeInVat;
    private final MonthlyDiscount monthlyDiscount = new MonthlyDiscount();
    private int lifeTime;
    private ProductAction action;

    public Product() {
        serviceClass = ServiceClass.SERVICE;
        name = SERVICE_NAMES.get(RANDOM.nextInt(SERVICE_NAMES.size()));
        monthlyChargeInVat = RANDOM.nextInt(200) - 100;
        lifeTime = RANDOM.nextInt(24) == 0 ? 0 : RANDOM.nextInt(24) + 1;
        action = getRandomAction();
    }

    public String getName() {
        return name;
    }

    public int getMonthlyChargeInVat() {
        return monthlyChargeInVat;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public MonthlyDiscount getMonthlyDiscount() {
        return monthlyDiscount;
    }

    public ServiceClass getServiceClass() {
        return serviceClass;
    }

    public Product(ServiceClass... serviceClasses) {
        if (serviceClasses.length == 0) {
            throw new IllegalArgumentException("At least one ServiceClass must be provided.");
        }
        ServiceClass selectedServiceClass = serviceClasses[RANDOM.nextInt(serviceClasses.length)];
        this.serviceClass = selectedServiceClass;

        switch (selectedServiceClass) {
            case DISCOUNT:
                name = DISCOUNT_SERVICE_NAMES.get(RANDOM.nextInt(DISCOUNT_SERVICE_NAMES.size()));
                monthlyChargeInVat = -RANDOM.nextInt(99) - 1;
                lifeTime = RANDOM.nextInt(13);
                action = ProductAction.ADD;
                break;
            case HARDWARE:
                name = HARDWARE.get(RANDOM.nextInt(HARDWARE.size()));
                monthlyChargeInVat = RANDOM.nextInt(100) + 1;
                action = ProductAction.ADD;
                break;
            case SERVICE:
                name = SERVICE_NAMES.get(RANDOM.nextInt(SERVICE_NAMES.size()));
                monthlyChargeInVat = RANDOM.nextInt(100) + 1;
                lifeTime = 0;
                action = ProductAction.ADD;
                break;
            default:
                throw new IllegalArgumentException("Unsupported ServiceClass: " + selectedServiceClass);
        }
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "user.fullName":
            case "legalOwner.fullName":
                return getRandomElement(ENGLISH_NAMES);
            case "product.current":
                return Subscription.product;
            case "insuranceScreenPriceAfter.inVat":
                return RANDOM.nextInt(5000) + 1000; // between 1000 and 5999
            case "msisdn":
                return String.format("+47%d", RANDOM.nextInt(90000000) + 10000000);
            case "flexibleSwitch.includedInsuranceInVat":
                return RANDOM.nextInt(500) + 100;   // between 100 and 599
            case "imei":
                return 22334455;
            case "flexibleSwitch.includedInsuranceExVat":
                return RANDOM.nextInt(500) + 50;    // between 50 and 549

            default:
                System.out.printf("Unresolved service's attribute: %s \n", attributeName);
                return String.format("Service's attribute value: %s", attributeName);
        }
    }

    public Object get(String attributeName) {
        if ("name".equals(attributeName)) {
            return name;
        } else if ("action".equals(attributeName)) {
            return action.toString();
        } else if ("monthlyCharge.inVat".equals(attributeName) || "product.monthlyCharge.inVat".equals(attributeName)) {
            return monthlyChargeInVat;
        } else if ("lifetimeInMonths".equals(attributeName)) {
            return lifeTime;
        } else if ("flexibleSwitch.isTeliaFinance".equals(attributeName)) {
            return "false";
        } else if ("flexibleSwitch.numberOfInstallments".equals(attributeName)) {
            return RANDOM.nextInt(24) + 1; // 1..24
        } else if ("flexibleSwitch.installmentAmountInVat".equals(attributeName)) {
            return RANDOM.nextInt(1000) + 100; // 100..1099
        } else if ("customerRelevant".equals(attributeName)) {
            return RANDOM.nextBoolean() ? "true" : "false";
        } else if ("insuranceClass".equals(attributeName)) {
            return "XL";
        } else if ("category".equals(attributeName)) {
            // You could decide "GENERAL" for product vs. "ADDON" for service, etc.
            return RANDOM.nextBoolean() ? "GENERAL" : "ADDON";
        } else if ("isSecretNumber".equals(attributeName)) {
            return RANDOM.nextBoolean() ? "true" : "false";
        } else {
            System.out.printf("Unresolved service's get value: %s \n", attributeName);
            return String.format("Service's get value: %s", attributeName);
        }
    }

    public static final List<String> HARDWARE = Arrays.asList(
            "Smartphone Galaxy S23", "Smartphone Galaxy S24", "Smartphone Galaxy S25",
            "Tablet Galaxy Tab S8", "Wi-Fi Router X100", "4G LTE Modem",
            "Mobile Hotspot Pro", "Home Security Camera Kit",
            "VoIP Phone Adapter", "Telecom Satellite Receiver"
    );


    public static final List<String> SERVICE_NAMES = Arrays.asList(
            "Data Booster", "International Calling Plan", "Music Streaming",
            "TV Add-on", "Family Roaming Package", "Additional Insurance",
            "Screen Insurance", "Cloud Storage Upgrade", "Backup Service",
            "Anti-Virus Protection", "Premium Tech Support"
    );

    public static final List<String> DISCOUNT_SERVICE_NAMES = Arrays.asList(
            "Loyalty Discount", "Promo Code Discount", "Seasonal Offer",
            "Referral Bonus", "Early Payment Discount", "Bundle Discount",
            "Student Discount", "Military Discount", "First-Time Customer Discount",
            "Trade-In Credit", "Volume Purchase Discount"
    );

    public static ProductAction getRandomAction() {
        ProductAction[] actions = ProductAction.values();
        int[] weights = {5, 4, 1, 1, 1, 1, 1};  // adjust as needed
        int totalWeight = 0;

        for (int weight : weights) {
            totalWeight += weight;
        }

        int randomIndex = RANDOM.nextInt(totalWeight);
        int cumulativeWeight = 0;

        for (int i = 0; i < weights.length; i++) {
            cumulativeWeight += weights[i];
            if (randomIndex < cumulativeWeight) {
                return actions[i];
            }
        }

        // Fallback, though we rarely get here
        return actions[0];
    }

    public static String generateRandomProductDescription() {
        String[] adjectives = {
                "innovative", "advanced", "reliable", "high-quality", "durable", "sleek", "efficient", "user-friendly",
                "cutting-edge", "state-of-the-art", "robust", "powerful", "versatile", "exceptional", "outstanding"
        };
        String[] nouns = {
                "device", "solution", "platform", "product", "tool", "technology", "system", "software", "appliance",
                "gadget", "equipment", "instrument", "hardware", "service"
        };
        String[] features = {
                "designed for optimal performance", "engineered for maximum efficiency", "packed with the latest features",
                "designed with modern technology", "perfect for both personal and professional use",
                "offers seamless integration with existing systems", "equipped with a cutting-edge interface",
                "features intuitive controls for easy operation", "tailored to meet the needs of modern users",
                "offers exceptional performance under demanding conditions", "incorporates advanced security protocols"
        };
        String[] benefits = {
                "improve your workflow and productivity", "streamline operations for greater efficiency",
                "simplify complex tasks with ease", "boost overall performance and reduce downtime",
                "enhance user experience with smooth functionality", "enable faster decision-making with real-time data",
                "save both time and money by optimizing resources", "deliver unparalleled reliability in every task",
                "support your business growth and scalability", "improve collaboration and communication across teams",
                "provide peace of mind with robust data protection", "empower teams to achieve their goals faster"
        };
        String[] usageContexts = {
                "in the office", "in the home", "in any business environment", "across various industries",
                "in both small and large enterprises", "in tech-driven workplaces", "for both personal and professional use",
                "in remote work setups", "in high-performance environments", "in environments that demand high reliability"
        };
        String[] closingStatements = {
                "With its unparalleled capabilities, this product is a must-have for anyone looking to stay ahead of the curve.",
                "Investing in this product means investing in your future success.",
                "Whether you're a professional or a hobbyist, this product is sure to exceed your expectations.",
                "This product is built to last and designed to adapt to your growing needs.",
                "Join the growing number of satisfied customers who trust this product for its consistent performance."
        };

        Random random = new Random();

        // Randomly select elements from each category
        String adjective = adjectives[random.nextInt(adjectives.length)];
        String noun = nouns[random.nextInt(nouns.length)];
        String feature = features[random.nextInt(features.length)];
        String benefit = benefits[random.nextInt(benefits.length)];
        String usageContext = usageContexts[random.nextInt(usageContexts.length)];
        String closingStatement = closingStatements[random.nextInt(closingStatements.length)];

        // Construct a longer, detailed product description
        return "Introducing the " + adjective + " " + noun + ", " +
                "a revolutionary product " + feature + ". " +
                "Perfect for use " + usageContext + ", this product is designed to " + benefit + ". " +
                "It provides a unique combination of " + feature + " and " + benefit + ", making it the ideal solution for modern-day needs. " +
                closingStatement;
    }
}
