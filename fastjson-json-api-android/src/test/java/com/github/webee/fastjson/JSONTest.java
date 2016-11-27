package com.github.webee.fastjson;

import com.github.webee.json.JSON;
import com.github.webee.json.JSONObject;

import org.junit.Test;

import java.util.Map;

/**
 * Created by webee on 16/11/25.
 */

public class JSONTest {
    @Test
    public void testEncoding() {
        JSON json = new fastjsonJSON();
        JSONObject jsonObject = json.newObject();
        jsonObject.set("name", "webee");
        jsonObject.set("age", 27);
        jsonObject.set("height", 1.74);
        jsonObject.set("graduated", true);
        jsonObject.set("languages", new Object[]{"java", "python", "golang"});

        JSONObject scores = json.newObject();
        scores.set("java", 80);
        scores.set("python", 85.0);
        scores.set("golang", 82.5);
        jsonObject.set("scores", scores);

        System.out.println(jsonObject.get("languages").getClass());
        System.out.println(jsonObject.get("scores").getClass());
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void testDecoding() {
        JSON json = new fastjsonJSON();
        String text = "{\"age\":27,\"graduated\":true,\"height\":1.74,\"languages\":[\"java\",\"python\",\"golang\"],\"name\":\"webee\",\"scores\":{\"golang\":82.5,\"java\":80,\"python\":85}}";
        JSONObject jsonObject = json.parseObject(text);

        System.out.println(jsonObject.get("languages").getClass());
        System.out.println(jsonObject.get("scores").getClass());
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void test() {
        JSON json = new fastjsonJSON();
        JSONObject jsonObject = json.newObject();

        jsonObject.set("key", "中国\uD83D\uDE00");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void testParse() {
        JSON json = new fastjsonJSON();
        System.out.println(json.parse("null"));
        System.out.println(json.parse("true").getClass());
        System.out.println(json.parse("\"abc\"").getClass());
        System.out.println(json.parse("0").getClass());
        System.out.println(json.parse("123456789").getClass());
        System.out.println(json.parse("1234567890123456").getClass());
        System.out.println(json.parse("1234.0").getClass());
        System.out.println(json.parse("[]").getClass());
        System.out.println(json.parseArray("[]").get().getClass());
        System.out.println(json.parse("{}").getClass());
        System.out.println(json.parseObject("{}").get().getClass());
        System.out.println(json.parseObject("{\"a\":{}}").get("a").getClass());
        Map<String, Object> a = (Map<String, Object>) json.parseObject("{\"a\":{\"b\":[1,2.3,{},999999999999999999999999999999999999999999999]}}").get("a");
        System.out.println(a.get("b").getClass());
        Object[] b = (Object[]) a.get("b");
        System.out.println(b[0].getClass());
        System.out.println(b[1].getClass());
        System.out.println(b[2].getClass());
        System.out.println(b[3].getClass());
    }
}
