package com.sap.rnd.logExtraction.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitInfo {
	
	@JsonProperty("hits")
	private List<HitContent> hits ;
	
	//Attention it could be an integer (if returned values)
	//or an object {'value':Integer} and you have to create such object and cast it if not instanceOf Integer
	@JsonProperty("total")
	private Object total ;

	public List<HitContent> getHits() {
		return hits;
	}

	public void setHits(List<HitContent> hits) {
		this.hits = hits;
	}

	public Object getTotal() {
		return total;
	}

	public void setTotal(Object total) {
		this.total = total;
	}

}
