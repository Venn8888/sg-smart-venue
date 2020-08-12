package com.sg.framework.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateSerializer extends JSONSerializer {
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws

	IOException, JsonProcessingException {
		SimpleDateFormat formatter =   new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);

	}
}
