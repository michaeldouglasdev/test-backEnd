package br.com.uol.webservice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ConsomeWebService {

	public static String getResponseStringFormat(String urlPersonagens) {
       try {
			URL url = new URL(urlPersonagens);
	        
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] buf = new byte[8192];
	        int len = 0;
	        
	        while ((len = in.read(buf)) != -1) {
	            baos.write(buf, 0, len);
	        }
	        
	        String body = new String(baos.toByteArray(), encoding);

	        return body;
       } 
       catch (IOException e) {
			e.printStackTrace();
       }
       
       return null;
	}
}
