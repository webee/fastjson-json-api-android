package com.github.webee.fastjson;

/**
 * Created by webee on 16/11/25.
 */

public class fastjsonJSON implements com.github.webee.json.JSON {
    @Override
    public com.github.webee.json.JSONObject newObject() {
        return new JSONObject(new com.alibaba.fastjson.JSONObject());
    }

    @Override
    public com.github.webee.json.JSONArray newArray() {
        return new JSONArray(new com.alibaba.fastjson.JSONArray());
    }

    @Override
    public Object parse(String text) {
        return com.alibaba.fastjson.JSON.parse(text);
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
