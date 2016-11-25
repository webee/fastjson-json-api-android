package com.github.webee.fastjson;

import com.github.webee.json.*;

/**
 * Created by webee on 16/11/25.
 */

public class JSONArray implements com.github.webee.json.JSONArray {
    com.alibaba.fastjson.JSONArray jsonArray;

    public JSONArray(com.alibaba.fastjson.JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public int size() {
        return jsonArray.size();
    }

    @Override
    public JSONType getType(int index) {
        if (index >= jsonArray.size()) {
            return null;
        }

        return Utils.getType(jsonArray.get(index));
    }

    @Override
    public boolean isNull(int index) {
        return jsonArray.get(index) == null;
    }

    @Override
    public Object get(int index) {
        return jsonArray.get(index);
    }

    @Override
    public Boolean getBoolean(int index) {
        return jsonArray.getBoolean(index);
    }

    @Override
    public Integer getInteger(int index) {
        return jsonArray.getInteger(index);
    }

    @Override
    public Long getLong(int index) {
        return jsonArray.getLong(index);
    }

    @Override
    public Double getDouble(int index) {
        return jsonArray.getDouble(index);
    }

    @Override
    public String getString(int index) {
        return jsonArray.getString(index);
    }

    @Override
    public com.github.webee.json.JSONArray getArray(int index) {
        return new JSONArray(jsonArray.getJSONArray(index));
    }

    @Override
    public com.github.webee.json.JSONObject getObject(int index) {
        return new JSONObject(jsonArray.getJSONObject(index));
    }

    @Override
    public boolean push(Object value) {
        return jsonArray.add(Utils.resolveValue(value));
    }

    @Override
    public Object set(int index, Object value) {
        return jsonArray.set(index, Utils.resolveValue(value));
    }

    @Override
    public Object remove(int index) {
        return jsonArray.remove(index);
    }

    @Override
    public String toJSONString() {
        return jsonArray.toJSONString();
    }
}
