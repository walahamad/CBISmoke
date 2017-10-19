package com.generic.selenium.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;


public class SelectorUtil {
	
	public static TreeMap <String, Elements> selectorToElementsMap;//TreeMap is better than HashMap to keep the insertion order
	public static TreeMap <String, String> selectorToValuesMap;
	
	 public static void initializeElementsSelectorsMaps(String[] subStrArr,String[] valuesArr){
	    	Elements foundElements = null;
	    	String selectorType = "id";
	    	selectorToElementsMap = new TreeMap <String, Elements>();
	    	selectorToValuesMap = new TreeMap <String, String>();
	    	try {
				enableSSLSocket();
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				Document doc = Jsoup.connect("https://www.tommybahama.com/en/login").get();
				int index = 0;
				for(String subStr: subStrArr) {
					foundElements = doc.select("[id="+subStr+"]");
					if(foundElements.isEmpty()) {
						//use regular expression (register.)?firstName for example
						foundElements = doc.select("[id~=(register.)?"+subStr+"]");
					}
					
					if (foundElements.isEmpty()) {
						//use * to mean contains
						foundElements = doc.select("[class*="+subStr+"]");
						selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
					}
					/*if (foundElements.isEmpty()) {
						foundElements = doc.select("contains("+ subStr +")");
						selectorType = (!(foundElements.isEmpty()) ? subStr:selectorType);
					}*/
					if (foundElements.isEmpty()) {
						foundElements = doc.select("[name*="+subStr+"]");
						selectorType = (!(foundElements.isEmpty()) ? "name":selectorType);
					}
					
					
					if (foundElements != null) {
						String key = selectorType +"_"+ index;
						selectorToElementsMap.put(key, foundElements);
						selectorToValuesMap.put(key, valuesArr[index]);
					}
					index++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    //This method was added to enable SSL connection
	    public static void enableSSLSocket() throws KeyManagementException, NoSuchAlgorithmException {
	        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        });
	 
	        SSLContext context = SSLContext.getInstance("TLS");
	        context.init(null, new X509TrustManager[]{new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
	        }}, new SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
	    }
	    
	    public static By getBySelectorForElements(Elements foundElements, String selType) {
	    	By selector = null; 
			for (org.jsoup.nodes.Element e : foundElements) {
				selector = null;
					switch(selType) {
					case "id":
						selector = By.id(e.id());
						break;
					case "class":
						String[] tempClassArr = e.className().split(" ");
						StringBuilder className = new StringBuilder();
						for (String s : tempClassArr) {
							className.append(".");
							className.append(s);
						}
						selector = By.cssSelector(className.toString());
						break;
					case "name":
						selector = By.name(e.attr("name"));
						break;
					/*default:
						String temp = selType.substring(0, selType.indexOf('_'));
						selector = By.xpath("//[contains(text()='"+ temp + "')]");
						break;*/
					}
		    		try {
		    			
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			return selector;
	    	
	    }
}
