package com.thoughtworks.ketsu.web.validator;

import com.thoughtworks.ketsu.domain.product.Product;

import java.util.Map;

public class ProductValidator implements ValidateCheck {
    @Override
    public boolean isValidate(Map<String, Object> info) {
        if (info.get("name").toString().trim().isEmpty())
            return false;
        if (info.get("desc").toString().trim().isEmpty())
            return false;
        if (info.get("price").toString().trim().isEmpty())
            return false;
        return true;
    }
}
