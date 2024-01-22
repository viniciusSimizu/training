package com.linkedrh.training.lib.helpers;

import com.linkedrh.training.lib.enums.ErrorEnum;

import java.util.HashMap;
import java.util.Map;

public class ErrorHelper {

    public static Map<String, Object> createMessage(ErrorEnum type, Object message) {
        Map<String, Object> response = new HashMap<>();

        switch (type) {
            case EXCEPTION:
                response.put("Exception", message);
                break;
            case VALIDATION:
                response.put("Validation", message);
                break;
            case AUTHORIZATION:
                response.put("Authorization", "Invalid header 'Authorization' to access endpoint");
                break;
            default:
                response.put("Error", "Something went wrong");
                break;
        }

        return response;
    }
}
