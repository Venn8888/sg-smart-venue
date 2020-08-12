package com.sg.framework.serializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {// HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = jp.getText();
		if(null == date || "".equals(date.trim())) {
			return null;
		}
		try {
			return new Date(Long.valueOf(date));
		} catch (Exception e) {
			try {
				return format.parse(date);
			} catch (ParseException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
