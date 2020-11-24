package com.sap.rnd.logExtraction.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeRange {
	@JsonProperty("@timestamp")
	private TimeQuery timestamp;

	public TimeQuery getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(TimeQuery timestamp) {
		this.timestamp = timestamp;
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
