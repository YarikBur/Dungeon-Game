package com.yarikbur.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Query {
	private final String USER_AGENT = "Mozilla/5.0";
	public static StringBuffer response = new StringBuffer();
	public static String answer;
	public static String login;
	public static String ip;
	public static StringBuffer pass = new StringBuffer();
	public static String online = "False";
	static Query m = new Query();
	
	public static String query(String mode, String query, String arg0, String arg1, String arg2) throws Exception{
		if(mode.equals("online")) m.online(query, arg0, arg1);
		else m.sendPost(arg0, arg1, arg2);
		return answer;
	}
	
	private void online(String arg0, String arg1, String arg2) throws Exception{
		if(arg0.equals("registered")){
			m.sendPost("allPlayer", arg1, arg2);
		} else if(arg0.equals("online")) {
			m.sendPost("onlinePlayer", arg1, arg2);
		} else if(arg0.equals("setOnlineT")){
			online = "True";
			m.sendPost("setOnline", arg1, arg2);
		} else if(arg0.equals("setOnlineF")){
			online = "False";
			m.sendPost("setOnline", arg1, arg2);
		}
	}
	
	@SuppressWarnings("unused")
	private void sendPost(String mode, String login, String pass) throws Exception {
		String url = "http://"+ip+"/function.php";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "login="+login+"&pass="+pass+"&mode="+mode+"&online="+online;

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		
		response.setLength(0);
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		answer = response.toString();
	}
}
