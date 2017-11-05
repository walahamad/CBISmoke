package com.generic.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.generic.setup.ActionDriver;
import com.generic.setup.SelTestCase;


public class SelectorUtil extends SelTestCase {
	
	public static Boolean isAnErrorSelector = Boolean.FALSE;
	
	 public static void initializeElementsSelectorsMaps(LinkedHashMap<String, LinkedHashMap> webElementsInfo , boolean isValidationStep) throws IOException
	 {
		 	getCurrentFunctionName(true);
	    	Elements foundElements = null;
	    	String selectorType = "id";
	    	try {
				enableSSLSocket();
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	Document doc;
			   if (!isValidationStep) {
			    
				doc = Jsoup.parse(SelTestCase.getDriver().getPageSource());
			   } else {
			
				   doc = Jsoup.parse(SelTestCase.getDriver().getPageSource());
				   //TODO remove useless if , kept for debuging purposes  
//				   Element foundElement = doc.select("[id=email.errors]").first();
//				   SelTestCase.logs.debug("===========>>>>>>"+foundElement.toString());
				   
			   }
			int index = 0;
			
			for(String subStr: webElementsInfo.keySet()) {
			 if (subStr.contains("error") && !isAnErrorSelector && isValidationStep) {
			  isAnErrorSelector = Boolean.TRUE;
			 }
			 
			 foundElements = doc.select("[id="+subStr+"]");
			 selectorType = "id";
			 
			 if(foundElements.isEmpty())
			 {
				 //logs.debug("in id~");
			  //use regular expression (register.)?firstName for example
			  foundElements = doc.select("[id~=(register.)?"+subStr+"$]");
			 }
			 
			 if (foundElements.isEmpty())
			 {
				 //logs.debug("in class");
			  //use * to mean contains
			  foundElements = doc.select("[class*="+subStr+" i]");
			  selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
			 }
			 
    	     if (foundElements.isEmpty())
    	     {
    	    	 //logs.debug("in xpath");
    	      foundElements = doc.select("*:contains("+ subStr +")");
    	      selectorType = (!(foundElements.isEmpty()) ? subStr:selectorType);
    	     }
    	     
			 if (foundElements.isEmpty()) {
				 //logs.debug("in name");
			  foundElements = doc.select("[name*="+subStr+"]");
			  selectorType = (!(foundElements.isEmpty()) ? "name":selectorType);
			 }
			 
			 if (foundElements != null)
			 {
				 Map <String, Object> webElementInfo = webElementsInfo.get(subStr);
				  webElementInfo.put("selector",getStringSelectorForElements(foundElements, selectorType));
				  webElementInfo.put("SelType",selectorType);
				  webElementInfo.put("by",getBySelectorForElements(foundElements,selectorType));
				  webElementInfo.put("action",getActiontype(foundElements));
				  SelTestCase.logs.debug("Found a valid selector: " + Arrays.asList(webElementInfo) );
			  
			  if (foundElements.size()>1)
			  {
			   //TODO handel multiple elemnet in browser
			   //System.out.println("TODO LATER");
			  }
			 }
			 index++;
			}
			getCurrentFunctionName(false);
	    	
	    }
	    
	    private static String getActiontype(Elements foundElements) {
			String ActionType = "";
			//TODO fix multiple elements 
			for (org.jsoup.nodes.Element e : foundElements) {
				
					if (e.tagName().equals("input") && (e.attr("type").equals("text") || e.attr("type").equals("password") )) {
						return "type";
					} else if (e.tagName().equals("select")) {
						return "selectByText";
					} else if (e.tagName().equals("input") && e.attr("type").equals("checkbox")){
						boolean selected = e.attr("checked").equalsIgnoreCase("checked");
				        if(!selected)
				        {
				           return "click";
				        }
					} else if (e.tagName().equals("button")) {
						return "click";
					} else if (e.tagName().equals("input") && e.attr("type").equals("submit")) {
						return "click";
					}
					else
					{
						return "Validate"; 
					}
//					else if (e.tagName().equals("span") && isErrorSel) {
//						if (!value.isEmpty()) {
//							Assert.assertNotEquals("The "+ e.id() + "has incorrect error msg", e.text(), value);
//							logs.debug("The "+ e.id() + "is found and has correct error msg");
//						}
					
			}
	    	return ActionType;
		
		
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
		    		
				}
			return selector;
	    	
	    }
	    

	    public static String getStringSelectorForElements(Elements foundElements, String selType) {
	    	getCurrentFunctionName(true);
	    	
	    	logs.debug("selector type is " + selType);
	    	
	    	String selector = ""; 
			for (org.jsoup.nodes.Element e : foundElements) {
				selector = null;
					switch(selType) {
					case "id":
						selector = e.id();
						break;
					case "class":
						String[] tempClassArr = e.className().split(" ");
						StringBuilder className = new StringBuilder();
						for (String s : tempClassArr) {
							className.append(".");
							className.append(s);
						}
						selector = className.toString();
						break;
					case "name":
						selector = e.attr("name");
						break;
					default:
						selector = "//button[contains(text(),'"+ selType + "')]";
						break;
					}
				}
			getCurrentFunctionName(false);
			return selector;
	    }
	    
	    public static void doAppropriateAction(Map <String, Object> webElementInfo ) {
	    	try
	        {
	    		if (!SelectorUtil.isAnErrorSelector)
	    		{
			       if (((String) webElementInfo.get("action")).equals("type"))
			       {
			    	   SelTestCase.logs.debug("writing " + (String) webElementInfo.get("value") +" to "+ webElementInfo.get("by").toString());
			    	   SelTestCase.getDriver().findElement((By)webElementInfo.get("by")).sendKeys((String) webElementInfo.get("value"));
			       }
			       else if (((String) webElementInfo.get("action")).equals("click"))
			       {
			    	   SelTestCase.logs.debug("clicking on " +  webElementInfo.get("by").toString());
			    	   SelTestCase.getDriver().findElement((By)webElementInfo.get("by")).click();
			       }
			       else if (((String) webElementInfo.get("action")).equals("selectByText"))
			       {
			    	   SelTestCase.logs.debug("selecting value " + webElementInfo.get("value")); 
			    	   Select select = new Select(SelTestCase.getDriver().findElement((By)webElementInfo.get("by")));
			    	   select.selectByVisibleText((String) webElementInfo.get("value"));
			       }
		         }
	    		else if (((String) webElementInfo.get("action")).equals("Validate") && SelectorUtil.isAnErrorSelector)
	    		{
	    			if (!((String)webElementInfo.get("value")).isEmpty())
	    			{
	    				Assert.assertEquals("The "+ (String) webElementInfo.get("selector") + " has incorrect error msg",
	    						SelTestCase.getDriver().findElement((By)webElementInfo.get("by")).getText(),
	    						(String)webElementInfo.get("value"));
	    				SelTestCase.logs.debug("The "+ (String) webElementInfo.get("selector") + "is found and has correct error msg");
	    			}
	    		}
	        }
	    	catch (Exception e)
	    	{
	    		SelTestCase.logs.debug("=> Error in selecotr: " + e.getClass().getCanonicalName() +
	    				" = " +
	    				e.getMessage().split("\n")[0] );
	    	}
		
		}
}
