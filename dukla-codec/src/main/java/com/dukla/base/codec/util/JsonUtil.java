package com.dukla.base.codec.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

public class JsonUtil {

    private static final Logger logger =
            LoggerFactory.getLogger(JsonUtil.class);


    private static ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
    }

    /**
     * 对象转json
     */
    public static String objToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    /**
     * json转对象
     */
    public static <T> T jsonToObj(String content, Class<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 把source转为target
     * @param source source
     * @param target target
     * @param <T> 返回值类型
     * @return 返回值
     * @throws Exception newInstance可能会抛出的异常
     */
    public static <T> T mapToObject(Map source,Class<T> target) throws Exception {
        Field[] fields = target.getDeclaredFields();
        T o = target.newInstance();
        for (Field field : fields) {
            Object val;
            if ((val = source.get(field.getName())) != null) {
                field.setAccessible(true);
                field.set(o, val);
            }
        }
        return o;
    }

}
