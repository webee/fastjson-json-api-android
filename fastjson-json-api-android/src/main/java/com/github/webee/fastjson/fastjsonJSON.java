package com.github.webee.fastjson;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by webee on 16/11/25.
 */

public class fastjsonJSON implements com.github.webee.json.JSON {
    @Override
    public com.github.webee.json.JSONObject newObject() {
        return new JSONObject(new com.alibaba.fastjson.JSONObject());
    }

    @Override
    public com.github.webee.json.JSONObject newObject(Map<String, Object> map) {
        return new JSONObject(new com.alibaba.fastjson.JSONObject(map));
    }

    @Override
    public com.github.webee.json.JSONArray newArray() {
        return new JSONArray(new com.alibaba.fastjson.JSONArray());
    }

    @Override
    public com.github.webee.json.JSONArray newArray(Object[] array) {
        return new JSONArray(new com.alibaba.fastjson.JSONArray(Arrays.asList(array)));
    }

    @Override
    public Object parse(String text) {
        Object value = com.alibaba.fastjson.JSON.parse(text);
        if (value instanceof com.alibaba.fastjson.JSONObject) {
            return new JSONObject((com.alibaba.fastjson.JSONObject) value);
        } else if (value instanceof com.alibaba.fastjson.JSONArray) {
            return new JSONArray((com.alibaba.fastjson.JSONArray) value);
        }
        return value;
    }

    @Override
    public com.github.webee.json.JSONObject parseObject(String text) {
        return new JSONObject(com.alibaba.fastjson.JSON.parseObject(text));
    }

    @Override
    public com.github.webee.json.JSONArray parseArray(String text) {
        return new JSONArray(com.alibaba.fastjson.JSON.parseArray(text));
    }
}
