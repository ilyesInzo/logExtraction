package com.sap.rnd.logExtraction.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeQuery {
	//the value could be any String, int ...
	@JsonProperty("query")
	private Object query;

	public Object getQuery() {
		return query;
	}

	public void setQuery(Object query) {
		this.query = query;
	}
	

}
