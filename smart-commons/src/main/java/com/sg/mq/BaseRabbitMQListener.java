package com.sg.mq;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMq监听基类
 */
public abstract class BaseRabbitMQListener {

    protected ObjectMapper objMap = new ObjectMapper();
    private static Map<String, Integer> records = new HashMap<String, Integer>();

    /**
     * 获取消息，转换为接收对象
     * <p>
     *
     * @param message   消息
     * @param valueType 接收实体类型
     * @return 接收对象
     * @throws Exception
     */
    public <T> T gainData(Message message, Class<T> valueType) throws Exception {
        T dto = null;
        String receiveMsg = null;
        receiveMsg = new String(message.getBody(), "utf-8");
        dto = objMap.readValue(receiveMsg, valueType);
        return dto;
    }


    public <T> T gainData(Message message, Class<T> valueType, Class<?>... parameterClasses) throws Exception {
        T dto = null;
        String receiveMsg = null;
        receiveMsg = new String(message.getBody(), "utf-8");
        JavaType javaType = objMap.getTypeFactory().constructParametricType(valueType, parameterClasses);
        dto = objMap.readerFor(javaType).readValue(receiveMsg);
        return dto;
    }

    public void addErrorCount(String queue, String key) {
        key = queue + "_" + key;
        if (records.containsKey(key)) {
            records.put(key, records.get(key) + 1);
        } else {
            records.put(key, 1);
        }
    }

    public int getErrorCount(String queue, String key) {
        key = queue + "_" + key;
        if (records.containsKey(key)) {
            return records.get(key);
        } else {
            return 0;
        }
    }

    public void clearErrorCount(String queue, String key) {
        key = queue + "_" + key;
        records.remove(key);
    }

}