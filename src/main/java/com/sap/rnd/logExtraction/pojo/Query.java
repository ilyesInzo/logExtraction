package com.sap.rnd.logExtraction.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Query {
	@JsonProperty("bool")
	private BoolQuery bool;
	
	
	public BoolQuery getBool() {
		return bool;
	}

	public void setBool(BoolQuery bool) {
		this.bool = bool;
	}

	// We want to keep the other values unchanged
	@JsonAnySetter
	Map<String, Object> properties = new HashMap<>();

	@JsonAnyGetter
	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public Object getPropertyValue(String key) {

		return properties.get(key);
	}
}
