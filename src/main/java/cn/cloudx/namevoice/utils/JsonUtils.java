package cn.cloudx.namevoice.utils;

import com.google.gson.GsonBuilder;

/**
 * @author zhanghao
 * @date 2018/06/24
 */
public class JsonUtils {
    /**
     * 友好的Json输出
     * @param object 要进行友好输出的json文本
     * @return 友好的json输出
     */
    public static String prettyJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create().toJson(object);
    }
}
