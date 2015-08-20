package com.example.requestverifier;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class RequestManager extends AsyncTask<Request, Integer, String> {

	Request mrequest = null;
	private Context ctxt;
	private Result res = Result.OK;

	public RequestManager(Context ctxt) {
		this.ctxt = ctxt;
	}

	@Override
	protected String doInBackground(Request... request) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost;
		String responseString;
		AccountManager accManager = AccountManager.get(ctxt);
		
		mrequest = request[0];
		List<NameValuePair> params = mrequest.getParams();
		try {
			String url = mrequest.getUrl();		
			Log.e("JSubmission Web api", "sending to url: " + url);
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			response = httpclient.execute(httpPost);
			
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			res = Result.UNKNOWN_ERROR;
			return null;
		} catch (IOException e) {
			res = Result.NO_INTERNET_CONNECTIOM;

			return null;
		}
		return responseString;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if (result == null || result.equals("")) {
			handleError(res);
			mrequest.handleResponse(result, res);
		} else {
			mrequest.handleResponse(result, Result.OK);
		}
		super.onPostExecute(result);
	}

	private void handleError(Result e) {
		if (ctxt != null) {
			if (e == Result.NO_INTERNET_CONNECTIOM) {
				Toast.makeText(ctxt, "Please check your internet connection.",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public enum Result {
		OK, NO_INTERNET_CONNECTIOM, UNKNOWN_ERROR
	}
}