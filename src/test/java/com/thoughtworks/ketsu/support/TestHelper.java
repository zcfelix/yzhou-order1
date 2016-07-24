package com.thoughtworks.ketsu.support;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    private static int auto_increment_key = 1;

    public static Map<String, Object> userMap(int id, String name) {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
        }};
    }

    public static Map<String, Object> productMap(int id, String name, String desc, double price) {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
            put("desc", desc);
            put("price", price);
        }};
    }
}
