package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserId;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.domain.user.UserRole;

import java.util.HashMap;
import java.util.Map;

//import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.description;

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
