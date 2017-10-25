package com.generic.selenium.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.List;
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

import com.generic.selenium.setup.SelTestCase;


public class SelectorUtil {
	
	public static LinkedHashMap<String, Elements> selectorToElementsMap;//LinkedHashMap is better than HashMap to keep the insertion order
	public static LinkedHashMap <String, String> selectorToValuesMap;
	public static LinkedHashMap <String, Boolean> selectorForErrorMsgsMap;
	
	 public static void initializeElementsSelectorsMaps(List<String> subStrArr,List<String> valuesArr){
	    	Elements foundElements = null;
	    	String selectorType = "id";
	    	selectorToElementsMap = new LinkedHashMap <String, Elements>();
	    	selectorToValuesMap = new LinkedHashMap <String, String>();
	    	selectorForErrorMsgsMap = new LinkedHashMap <String, Boolean>();
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
				Document doc = Jsoup.connect(SelTestCase.getDriver().getCurrentUrl()).get();
	    		//Document doc = Jsoup.connect("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/login?site=apparel-uk").get();
				int index = 0;
				Boolean isAnErrorSelector = Boolean.FALSE;
				for(String subStr: subStrArr) {
					if (subStr.contains("error")) {
						isAnErrorSelector = Boolean.TRUE;
					}
					foundElements = doc.select("[id="+subStr+"]");
					if(foundElements.isEmpty()) {
						//use regular expression (register.)?firstName for example
						foundElements = doc.select("[id~=(register.)?"+subStr+"$]");
					}
					
					if (foundElements.isEmpty()) {
						//use * to mean contains
						foundElements = doc.select("[class*="+subStr+" i]");
						selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
					}
					if (foundElements.isEmpty()) {
						foundElements = doc.select("*:contains("+ subStr +")");
						selectorType = (!(foundElements.isEmpty()) ? subStr:selectorType);
					}
					if (foundElements.isEmpty()) {
						foundElements = doc.select("[name*="+subStr+"]");
						selectorType = (!(foundElements.isEmpty()) ? "name":selectorType);
					}
					
					
					if (foundElements != null) {
						String key = selectorType +"_"+ index;
						selectorToElementsMap.put(key, foundElements);
						selectorToValuesMap.put(key, valuesArr.get(index));
						selectorForErrorMsgsMap.put(key, isAnErrorSelector);
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
					default:
						selector = By.xpath("//button[contains(text(),'"+ selType + "')]");
						break;
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
