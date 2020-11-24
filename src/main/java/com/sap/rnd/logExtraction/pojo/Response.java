package com.sap.rnd.logExtraction.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty("hits")
	private HitInfo hits ;
	
	@JsonProperty("status")
	private Integer status ;

	public HitInfo getHits() {
		return hits;
	}

	public void setHits(HitInfo hits) {
		this.hits = hits;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	

}
