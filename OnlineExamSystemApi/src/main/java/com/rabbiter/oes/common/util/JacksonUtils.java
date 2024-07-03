package com.rabbiter.oes.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 封装 jackson 工具类
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.common.util
 * @since 2024/7/1
 */
@Slf4j
public class JacksonUtils {
    public static final String EMPTY_JSON_STR = "{}";
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的格式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        //忽略 在json字符串中存在，但在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将JSON字符串转化为Java对象
     *
     * @param jsonString    JSON字符串
     * @param typeReference java类型映射
     * @return bean
     */
    public static <T> T ofObject(String jsonString, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (JsonProcessingException e) {
            log.error("toObject error:{}", e.getMessage(), e);
            throw new ApiException(ResponseCode.JSON_PROCESSING_EXCEPTION, e);
        }
    }

    /**
     * 将JSON字符串转化为Java对象
     *
     * @param jsonString JSON字符串
     * @param clazz      java类型
     * @return bean
     */
    public static <T> T ofObject(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            log.error("toObject error:{}", e.getMessage(), e);
            throw new ApiException(ResponseCode.JSON_PROCESSING_EXCEPTION, e);
        }
    }

    /**
     * 将JSON字符串转化为Java对象
     *
     * @param jsonString   JSON字符串
     * @param defaultValue 默认值
     * @param clazz        java类型
     * @return bean
     */
    public static <T> T ofObject(String jsonString, T defaultValue, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            log.error("toObject error:{}", e.getMessage(), e);
        }
        return defaultValue;
    }

    /**
     * 将Java对象转化为JSON字符串
     *
     * @param obj          java对象
     * @param defaultValue 默认值
     * @return JSON字符串
     */
    public static String toJsonStr(Object obj, String defaultValue) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("toJsonString error:{}", e.getMessage(), e);
        }
        return defaultValue;
    }

    /**
     * 将Java对象转化为JSON字符串
     *
     * @param obj java对象
     * @return JSON字符串，异常返回“{}”
     */
    public static String toJsonStr(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("toJsonString error:{}", e.getMessage(), e);
            throw new ApiException(ResponseCode.JSON_PROCESSING_EXCEPTION, e);
        }
    }

    /**
     * 将Java对象转化为字节数组
     *
     * @param obj java对象
     * @return byte[] or null
     */
    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        try {
            bytes = objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error("Object转ByteArray失败：{}", e.getMessage(), e);
        }
        return bytes;
    }

    /**
     * 将JSON反序列化为List
     *
     * @param jsonString JSON字符串
     * @param clazz      java类型
     * @return list bean
     */
    public static <T> List<T> ofList(String jsonString, Class<T> clazz) {
        if (StringUtil.isBlank(jsonString)) {
            return null;
        }
        CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return objectMapper.readValue(jsonString, javaType);
        } catch (JsonProcessingException e) {
            log.error("JsonString to list error:{}", e.getMessage(), e);
            throw new ApiException(ResponseCode.JSON_PROCESSING_EXCEPTION, e);
        }
    }

    /**
     * 将JSON反序列化为Map
     *
     * @param jsonString JSON字符串
     * @param keyClazz   map key类型
     * @param valueClazz map value类型
     * @return map
     */
    public static <K, V> Map<K, V> ofMap(String jsonString, Class<K> keyClazz, Class<V> valueClazz) {
        if (StringUtil.isBlank(jsonString)) {
            return null;
        }
        MapType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, keyClazz, valueClazz);
        try {
            return objectMapper.readValue(jsonString, javaType);
        } catch (JsonProcessingException e) {
            log.error("JsonString to map error:{}", e.getMessage(), e);
            throw new ApiException(ResponseCode.JSON_PROCESSING_EXCEPTION, e);
        }
    }

    /**
     * 将JSON反序列化为Map, key类型为String
     *
     * @param jsonString JSON字符串
     * @param valueClazz map value类型
     * @return map
     */
    public static <V> Map<String, V> ofMap(String jsonString, Class<V> valueClazz) {
        return ofMap(jsonString, String.class, valueClazz);
    }

    /**
     * 将JSON反序列化为Map,默认key,value类型为String
     *
     * @param jsonString JSON字符串
     * @return map
     */
    public static Map<String, String> ofMap(String jsonString) {
        return ofMap(jsonString, String.class, String.class);
    }

    /** =============================以下是与JsonNode相关的======================================= */
    //JsonNode和JSONObject一样，都是JSON树形模型，只不过在jackson中，存在的是JsonNode
    public static JsonNode parseJSONObject(String jsonString) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            log.error("JSONString转为JsonNode失败：{}", e.getMessage(), e);
        }
        return jsonNode;
    }

    public static JsonNode parseJSONObject(Object object) {
        return objectMapper.valueToTree(object);
    }

    public static String toJSONString(JsonNode jsonNode) {
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            log.error("JsonNode转JSONString失败：{}", e.getMessage());
        }
        return jsonString;
    }

    //JsonNode是一个抽象类，不能实例化，创建JSON树形模型，得用JsonNode的子类ObjectNode，用法和JSONObject大同小异
    public static ObjectNode newJSONObject() {
        return objectMapper.createObjectNode();
    }

    //创建JSON数组对象，就像JSONArray一样用
    public static ArrayNode newJSONArray() {
        return objectMapper.createArrayNode();
    }


}
