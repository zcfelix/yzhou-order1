package com.thoughtworks.ketsu.web.exception;

import java.util.ArrayList;
import java.util.List;

public class InvalidParameterException extends RuntimeException {

    private List<InvalidParameterInfo> invalidParameterInfos;

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(Exception e) {
        super(e);
    }

    public InvalidParameterException(List<String> invalidParameter) {
        invalidParameterInfos = new ArrayList<>();
        for (int i = 0; i < invalidParameter.size(); i++) {
            invalidParameterInfos.add(new InvalidParameterInfo(invalidParameter.get(i)));
        }
    }
}
