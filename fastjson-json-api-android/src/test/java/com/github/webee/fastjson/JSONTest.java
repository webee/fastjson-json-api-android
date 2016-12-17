package com.github.webee.fastjson;

import com.github.webee.json.JSON;
import com.github.webee.json.JSONObject;
import com.github.webee.json.WritableJSONObject;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by webee on 16/11/25.
 */

public class JSONTest {
    private JSON json = new fastjsonJSON();
    @Test
    public void testEncoding() {
        WritableJSONObject jsonObject = json.newObject();
        jsonObject.set("name", "webee.易");
        jsonObject.set("age", 27);
        jsonObject.set("address", null);
        jsonObject.set("height", 1.74);
        jsonObject.set("graduated", true);
        jsonObject.set("languages", new Object[]{"java", "python", "golang", null});

        WritableJSONObject scores = json.newObject();
        scores.set("java", 80);
        scores.set("python", 85.0);
        scores.set("golang", 82.5);
        scores.set("xxx", null);
        jsonObject.set("scores", scores);

        Assert.assertEquals(jsonObject.getString("name"), "webee.易");
        Assert.assertEquals(jsonObject.getInteger("age"), Integer.valueOf(27));
        Assert.assertEquals(jsonObject.getDouble("height"), Double.valueOf(1.74));
        Assert.assertEquals(jsonObject.getString("address"), null);
        Assert.assertEquals(jsonObject.isNull("address"), true);
        Assert.assertEquals(jsonObject.getBoolean("graduated"), true);
        Assert.assertEquals(jsonObject.getArray("languages").getString(0), "java");
        Assert.assertEquals(jsonObject.getArray("languages").getString(3), null);
        Assert.assertEquals(jsonObject.getArray("languages").isNull(3), true);
        Assert.assertEquals(jsonObject.getObject("scores").getDouble("java"), Double.valueOf(80));
        Assert.assertEquals(jsonObject.getObject("scores").getDouble("xxx"), null);
        Assert.assertEquals(jsonObject.getObject("scores").isNull("xxx"), true);

        //System.out.println(jsonObject.get("languages").getClass());
        //System.out.println(jsonObject.get("scores").getClass());
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void testDecoding() {
        String text = "{\"address\":null,\"age\":27,\"graduated\":true,\"height\":1.74,\"languages\":[\"java\",\"python\",\"golang\",null],\"name\":\"webee.易\",\"scores\":{\"golang\":82.5,\"java\":80,\"python\":85,\"xxx\":null}}";
        JSONObject jsonObject = json.parseObject(text);

        Assert.assertEquals(jsonObject.getString("name"), "webee.易");
        Assert.assertEquals(jsonObject.getInteger("age"), Integer.valueOf(27));
        Assert.assertEquals(jsonObject.getDouble("height"), Double.valueOf(1.74));
        Assert.assertEquals(jsonObject.isNull("address"), true);
        Assert.assertEquals(jsonObject.getString("address"), null);
        Assert.assertEquals(jsonObject.getBoolean("graduated"), true);
        Assert.assertEquals(jsonObject.getArray("languages").getString(0), "java");
        Assert.assertEquals(jsonObject.getArray("languages").getString(3), null);
        Assert.assertEquals(jsonObject.getArray("languages").isNull(3), true);
        Assert.assertEquals(jsonObject.getObject("scores").getDouble("java"), Double.valueOf(80));
        Assert.assertEquals(jsonObject.getObject("scores").getDouble("xxx"), null);
        Assert.assertEquals(jsonObject.getObject("scores").isNull("xxx"), true);
    }

    @Test
    public void testParse() {
        Assert.assertEquals(json.parse("null"), null);
        Assert.assertEquals(json.parse("true"), true);
        Assert.assertEquals(json.parse("\"abc\""), "abc");
        Assert.assertEquals(json.parse("0"), 0);
        Assert.assertEquals(json.parse("123456789"), 123456789);
        Assert.assertEquals(json.parse("1234567890123456"), 1234567890123456L);
        Assert.assertEquals(json.parse("[]") instanceof com.github.webee.json.JSONArray, true);
        Assert.assertArrayEquals(json.parseArray("[]").get(), new Object[0]);
        Assert.assertEquals(json.parse("{}") instanceof com.github.webee.json.JSONObject, true);
        Assert.assertEquals(json.parseObject("{}") instanceof com.github.webee.json.JSONObject, true);
        /*
        Map<String, Object> a = (Map<String, Object>) json.parseObject("{\"a\":{\"b\":[1,2.3,{},999999999999999999999999999999999999999999999]}}").get("a");
        System.out.println(a.get("b").getClass());
        Object[] b = (Object[]) a.get("b");
        System.out.println(b[0].getClass());
        System.out.println(b[1].getClass());
        System.out.println(b[2].getClass());
        System.out.println(b[3].getClass());
        */
    }

    @Test
    public void testEmoji() {
        WritableJSONObject jsonObject = json.newObject();

        String value = "中国\uD83D\uDE00";
        jsonObject.set("key", value);
        Assert.assertEquals(jsonObject.getString("key"), value);
    }
}
