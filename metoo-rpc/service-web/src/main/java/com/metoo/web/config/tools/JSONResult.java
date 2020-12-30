package com.metoo.web.config.tools;

import org.json.JSONObject;

import java.io.Serializable;

public class JSONResult implements Serializable {
    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("code", status);
            put("msg", message);
            put("data", result);
        }};
        return jsonObject.toString();
    }
}
