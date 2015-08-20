package com.example.requestverifier;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.requestverifier.RequestManager.Result;

/**
 * Created by abdo on 2/20/15. an abstraction of a request that allows to handle
 * the request parameters and what to do with response in each request
 * Parameters: URL: The base URL of all requests Functions: geturl: returns the
 * url of the request. handleResponse: implemented to handle the response
 */
public abstract class Request {
	public final static String URL = "http://ip-api.com/json?";
	public String type;
	private List<NameValuePair> params = new ArrayList<NameValuePair>();;

	public void addParameter(String key, String value) {
		params.add(new BasicNameValuePair(key, value));
	}

	public List<NameValuePair> getParams() {
		return params;
	}

	public abstract String getUrl();

	public abstract void handleResponse(String response, Result res);
}
