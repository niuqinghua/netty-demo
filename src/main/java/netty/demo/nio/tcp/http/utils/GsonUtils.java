package netty.demo.nio.tcp.http.utils;

import com.google.gson.Gson;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class GsonUtils {

    private static final Gson gson = new Gson();

    public static String objToJson(Object obj) throws Exception{
        return gson.toJson(obj);
    }

    public static <T> T jsonToPojo(String jsonStr, Class<T> clazz) throws Exception{
        return gson.fromJson(jsonStr, clazz);
    }

}
