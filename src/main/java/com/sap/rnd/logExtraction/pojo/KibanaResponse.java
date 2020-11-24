package com.sap.rnd.logExtraction.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KibanaResponse {
	
	@JsonProperty("responses")
	private List<Response> responses;

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

}
