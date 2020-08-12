package com.sg.framework.xss;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sg.framework.util.StringUtil;

/**
 * 
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {

    
    public Class<String> handledType() {
        return String.class;
    }

    
    public void serialize(String value, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            String encodedValue = StringUtil.stripXss(value).trim();
            jsonGenerator.writeString(encodedValue);
        }
    }

}