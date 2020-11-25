package com.sap.rnd.logExtraction.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FilterItem {
	@JsonProperty("match_phrase")
	private AttributeMatch match;
	@JsonProperty("range")
	private TimeRange range;
	public AttributeMatch getMatch() {
		return match;
	}
	public void setMatch(AttributeMatch match) {
		this.match = match;
	}
	public TimeRange getRange() {
		return range;
	}
	public void setRange(TimeRange range) {
		this.range = range;
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
