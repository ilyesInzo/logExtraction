package com.sap.rnd.logExtraction.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitContent {
	@JsonProperty("_index")
	private String index;
	@JsonProperty("_type")
	private String type;
	@JsonProperty("_id")
	private String id;
	@JsonProperty("_source")
	private SourceContent sourceContent;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SourceContent getSourceContent() {
		return sourceContent;
	}

	public void setSourceContent(SourceContent sourceContent) {
		this.sourceContent = sourceContent;
	}
	
	

}
