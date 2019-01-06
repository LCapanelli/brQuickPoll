package com.brquickpoll.client;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class BrQuickPollClientV3BasicAuth {

	
	//Basic Authentication for V3 client
	private HttpHeaders getAuthenticationHeader(String username, String password) {
			
		String credentials = username + ":" + password;
		byte[] base64CredentialData = Base64.encodeBase64(credentials.getBytes());
			
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic " + new String(base64CredentialData));
		
		return headers;
	}
}
 