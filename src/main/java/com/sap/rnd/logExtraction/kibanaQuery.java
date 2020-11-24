package com.sap.rnd.logExtraction;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.sap.rnd.logExtraction.helper.JsonHelper;
import com.sap.rnd.logExtraction.pojo.AttributeMatch;
import com.sap.rnd.logExtraction.pojo.AttributeQuery;
import com.sap.rnd.logExtraction.pojo.FilterItem;
import com.sap.rnd.logExtraction.pojo.KibanaQuery;
import com.sap.rnd.logExtraction.pojo.TimeQuery;

public class kibanaQuery {

	private static String logstash_index = "{ \"index\": \"logstash-*\", \"ignore_unavailable\": true, \"preference\": 1605253703666 }";
	private static String RETUREN_LINE = "\n";
	private static String COMPONENT_ID = "component_id";

	public static String getKibanaRequest(String componentId, LocalDateTime startDate, LocalDateTime endDate, int queryTempalte) {
		String query = logstash_index + RETUREN_LINE;
		switch (queryTempalte) {
		case 1:
			query += getRequestQuery(componentId, startDate, endDate) + RETUREN_LINE;
			break;
		case 2:
			query += getAppLogQuery(componentId, startDate, endDate) + RETUREN_LINE;
			break;
		case 3:
			query += getRequestQuery(componentId, startDate, endDate) + RETUREN_LINE
					+ getAppLogQuery(componentId, startDate, endDate) + RETUREN_LINE;
			break;
		default:
			query += getAppLogQuery(componentId, startDate, endDate) + RETUREN_LINE;
			break;
		}
		return query;
	}

	private static String getRequestQuery(String componentId, LocalDateTime startDate, LocalDateTime endDate) {
		return prepareQuery("template/request.json", componentId, startDate, endDate);
	}

	private static String getAppLogQuery(String componentId, LocalDateTime startDate, LocalDateTime endDate) {
		return prepareQuery("template/app_log.json", componentId, startDate, endDate);
	}

	private static String prepareQuery(String filePath, String componentId, LocalDateTime startDate, LocalDateTime endDate) {
		String query = "";
		InputStream inStream = kibanaQuery.class.getClassLoader().getResourceAsStream(filePath);
		if (inStream != null) {
			try {
				KibanaQuery kibanaQuery = JsonHelper.convertToObject(inStream, KibanaQuery.class);
				if (componentId != null && !"".equals(componentId)) {
					FilterItem filterItem = addFilterItem(COMPONENT_ID, componentId);
					kibanaQuery.getQuery().getBool().getFilterItems().add(filterItem);
				}

				TimeQuery  timeQuery = kibanaQuery.getQuery().getBool().getFilterItems().get(1).getRange().getTimestamp();
				timeQuery.setGte(convertTimeZone(ZoneOffset.UTC ,startDate));
				timeQuery.setLte(convertTimeZone(ZoneOffset.UTC ,endDate));
				query = JsonHelper.writeObjectToJsonString(kibanaQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("tempalte not found");
		}
		return query;
	}

	private static FilterItem addFilterItem(String attributeName, Object attributeValue) {
		FilterItem filterItem = new FilterItem();
		AttributeMatch attributeMatch = new AttributeMatch();
		AttributeQuery attributeQuery = new AttributeQuery();
		attributeQuery.setQuery(attributeValue);
		attributeMatch.getProperties().put(attributeName, attributeQuery);
		filterItem.setMatch(attributeMatch);
		return filterItem;
	}
	
	private static String convertTimeZone(ZoneOffset zoneOffset ,LocalDateTime localDateTime) {
		return ZonedDateTime.of(localDateTime, zoneOffset).format( DateTimeFormatter.ISO_INSTANT);
	}

}
