package com.android.cmpe220.farm2home.demo.constant;

import com.android.cmpe220.farm2home.demo.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class Constant {
    public static final List<Integer> QUANTITY_LIST = new ArrayList<Integer>();

    static {
        for (int i = 1; i < 11; i++) QUANTITY_LIST.add(i);
    }

    public static final Product PRODUCT1 = new Product(1, "Tomatoes", BigDecimal.valueOf(9.99), "Excellent source of vitamin C, as well as antioxidants that may prevent cancer, such as lycopene. Lycopene promotes overall mental and physical health. Good source of potassium. Antioxidant and anti-cancer.");
    public static final Product PRODUCT2 = new Product(2, "Carrots", BigDecimal.valueOf(7.25), "Excellent source of vitamins A, B, and C. Rich in beta-carotene and carotenoids; they help protect the body from cancer, cardiac disease, and cataract and macular degeneration. They also contain iron, calcium, potassium, and natural sodium.");
    public static final Product PRODUCT3 = new Product(3, "Potatoes", BigDecimal.valueOf(12.25), "Potatoes are a very good source of vitamin B6 and a good source of potassium, copper, vitamin C, manganese, phosphorus, niacin, dietary fiber, and pantothenic acid.");
    public static final Product PRODUCT4 = new Product(4, "Okra", BigDecimal.valueOf(13.00), "Source of vitamin A, Thiamin, B6. Okra benefits include detoxification, diabetic control, stronger bones etc.");
    public static final Product PRODUCT5 = new Product(5, "Egg Plant", BigDecimal.valueOf(13.25), "Low in calories and sodium, eggplants have a phytochemical called monoterpene that may help prevent cancer cell growth.");
    public static final Product PRODUCT6 = new Product(6, "Turnips", BigDecimal.valueOf(4.25), "Turnip is a great source of minerals, antioxidants, and dietary fiber. High in vitamin C");
    public static final Product PRODUCT7 = new Product(7, "Mushroom", BigDecimal.valueOf(5.25), "Mushrooms are good sources of selenium, an antioxidant mineral, as well as copper, niacin, potassium and phosphorous.");
    public static final Product PRODUCT8 = new Product(8, "Cauliflower", BigDecimal.valueOf(11.25), "One serving of cauliflower contains 77 percent of the recommended daily value of vitamin C. It's also a good source of vitamin K, protein, thiamin, riboflavin, niacin, magnesium, phosphorus, fiber, vitamin B6, folate, pantothenic acid, potassium, and manganese.");
    public static final Product PRODUCT9 = new Product(9, "Squash", BigDecimal.valueOf(10.25), "Winter varieties are an excellent source of vitamin A; Vitamin A keeps eyes and skin healthy and helps to protect against infections. Rich in potassium, an electrolyte which helps prevent muscle cramps and keeps blood pressure low");
    public static final Product PRODUCT10 = new Product(10, "Cabbage", BigDecimal.valueOf(9.75), "High in sulfur, which purifies the blood, and one of very few vegetables that contains vitamin E. Antibacterial, antioxidant, and an anti-inflammatory.");

    public static final List<Product> PRODUCT_LIST = new ArrayList<Product>();

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
}
