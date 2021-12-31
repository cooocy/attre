package ink.wulian.attre.json.vs;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * case 1:
 * round = 1000000
 * <p>
 * gson: str > json: 21432 21799
 * gson: json > str: 27162 24877
 * fastjson: str > json: 22265 23400
 * fastjson: json > str: 16261 16136
 * jackson: str > json: 21117 20502
 * jackson: json > str: 11051 11089
 * <p>
 * 结论: jackson 性能最好.
 */
public class A {

    public static void main(String[] args) throws JsonProcessingException {
        String jsonStr = readJson();
        int rounds = 1000000;
        jacksonPerf(jsonStr, rounds);
        gsonPerf(jsonStr, rounds);
        fastjsonPerf(jsonStr, rounds);
    }

    public static String readJson() {
        try {
            return Files.readString(Paths.get(ClassLoader.getSystemResource("simple.json").toURI()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void gsonPerf(String jsonStr, int rounds) {
        Map jsonMap = new Gson().fromJson(jsonStr, Map.class);
        int round;
        long begin;

        Gson gson = new Gson();

        round = 0;
        begin = System.currentTimeMillis();
        while (round < rounds) {
            Map map = gson.fromJson(jsonStr, Map.class);
            round++;
        }
        System.out.println("gson: str > json: " + (System.currentTimeMillis() - begin));

        round = 0;
        begin = System.currentTimeMillis();
        while (round < rounds) {
            String str = gson.toJson(jsonMap);
            round++;
        }
        System.out.println("gson: json > str: " + (System.currentTimeMillis() - begin));
    }

    public static void fastjsonPerf(String jsonStr, int rounds) {
        Map jsonMap = new Gson().fromJson(jsonStr, Map.class);
        int round;
        long begin;

        round = 0;
        begin = System.currentTimeMillis();
        while (round < rounds) {
            Map map = JSONObject.parseObject(jsonStr, Map.class);
            round++;
        }
        System.out.println("fastjson: str > json: " + (System.currentTimeMillis() - begin));

        round = 0;
        begin = System.currentTimeMillis();
        while (round < rounds) {
            String str = JSONObject.toJSONString(jsonMap);
            round++;
        }
        System.out.println("fastjson: json > str: " + (System.currentTimeMillis() - begin));
    }

    public static void jacksonPerf(String jsonStr, int rounds) throws JsonProcessingException {
        Map jsonMap = new Gson().fromJson(jsonStr, Map.class);
        int round;
        long begin;

        ObjectMapper objectMapper = new ObjectMapper();

        round = 0;
        begin = System.currentTimeMillis();
        while (round < rounds) {
            Map map = objectMapper.readValue(jsonStr, Map.class);
            round++;
        }
        System.out.println("jackson: str > json: " + (System.currentTimeMillis() - begin));

        round = 0;
        begin = System.currentTimeMillis();
        while (round < rounds) {
            String str = objectMapper.writeValueAsString(jsonMap);
            round++;
        }
        System.out.println("jackson: json > str: " + (System.currentTimeMillis() - begin));
    }


}
