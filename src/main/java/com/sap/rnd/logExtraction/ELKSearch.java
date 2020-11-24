package com.sap.rnd.logExtraction;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.sap.rnd.logExtraction.exception.RequestProcessException;
import com.sap.rnd.logExtraction.helper.JsonHelper;
import com.sap.rnd.logExtraction.helper.RequestHelper;
import com.sap.rnd.logExtraction.pojo.KibanaResponse;
import com.sap.rnd.logExtraction.pojo.UaaTokenResponse;

/**
 * Hello world!
 *
 */
public class ELKSearch {
	// can changed to static and used to send other request
	// without passing all the steps again
	private CookieStore cookieStore;
	// we will get from VCAP cf_api
	private static String uaaUrl = RequestHelper.getUaaUrl();
	private static String logsUrl = RequestHelper.getLogUrl();
	
	private static String state = "";
	private static String client_id = "";
	private static String token = "";
	
	private static String STATE = "state";
	private static String CLIENT_ID = "client_id";
	private static String KBN_XSRF = "kbn-xsrf";
	private static String LOCATION = "Location";
	
	private static String USERNAME = "ilyes.mansour@focus-corporation.com";
	private static String PASSWORD = "Psy4tr@1";
	

	private void getTokenAccess() throws RequestProcessException {
		System.out.println("********************** Get Token Access ************************");
		HttpClientContext context = HttpClientContext.create();
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(uaaUrl + "/oauth/token");
		List<NameValuePair> params = new ArrayList<NameValuePair>(5);
		params.add(new BasicNameValuePair("username", USERNAME));
		params.add(new BasicNameValuePair("password", PASSWORD));
		params.add(new BasicNameValuePair("client_id", "cf"));
		params.add(new BasicNameValuePair("grant_type", "password"));
		params.add(new BasicNameValuePair("response_type", "token"));
		HttpResponse response;
		UaaTokenResponse uaaTokenResponse = null;
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
			response = httpclient.execute(httppost, context);
			cookieStore = context.getCookieStore();
			HttpEntity entity = response.getEntity();
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RequestProcessException("Got response status "+response.getStatusLine().getStatusCode()
						+" instead of 200");
			}
			if (entity != null) {
				try {
					InputStream instream = entity.getContent();
					uaaTokenResponse = JsonHelper.convertToObject(instream, UaaTokenResponse.class);
					token = uaaTokenResponse.getAccessToken();
				} catch (Exception e) {
					throw new RequestProcessException("Unexpected error while converting response body",e);
				}
			}else {
				throw new RequestProcessException("Returned response entity null");
			}
		} catch (IOException e) {
			throw new RequestProcessException("Unexpected error while executing the POST request",e);
		}
	}

	private void getKibanaInfo() throws RequestProcessException {
		System.out.println("********************** Kibana Info ************************");
		HttpClientContext context = HttpClientContext.create();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
		HttpClient httpclient = HttpClientBuilder.create().disableRedirectHandling().build();
		HttpGet httpget = new HttpGet(logsUrl + "/app/kibana#/discover");
		HttpResponse response;
		try {
			response = httpclient.execute(httpget, context);
			cookieStore = context.getCookieStore();
			if(response.getStatusLine().getStatusCode() != 302) {
				throw new RequestProcessException("Got response status "+response.getStatusLine().getStatusCode()
						+" instead of 302");
			}
			Map<String, String> queryMap = RequestHelper.parseURLQuery(response.getLastHeader(LOCATION).getValue());
			if (queryMap != null && !queryMap.isEmpty()) {
				state = queryMap.get(STATE);
				client_id = queryMap.get(CLIENT_ID);
				if(state == null || client_id == null) {
					throw new RequestProcessException("Missing state or client_id params");
				}
			} else {
				throw new RequestProcessException("No query params found to process authentification");
			}
		} catch (IOException e) {
			throw new RequestProcessException("Unexpected error while executing the GET request",e);
		} catch (URISyntaxException e) {
			throw new RequestProcessException("Could not parse Location url",e);
		}
	}

	private void validateAuth() throws RequestProcessException {
		System.out.println("********************** Validate Authentification ************************");
		HttpClientContext context = HttpClientContext.create();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(uaaUrl + "/oauth/authorize?grant_type=authorization_code&client_id=" + client_id
				+ "&response_type=code&state=" + state + "&redirect_uri=" + RequestHelper.encodeURL(logsUrl + "/authorization"));
		httpget.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + token);
		HttpResponse response;
		try {
			response = httpclient.execute(httpget, context);
			cookieStore = context.getCookieStore();
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RequestProcessException("Got response status "+response.getStatusLine().getStatusCode()
						+" instead of 200");
			}
		} catch (IOException e) {
			throw new RequestProcessException("Unexpected error while executing the GET request",e);
		}
	}

	private KibanaResponse sendKibanaQuery(String componentId, LocalDateTime startDate, LocalDateTime endDate, int queryTempalte) throws RequestProcessException {

		System.out.println("********************** Send Kibana Query ************************");
		HttpClientContext context = HttpClientContext.create();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(logsUrl + "/elasticsearch/_msearch");
		HttpResponse response;
		httppost.setHeader(KBN_XSRF, "true");
		KibanaResponse kibanaResponse = null;
		try {
			httppost.setEntity(new StringEntity(kibanaQuery.getKibanaRequest(componentId, startDate, endDate, queryTempalte)));
			response = httpclient.execute(httppost, context);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RequestProcessException("Got response status "+response.getStatusLine().getStatusCode()
						+" instead of 200");
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try {
					InputStream instream = entity.getContent();
					 kibanaResponse = JsonHelper.convertToObject(instream, KibanaResponse.class);
				} catch (Exception e) {
					throw new RequestProcessException("Unexpected error while converting response body",e);
				}
			}else {
				throw new RequestProcessException("Returned response entity null");
			}
		} catch (IOException e) {
			throw new RequestProcessException("Unexpected error while executing the POST request",e);
		}
		return kibanaResponse;
	}
	
	public KibanaResponse getLogs(String componentId, LocalDateTime startDate, LocalDateTime endDate, int queryTempalte) throws RequestProcessException {
		KibanaResponse kibanaResponse;
		getTokenAccess();
		getKibanaInfo();
		validateAuth();
		kibanaResponse = sendKibanaQuery(componentId, startDate, endDate, queryTempalte);
		return kibanaResponse;
	}

}
