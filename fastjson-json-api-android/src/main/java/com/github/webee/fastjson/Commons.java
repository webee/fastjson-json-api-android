package com.github.webee.fastjson;

import com.github.webee.json.JSONType;
import com.github.webee.json.Utils;

import java.util.Map;

/**
 * Created by webee on 16/11/27.
 */

public final class Commons {
    public static Object resolveValue(Object value) {
        JSONType t = Utils.getType(value);
        if (t != null) {
            return Utils.resolveValue(value, t);
        }
        if (value instanceof com.alibaba.fastjson.JSONObject) {
            return Utils.objectToMap(new JSONObject((com.alibaba.fastjson.JSONObject) value));
        } else if (value instanceof com.alibaba.fastjson.JSONArray) {
            return Utils.arrayToObjects(new JSONArray((com.alibaba.fastjson.JSONArray) value));
        } else if (value instanceof Map) {
            return Utils.objectToMap((Map<String, Object>) value);
        } else if (value instanceof Object[]) {
            return Utils.arrayToObjects((Object[]) value);
        }
        return null;
    }

    public static JSONType getType(Object value) {
        JSONType t = Utils.getType(value);
        if (t != null) {
            return t;
        }
        if (value instanceof com.alibaba.fastjson.JSONObject) {
            return JSONType.Object;
        } else if (value instanceof com.alibaba.fastjson.JSONArray) {
            return JSONType.Array;
        } else if (value instanceof Map) {
            return JSONType.Object;
        } else if (value instanceof Object[]) {
            return JSONType.Array;
        }
        return null;
    }
}
