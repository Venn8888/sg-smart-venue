package com.sg.framework.util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GsonUtil {
	private static Logger logger = LogManager.getLogger(GsonUtil.class);
	public static final String[] ACCEPT_DATE_FORMATS = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };	
	
	public static Gson create(){
	    GsonBuilder builder = new GsonBuilder();
	    builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
	        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	                throws JsonParseException {
	        	String date = json.getAsJsonPrimitive().getAsString();
	        	
	        	date = date.replace("T", " ");
	        	if(date.indexOf(",") != -1){
	        		date = date.split(",")[0];
	        	}
	            try {
	            	return DateUtils.parseDate(date, ACCEPT_DATE_FORMATS);
	            } catch (ParseException e) {
	            	logger.error(e.getMessage());
	                //throw new RuntimeException(e);
	                return null;
	            }
	        }
	    });
	    
	    builder.registerTypeAdapter(Long.class, new JsonDeserializer<Long>() {
	        public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	                throws JsonParseException {
	            String data = json.getAsJsonPrimitive().getAsString();
	            try {
	            	if(!StringUtil.isEmpty(data)){
	            		return Long.valueOf(data);
	            	}
	            } catch (Exception e) {
	            	logger.error(e.getMessage());
	                throw new RuntimeException(e);
	            }
				return null;
	        }
	    });

	    builder.registerTypeAdapter(Double.class, new JsonDeserializer<Double>() {
	        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	                throws JsonParseException {
	            String data = json.getAsJsonPrimitive().getAsString();
	            try {
	            	if(!StringUtil.isEmpty(data)){
	            		return Double.valueOf(data);
	            	}
	            } catch (Exception e) {
	            	logger.error(e.getMessage());
	                throw new RuntimeException(e);
	            }
				return null;
	        }
	    });
	    
	    builder.registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {
	        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	                throws JsonParseException {
	            String data = json.getAsJsonPrimitive().getAsString();
	            try {
	            	if(!StringUtil.isEmpty(data)){
	            		return Integer.valueOf(data);
	            	}
	            } catch (Exception e) {
	            	logger.error(e.getMessage());
	                throw new RuntimeException(e);
	            }
				return null;
	        }
	    });
	    
	    return builder.create();
	}

}
