package com.app.messdeck.deserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class DateDeserialize extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String text = p.getText();
		Date date = null;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = ft.parse(text);
		} catch (ParseException e) {
			throw new InvalidFormatException(e.getMessage(), text, Date.class);
		}
		return date;
	}

}
