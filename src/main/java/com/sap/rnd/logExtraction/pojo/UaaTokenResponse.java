package com.sap.rnd.logExtraction.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UaaTokenResponse {
	
	@JsonProperty("access_token")
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	
	
	
	
}
