package netty.demo.nio.tcp.http.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.StringWriter;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class JacksonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static XmlMapper xmlMapper = new XmlMapper();

    public static String objToJson(Object obj) throws Exception{
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T jsonToPojo(String jsonStr, Class<T> clazz) throws Exception{
        return objectMapper.readValue(jsonStr, clazz);
    }

    public static String objToXml(Object obj) throws Exception{
        return jsonToXml(objectMapper.writeValueAsString(obj));
    }

    public static <T> T xmlToPojo(String xmlStr, Class<T> clazz) throws Exception{
        return objectMapper.readValue(xmlToJson(xmlStr), clazz);
    }

    private static String jsonToXml(String jsonStr)throws Exception{
        JsonNode root = objectMapper.readTree(jsonStr);
        String xml = xmlMapper.writeValueAsString(root);
        return xml;
    }

    private static String xmlToJson(String xml)throws Exception{
        StringWriter w = new StringWriter();
        JsonParser jp = xmlMapper.getFactory().createParser(xml);
        JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
        while (jp.nextToken() != null) {
            jg.copyCurrentEvent(jp);
        }
        jp.close();
        jg.close();
        return w.toString();
    }

}
