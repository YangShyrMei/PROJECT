package net.share.loginSystem;

import java.util.HashMap;

public class ClientIP {
	static HashMap<String,String> clientIP= new HashMap<String,String>();
	ClientIP(){
		clientIP.put("GM","192.168.43.88");
		}
	protected static String getClientIP(String clientID) {
		return clientIP.get(clientID);
	}

}
