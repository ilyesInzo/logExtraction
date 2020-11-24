package com.sap.rnd.logExtraction.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class AttributeMatch {
	
	@JsonAnySetter
	Map<String, AttributeQuery> properties = new HashMap<>();

	@JsonAnyGetter
	public Map<String, AttributeQuery> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, AttributeQuery> properties) {
		this.properties = properties;
	}

	public AttributeQuery getPropertyValue(String key) {

		return properties.get(key);
	}
	

}
