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
import java.util.Map;
import java.util.Set;

/**
 * Created by webee on 16/11/25.
 */

public class JSONObject implements com.github.webee.json.JSONObject {
    com.alibaba.fastjson.JSONObject jsonObject;

    public JSONObject(com.alibaba.fastjson.JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public Set<String> keySet() {
        return jsonObject.keySet();
    }

    @Override
    public boolean hasKey(String key) {
        return jsonObject.containsKey(key);
    }

    @Override
    public JSONType getType(String key) {
        return Commons.getType(jsonObject.get(key));
    }

    @Override
    public boolean isNull(String key) {
        return jsonObject.get(key) == null;
    }

    @Override
    public Map<String, Object> get() {
        return Utils.objectToMap(this);
    }

    @Override
    public Object get(String key) {
        return Commons.resolveValue(jsonObject.get(key));
    }

    public Boolean getBoolean(String key) {
        return jsonObject.getBoolean(key);
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return jsonObject.getBigDecimal(key);
    }

    @Override
    public BigInteger getBigInteger(String key) {
        return jsonObject.getBigInteger(key);
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

    @Override
    public String getString(String key) {
        return jsonObject.getString(key);
    }

    @Override
    public com.github.webee.json.JSONArray getArray(String key) {
        return new JSONArray(jsonObject.getJSONArray(key));
    }

    @Override
    public com.github.webee.json.JSONObject getObject(String key) {
        return new JSONObject(jsonObject.getJSONObject(key));
    }

    @Override
    public String toJSONString() {
        SerializeWriter out = new SerializeWriter((Writer) null, JSON.DEFAULT_GENERATE_FEATURE, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
        try {
            new JSONSerializer(out, SerializeConfig.globalInstance).write(jsonObject);
            return out.toString();
        } finally {
            out.close();
        }
    }
}
