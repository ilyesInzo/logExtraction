package com.sap.rnd.logExtraction.helper;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public final class RequestHelper {
	
	@SuppressWarnings("deprecation")
	public static Map<String, String> parseURLQuery(String url) throws URISyntaxException, UnsupportedEncodingException {
		List<NameValuePair> queryList = URLEncodedUtils.parse(new URI(url), StandardCharsets.UTF_8.toString());
		Map<String, String> queryMap = null;
		if (queryList != null && !queryList.isEmpty()) {
			queryMap = queryList.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
		}
		return queryMap;
	}

	public static String encodeURL(String url) {
		String encodedURL = null;
		try {
			encodedURL = URLEncoder.encode(url, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodedURL;
	}
	
	public static String getUaaUrl() {
		return "https://cf:@"+ "uaa"+ getCFRegionPath();
	}
	
	public static String getLogUrl() {
		return "https://"+ "logs"+getCFRegionPath();
	}
	
	private static String getCFRegionPath() {
		// get the CF_API from VCAP instead of this
		String url ="https://api.cf.us10.hana.ondemand.com";
		// System.getenv("VCAP_APPLICATION");
		// or use the com.sap.cloud.sdk.cloudplatform.ScpCfCloudPlatform
		// to get the EnvVar
		String cfPath = "";
		Matcher matcher = Pattern.compile(".cf(.[a-zA-Z]*)*").matcher(url);
		if(matcher.find()) {
			cfPath = matcher.group(0);
		}
		return cfPath;
	}

}
