package com.lisir.cn.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisir.cn.exception.JsonDeserializeException;
import com.lisir.cn.exception.JsonSerializeException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lilei
 */
@Component
public class JsonUtils {

    private static ObjectMapper OBJECT_MAPPER;

    public JsonUtils(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
    }

    /**
     * 反序列化方法, 使用当前方法反序列化普通类,
     * 如果有泛型,使用这个泛型内部的可能会出现类型不是你需要的,
     * 请使用其他的序列化方法
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (IOException e) {
            throw new JsonDeserializeException("反序列化json失败",e);
        }
    }

    /**
     * 反序列化方法,可以使用这个方法反序列化含有泛型的类,并且可以指定泛型类型
     * 所以不会出现类型
     * @param json
     * @param typeReference 含有泛型信息的类型
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonDeserializeException("反序列化json失败",e);
        }
    }

    /**
     * 反序列化方法
     * @param json
     * @param tClass 带有泛型的类型
     * @param clazz 泛型类型
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> tClass, Class<?> ... clazz){
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(tClass, clazz);
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            throw new JsonDeserializeException("反序列化json失败",e);
        }
    }

    public static String objectToJson(Object object) {
        if(object == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            throw new JsonSerializeException("序列化失败",e);
        }
    }
}
