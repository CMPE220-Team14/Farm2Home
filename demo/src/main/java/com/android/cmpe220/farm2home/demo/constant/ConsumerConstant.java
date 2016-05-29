package com.android.cmpe220.farm2home.demo.constant;

import com.android.cmpe220.farm2home.demo.model.ConsumerProduct;
import com.android.cmpe220.farm2home.demo.model.ConsumerFarm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ConsumerConstant {
    public static final List<Integer> QUANTITY_LIST = new ArrayList<Integer>();

    static {
        for (int i = 1; i < 11; i++) QUANTITY_LIST.add(i);
    }

    public static final ConsumerProduct PRODUCT1 = new ConsumerProduct(1, "Tomatoes", BigDecimal.valueOf(9.99), "Excellent source of vitamin C, as well as antioxidants that may prevent cancer, such as lycopene. Lycopene promotes overall mental and physical health. Good source of potassium. Antioxidant and anti-cancer.");
    public static final ConsumerProduct PRODUCT2 = new ConsumerProduct(2, "Carrots", BigDecimal.valueOf(7.25), "Excellent source of vitamins A, B, and C. Rich in beta-carotene and carotenoids; they help protect the body from cancer, cardiac disease, and cataract and macular degeneration. They also contain iron, calcium, potassium, and natural sodium.");
    public static final ConsumerProduct PRODUCT3 = new ConsumerProduct(3, "Potatoes", BigDecimal.valueOf(12.25), "Potatoes are a very good source of vitamin B6 and a good source of potassium, copper, vitamin C, manganese, phosphorus, niacin, dietary fiber, and pantothenic acid.");
    public static final ConsumerProduct PRODUCT4 = new ConsumerProduct(4, "Okra", BigDecimal.valueOf(13.00), "Source of vitamin A, Thiamin, B6. Okra benefits include detoxification, diabetic control, stronger bones etc.");
    public static final ConsumerProduct PRODUCT5 = new ConsumerProduct(5, "Egg Plant", BigDecimal.valueOf(13.25), "Low in calories and sodium, eggplants have a phytochemical called monoterpene that may help prevent cancer cell growth.");
    public static final ConsumerProduct PRODUCT6 = new ConsumerProduct(6, "Turnips", BigDecimal.valueOf(4.25), "Turnip is a great source of minerals, antioxidants, and dietary fiber. High in vitamin C");
    public static final ConsumerProduct PRODUCT7 = new ConsumerProduct(7, "Mushroom", BigDecimal.valueOf(5.25), "Mushrooms are good sources of selenium, an antioxidant mineral, as well as copper, niacin, potassium and phosphorous.");
    public static final ConsumerProduct PRODUCT8 = new ConsumerProduct(8, "Cauliflower", BigDecimal.valueOf(11.25), "One serving of cauliflower contains 77 percent of the recommended daily value of vitamin C. It's also a good source of vitamin K, protein, thiamin, riboflavin, niacin, magnesium, phosphorus, fiber, vitamin B6, folate, pantothenic acid, potassium, and manganese.");
    public static final ConsumerProduct PRODUCT9 = new ConsumerProduct(9, "Squash", BigDecimal.valueOf(10.25), "Winter varieties are an excellent source of vitamin A; Vitamin A keeps eyes and skin healthy and helps to protect against infections. Rich in potassium, an electrolyte which helps prevent muscle cramps and keeps blood pressure low");
    public static final ConsumerProduct PRODUCT10 = new ConsumerProduct(10, "Cabbage", BigDecimal.valueOf(9.75), "High in sulfur, which purifies the blood, and one of very few vegetables that contains vitamin E. Antibacterial, antioxidant, and an anti-inflammatory.");

    public static final List<ConsumerProduct> PRODUCT_LIST = new ArrayList<ConsumerProduct>();

    static {
        PRODUCT_LIST.add(PRODUCT1);
        PRODUCT_LIST.add(PRODUCT2);
        PRODUCT_LIST.add(PRODUCT3);
        PRODUCT_LIST.add(PRODUCT4);
        PRODUCT_LIST.add(PRODUCT5);
        PRODUCT_LIST.add(PRODUCT6);
        PRODUCT_LIST.add(PRODUCT7);
        PRODUCT_LIST.add(PRODUCT8);
        PRODUCT_LIST.add(PRODUCT9);
        PRODUCT_LIST.add(PRODUCT10);
    }

    public static final ConsumerFarm FARM1 = new ConsumerFarm(1, "Farm1", BigDecimal.valueOf(1.4));
    public static final ConsumerFarm FARM2 = new ConsumerFarm(2, "Farm2", BigDecimal.valueOf(10.3));
    public static final ConsumerFarm FARM3 = new ConsumerFarm(3, "Farm3", BigDecimal.valueOf(4.8));
    public static final ConsumerFarm FARM4 = new ConsumerFarm(4, "Farm4", BigDecimal.valueOf(8.2));
    public static final ConsumerFarm FARM5 = new ConsumerFarm(5, "Farm5", BigDecimal.valueOf(3.7));

    public static final List<ConsumerFarm> FARM_LIST = new ArrayList<ConsumerFarm>();

    static {
        FARM_LIST.add(FARM1);
        FARM_LIST.add(FARM2);
        FARM_LIST.add(FARM3);
        FARM_LIST.add(FARM4);
        FARM_LIST.add(FARM5);
    }
}
