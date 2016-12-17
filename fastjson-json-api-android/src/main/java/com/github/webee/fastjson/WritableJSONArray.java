package com.github.webee.fastjson;

import com.github.webee.json.Utils;

/**
 * Created by webee on 16/12/16.
 */

public class WritableJSONArray extends JSONArray implements com.github.webee.json.WritableJSONArray {
    public WritableJSONArray(com.alibaba.fastjson.JSONArray jsonArray) {
        super(jsonArray);
    }

    @Override
    public boolean push(Object value) {
        return jsonArray.add(Utils.resolveValue(value));
    }

    @Override
    public Object set(int index, Object value) {
        return Commons.resolveValue(jsonArray.set(index, Utils.resolveValue(value)));
    }

    @Override
    public Object remove(int index) {
        return Commons.resolveValue(jsonArray.remove(index));
    }
}
