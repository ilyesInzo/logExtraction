package com.sap.rnd.logExtraction.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SourceContent {
	
	private String channel;
	private String level;
	
	@JsonProperty("organization_id")
	private String organizationId;
	@JsonProperty("organization_name")
	private String organizationName;
	
	@JsonProperty("space_id")
	private String spaceId;
	@JsonProperty("space_name")
	private String spaceName;
	
	@JsonProperty("component_id")
	private String componentId;
	@JsonProperty("component_name")
	private String componentName;
	@JsonProperty("component_instance")
	private String componentInstance;
	
	@JsonProperty("service_id")
	private String serviceId;
	@JsonProperty("service_name")
	private String serviceName;
	
	@JsonProperty("correlation_id")
	private String correlationId;
	@JsonProperty("container_id")
	private String containerId;
	
	private String type; // request , log
	private String method;
	private String request;
	@JsonProperty("request_received_at")
	private String requestReceivedAt;
	@JsonProperty("response_status")
	private Integer responseStatus;
	@JsonProperty("response_sent_at")
	private String responseSentAt;
	
	@JsonProperty("msg")
	private String message;
	
	@JsonProperty("@timestamp")
	private String timestamp;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentInstance() {
		return componentInstance;
	}

	public void setComponentInstance(String componentInstance) {
		this.componentInstance = componentInstance;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRequestReceivedAt() {
		return requestReceivedAt;
	}

	public void setRequestReceivedAt(String requestReceivedAt) {
		this.requestReceivedAt = requestReceivedAt;
	}

	public Integer getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(Integer responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getResponseSentAt() {
		return responseSentAt;
	}

	public void setResponseSentAt(String responseSentAt) {
		this.responseSentAt = responseSentAt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
