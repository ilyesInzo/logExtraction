package com.sap.rnd.logExtraction.helper;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonHelper {

	private static final ObjectMapper OBJECT_MAPPER;

	public static final JsonFactory JSON_FACTORY;

	static {
		OBJECT_MAPPER = new ObjectMapper().configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		JSON_FACTORY = new JsonFactory().configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
	}

	public static <T> T convertToObject(InputStream rawDataInputStream, Class<T> objectClass) throws Exception {
		try {
			return OBJECT_MAPPER.readerFor(objectClass).readValue(rawDataInputStream);
		} catch (IOException e) {
			throw new Exception("Error while extracting json for class " + objectClass.getName(), e);
		}
	}

	public static String writeObjectToJsonString(Object jsonAnnotatedPojo) throws Exception {
		try {
			return OBJECT_MAPPER.writeValueAsString(jsonAnnotatedPojo);
		} catch (JsonProcessingException e) {
			throw new Exception("Exception while converting to JSON string ", e);
		}
	}

}
