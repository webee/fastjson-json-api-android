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
        int age = 27;
        double javaScore = 80.;
        JSONObject jsonObject = genJsonObject(age, javaScore);

        doAssets(jsonObject, age, javaScore);

        //System.out.println(jsonObject.get("languages").getClass());
        //System.out.println(jsonObject.get("scores").getClass());
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void testDecoding() {
        int age = 27;
        double javaScore = 80.;
        String text = genJsonObject(age, javaScore).toJSONString();
        JSONObject jsonObject = json.parseObject(text);

        doAssets(jsonObject, age, javaScore);
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

    @Test
    public void testEncodingPerf() {
        int count = 10000;
        long ts = 0;
        long dMax = 0;
        long dMin = Long.MAX_VALUE;
        for (int i = 0; i <= count; i++) {
            int age = i + 20;
            double javaScore = i + 60.;
            long ss = System.nanoTime();
            JSONObject jsonObject = genJsonObject(age, javaScore);

            doAssets(jsonObject, age, javaScore);

            long ee = System.nanoTime();
            long d = ee - ss;
            if (i > 0) {
                // 忽略第一次
                ts += d;
                dMax = Math.max(dMax, d);
                dMin = Math.min(dMin, d);
            }
            System.out.println(String.format("%d: %f", i, d/1000000.));
        }
        System.out.println(String.format("total: %f", ts/1000000.));
        System.out.println(String.format("avg: %f", ts/1000000./count));
        System.out.println(String.format("max: %f", dMax/1000000.));
        System.out.println(String.format("min: %f", dMin/1000000.));
    }

    @Test
    public void testDecodingPerf() {
        int count = 10000;
        long ts = 0;
        long dMax = 0;
        long dMin = Long.MAX_VALUE;
        for (int i = 0; i <= count; i++) {
            int age = i + 20;
            double javaScore = i + 60.;
            String text = genJsonObject(age, javaScore).toJSONString();
            long ss = System.nanoTime();
            JSONObject jsonObject = new fastjsonJSON().parseObject(text);

            doAssets(jsonObject, age, javaScore);

            long ee = System.nanoTime();
            long d = ee - ss;
            if (i > 0) {
                // 忽略第一次
                ts += d;
                dMax = Math.max(dMax, d);
                dMin = Math.min(dMin, d);
            }
            System.out.println(String.format("%d: %f", i, d/1000000.));
        }
        System.out.println(String.format("total: %f", ts/1000000.));
        System.out.println(String.format("avg: %f", ts/1000000./count));
        System.out.println(String.format("max: %f", dMax/1000000.));
        System.out.println(String.format("min: %f", dMin/1000000.));
    }

    private JSONObject genJsonObject(int age, double javaScore) {
        WritableJSONObject jsonObject = new fastjsonJSON().newObject();
        jsonObject.set("name", "webee.易");
        jsonObject.set("age", age);
        jsonObject.set("address", null);
        jsonObject.set("height", 1.74);
        jsonObject.set("graduated", true);
        jsonObject.set("long.max", Long.MAX_VALUE);
        jsonObject.set("languages", new Object[]{"java", "python", "golang", null});

        WritableJSONObject scores = json.newObject();
        scores.set("java", javaScore);
        scores.set("python", 85.0);
        scores.set("golang", 82.5);
        scores.set("xxx", null);
        jsonObject.set("scores", scores);

        return jsonObject;
    }

    private void doAssets(JSONObject jsonObject, int age, double javaScore) {
        Assert.assertEquals(jsonObject.getString("name"), "webee.易");
        Assert.assertEquals(jsonObject.getInteger("age"), Integer.valueOf(age));
        Assert.assertEquals(jsonObject.getDouble("height"), Double.valueOf(1.74));
        Assert.assertEquals(jsonObject.getString("address"), null);
        Assert.assertEquals(jsonObject.isNull("address"), true);
        Assert.assertEquals(jsonObject.getBoolean("graduated"), true);
        Assert.assertEquals(jsonObject.getLong("long.max"), Long.valueOf(Long.MAX_VALUE));
        Assert.assertEquals(jsonObject.getArray("languages").getString(0), "java");
        Assert.assertEquals(jsonObject.getArray("languages").getString(3), null);
        Assert.assertEquals(jsonObject.getArray("languages").isNull(3), true);
        Assert.assertEquals(jsonObject.getObject("scores").getDouble("java"), Double.valueOf(javaScore));
        Assert.assertEquals(jsonObject.getObject("scores").getDouble("xxx"), null);
        Assert.assertEquals(jsonObject.getObject("scores").isNull("xxx"), true);
    }

}
