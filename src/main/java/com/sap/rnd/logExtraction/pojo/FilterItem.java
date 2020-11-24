package com.sap.rnd.logExtraction.pojo;

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

	
	
}
