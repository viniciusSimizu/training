package com.linkedrh.training.lib.helpers;

import com.linkedrh.training.lib.enums.ErrorEnum;

import java.util.HashMap;
import java.util.Map;

public class ErrorHelper {

    public static Map<String, Object> createMessage(ErrorEnum type, Object message) {
        Map<String, Object> response = new HashMap<>();

        switch (type) {
            case EXCEPTION:
                response.put("exception", message);
                break;
            case VALIDATION:
                response.put("validation", message);
            default:
                response.put("error", "Something went wrong");
                break;
        }

        return response;
    }
}
