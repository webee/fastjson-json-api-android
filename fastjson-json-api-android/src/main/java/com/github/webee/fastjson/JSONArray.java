package com.github.webee.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.webee.json.JSONType;
import com.github.webee.json.Utils;

import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

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
        return Commons.getType(jsonArray.get(index));
    }

    @Override
    public boolean isNull(int index) {
        return jsonArray.get(index) == null;
    }

    @Override
    public Object[] get() {
        return Utils.arrayToObjects(this);
    }

    @Override
    public Object get(int index) {
        return Commons.resolveValue(jsonArray.get(index));
    }

    @Override
    public Boolean getBoolean(int index) {
        return jsonArray.getBoolean(index);
    }

    @Override
    public BigDecimal getBigDecimal(int index) {
        return jsonArray.getBigDecimal(index);
    }

    @Override
    public BigInteger getBigInteger(int index) {
        return jsonArray.getBigInteger(index);
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
    public String toJSONString() {
        SerializeWriter out = new SerializeWriter((Writer) null, JSON.DEFAULT_GENERATE_FEATURE, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
        try {
            new JSONSerializer(out, SerializeConfig.globalInstance).write(jsonArray);
            return out.toString();
        } finally {
            out.close();
        }
    }
}
