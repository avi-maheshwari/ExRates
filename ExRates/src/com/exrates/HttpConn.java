package com.exrates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConn {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String APP_ID = "18856ba1456340fca251a7af629578fe";
	private static final String GET_URL = " https://openexchangerates.org/api/latest.json";
	

	public String sendGET() throws IOException {
		
		StringBuffer response = new StringBuffer();
		String getUrl = GET_URL+"?app_id="+APP_ID+"&base=usd";
		System.out.println("httpConnector.sendGET() : URL is : "+getUrl);
		URL obj = new URL(getUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) 
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} else {
			System.out.println("GET request not worked");
		}
		
		return response.toString(); 

	}
}


