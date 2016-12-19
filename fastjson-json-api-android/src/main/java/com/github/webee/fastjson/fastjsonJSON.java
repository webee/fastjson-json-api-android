package com.github.webee.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Writer;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by webee on 16/11/25.
 */

public class fastjsonJSON implements com.github.webee.json.JSON {
    @Override
    public com.github.webee.json.WritableJSONObject newObject() {
        return new WritableJSONObject(new com.alibaba.fastjson.JSONObject());
    }

    @Override
    public com.github.webee.json.WritableJSONObject newObject(Map<String, Object> map) {
        return new WritableJSONObject(new com.alibaba.fastjson.JSONObject(map));
    }

    @Override
    public com.github.webee.json.WritableJSONArray newArray() {
        return new WritableJSONArray(new com.alibaba.fastjson.JSONArray());
    }

    @Override
    public com.github.webee.json.WritableJSONArray newArray(Object[] array) {
        return new WritableJSONArray(new com.alibaba.fastjson.JSONArray(Arrays.asList(array)));
    }

    @Override
    public String serialize(Object value) {
        SerializeWriter out = new SerializeWriter((Writer) null, JSON.DEFAULT_GENERATE_FEATURE, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
        try {
            new JSONSerializer(out, SerializeConfig.globalInstance).write(value);
            return out.toString();
        } finally {
            out.close();
        }
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
