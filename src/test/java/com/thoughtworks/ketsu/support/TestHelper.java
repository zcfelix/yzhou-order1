package com.thoughtworks.ketsu.support;

import java.util.*;

public class TestHelper {
    private static int auto_increment_key = 1;

    public static Map<String, Object> userMap(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
    }

    public static Map<String, Object> productMap(String name, String desc, double price) {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("desc", desc);
            put("price", price);
        }};
    }

    public static Map<String, Object> orderMap(String name, int productId) {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("address", "beijing");
            put("phone", "15184452287");
//            List<Map<String, Object>> items = new ArrayList<>();
//            items.add(new HashMap<String, Object>() {{
//                put("id", productId);
//                put("quantity", 200);
//            }});
//            put("items", items);
        }};
    }
}
