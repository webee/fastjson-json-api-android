package com.github.webee.fastjson;

import com.github.webee.json.*;
import com.github.webee.json.JSONObject;

import org.junit.Test;

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

        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void testDecoding() {
        JSON json = new fastjsonJSON();
        String text = "{\"age\":27,\"graduated\":true,\"height\":1.74,\"languages\":[\"java\",\"python\",\"golang\"],\"name\":\"webee\",\"scores\":{\"golang\":82.5,\"java\":80,\"python\":85}}";
        JSONObject jsonObject = json.parseObject(text);

        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void test() {
        JSON json = new fastjsonJSON();
        JSONObject jsonObject = json.newObject();

        jsonObject.set("key", "中国\uD83D\uDE00");
        System.out.println(jsonObject.toJSONString());
    }
}
