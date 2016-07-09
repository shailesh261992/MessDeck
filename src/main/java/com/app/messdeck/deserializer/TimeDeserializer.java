package com.app.messdeck.deserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class TimeDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {

	
		String text = jsonParser.getText();
		SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
		Date time = null;
		try {

			time = ft.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

}
