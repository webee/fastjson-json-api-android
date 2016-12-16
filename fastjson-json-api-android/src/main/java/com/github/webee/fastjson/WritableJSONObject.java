package com.github.webee.fastjson;

import com.github.webee.json.Utils;

/**
 * Created by webee on 16/12/16.
 */

public class WritableJSONObject extends JSONObject implements com.github.webee.json.WritableJSONObject {
    public WritableJSONObject(com.alibaba.fastjson.JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public Object set(String key, Object value) {
        return jsonObject.put(key, Utils.resolveValue(value));
    }

    @Override
    public Object remove(String key) {
        return jsonObject.remove(key);
    }
}
