package com.github.webee.fastjson;

import com.github.webee.json.JSONType;
import com.github.webee.json.Utils;

import java.util.Set;

/**
 * Created by webee on 16/11/25.
 */

public class JSONObject implements com.github.webee.json.JSONObject {
    com.alibaba.fastjson.JSONObject jsonObject;

    public JSONObject(com.alibaba.fastjson.JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Set<String> keySet() {
        return jsonObject.keySet();
    }

    public boolean hasKey(String key) {
        return jsonObject.containsKey(key);
    }

    public JSONType getType(String key) {
        return Utils.getType(jsonObject.get(key));
    }

    public boolean isNull(String key) {
        return jsonObject.get(key) == null;
    }

    @Override
    public Object get(String key) {
        return jsonObject.get(key);
    }

    public Boolean getBoolean(String key) {
        return jsonObject.getBoolean(key);
    }

    @Override
    public Integer getInteger(String key) {
        return jsonObject.getInteger(key);
    }

    @Override
    public Long getLong(String key) {
        return jsonObject.getLong(key);
    }

    @Override
    public Double getDouble(String key) {
        return jsonObject.getDouble(key);
    }

    public String getString(String key) {
        return jsonObject.getString(key);
    }

    public com.github.webee.json.JSONArray getArray(String key) {
        return new JSONArray(jsonObject.getJSONArray(key));
    }

    public com.github.webee.json.JSONObject getObject(String key) {
        return new JSONObject(jsonObject.getJSONObject(key));
    }

    public Object set(String key, Object value) {
        return jsonObject.put(key, Utils.resolveValue(value));
    }

    public Object remove(String key) {
        return jsonObject.remove(key);
    }

    @Override
    public String toJSONString() {
        return jsonObject.toJSONString();
    }
}
