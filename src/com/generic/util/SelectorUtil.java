package com.generic.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.generic.setup.SelTestCase;


public class SelectorUtil extends SelTestCase {
	
	public static Boolean isAnErrorSelector = Boolean.FALSE;
	public static String textValue;
	
	 public static void initializeElementsSelectorsMaps(LinkedHashMap<String, LinkedHashMap> webElementsInfo , boolean isValidationStep) throws IOException
	 {
		 	getCurrentFunctionName(true);
	    	Elements foundElements = null;
	    	String selectorType = null;
//	    	try {
//				enableSSLSocket();
//			} catch (KeyManagementException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NoSuchAlgorithmException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    	
	    	Document doc = Jsoup.parse(SelTestCase.getDriver().getPageSource());
			int index = 0;
			
			
			for(String subStr: webElementsInfo.keySet()) {
				 if (subStr.contains("error") && !isAnErrorSelector && isValidationStep) {
				  isAnErrorSelector = Boolean.TRUE;
				 }
				 foundElements = doc.select("[id="+subStr+"]");
				 selectorType = (!(foundElements.isEmpty()) ? "id":selectorType);
				 
				 if (foundElements.isEmpty())
				 {
					 //logs.debug("in exact class");
					 foundElements = doc.select("[class="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
				 }
				 
				 if (foundElements.isEmpty())
				 {
					 //logs.debug("in i class");
					 foundElements = doc.select("[class="+subStr+" i]");
					 selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
				 }
				 
				 if (foundElements.isEmpty())
				 {
					 //logs.debug("in exact name");
					 foundElements = doc.select("[name="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "name":selectorType);
				 }
				 
				 if(foundElements.isEmpty())
				 {
					 //logs.debug("in *id");
				  //use regular expression (register.)?firstName for example
				  foundElements = doc.select("[id~=(register.)?"+subStr+"$]");
				  selectorType = (!(foundElements.isEmpty()) ? "id":selectorType);
				 }
				 
				 if (foundElements.isEmpty())
				 {
				  //use * to mean contains
					 //logs.debug("in *class");
					 foundElements = doc.select("[class*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
				 }
				 
	    	     if (foundElements.isEmpty())
	    	     {
	    	    	 //logs.debug("in xpath");
	    	    	 foundElements = doc.select("*:contains("+ subStr +")");
	    	    	 selectorType = (!(foundElements.isEmpty()) ? subStr:selectorType);
	    	     }
	    	     
				 if (foundElements.isEmpty()) {
					 //logs.debug("in *name");
					 foundElements = doc.select("[name*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "name":selectorType);
				 }
				 if (foundElements.isEmpty())
				 {
					 //logs.debug("in *title"); 
					 foundElements = doc.select("[title*="+subStr+"]");
					  selectorType = (!(foundElements.isEmpty()) ? "title":selectorType);
				 }
				 
				 
				 if (foundElements != null && !foundElements.isEmpty()) {
					  Map <String, Object> webElementInfo = webElementsInfo.get(subStr);
					  webElementInfo.put("selector",getStringSelectorForElements(foundElements, selectorType));
					  webElementInfo.put("SelType",selectorType);
					  webElementInfo.put("by",getBySelectorForElements(foundElements,selectorType));
					  webElementInfo.put("action",getActiontype(foundElements));
					  SelTestCase.logs.debug("Found a valid selector: " + Arrays.asList(webElementInfo));
				  
				  if (foundElements.size() ==0)
				  {
					  logs.debug("NO Valid Selector Found");
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
					} else if (e.tagName().equals("button") ||
							e.tagName().equals("img") ||
							e.tagName().equals("a") ||
							e.tagName().equals("div"))
					{
						return "click";
					} else if (e.tagName().equals("input") && e.attr("type").equals("submit")) {
						return "click";
					}
					else if (e.tagName().equals("p")) {
						return "gettext";
					}
					else
					{
						logs.debug("Returning the default action");
						return "Validate"; 
					}
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
	    
	    public static String doAppropriateAction(Map <String, Object> webElementInfo ) {
	    	try
	        {
	    		String selector = (String) webElementInfo.get("selector");
	    		String action = (String) webElementInfo.get("action");
	    		String value = (String) webElementInfo.get("value");
	    		By byAction = (By) webElementInfo.get("by");
	    		
	    		
	    		if (!selector.equals(""))
	    		{
		    		if (!SelectorUtil.isAnErrorSelector)
		    		{
					   if (action.equals("type"))
					   {
						  logs.debug("writing " + value +" to "+ byAction.toString());
						  WebElement field = getDriver().findElement(byAction);
						  field.clear();
						  field.sendKeys(value);
					   }
					   else if (action.equals("click"))
					   {
						   logs.debug("clicking on " +  byAction.toString());
						   getDriver().findElement(byAction).click();
					   }
					   else if (action.equals("gettext"))
					   {
						   logs.debug("getting txt from " +  byAction.toString());
						   return getDriver().findElement(byAction).getText();
					   }
					   else if (action.equals("selectByText"))
					   {
						   logs.debug("selecting value " + byAction.toString()); 
						   Select select = new Select(getDriver().findElement(byAction));
						   
						   //Keep the block below for debugging purposes  
						   //List<WebElement> options = select.getOptions();
						   //logs.debug(options.toString());
						   //for(int i=1; i<options.size(); i++) {
						   //	    logs.debug(options.get(i).getText());
							//}
						   
						   select.selectByVisibleText(value);
					   }
		    		}
		    		else if (action.equals("Validate") && SelectorUtil.isAnErrorSelector)
		    		{
				       if (!value.isEmpty())
				       {
				        Assert.assertEquals("The "+ selector + " has incorrect error msg", getDriver().findElement(byAction).getText(), value);
				        logs.debug("The "+ selector + " is found and has correct error msg");
				       }
		    		}
	    		}
	        }
	    	catch (Exception e)
	    	{
	    		SelTestCase.logs.debug("=> Error in selecotr: " + e.getClass().getCanonicalName() +
	    				" = " +
	    				e.getMessage().split("\n")[0] );
	    	}
	    	return "";
		}
	    
	    
		public static void initializeSelectorsAndDoActions(List<String> subStrArr, List<String> valuesArr) throws InterruptedException, IOException {
			LinkedHashMap<String, LinkedHashMap> webElementsInfo = new LinkedHashMap<String, LinkedHashMap>();
			
			int index = 0;
			boolean isValidationStep = false;
			for (String key : subStrArr) {
				//logs.debug(key);
				LinkedHashMap<String, Object> webElementInfo = new LinkedHashMap<>();
				webElementInfo.put("value", valuesArr.get(index));
				webElementInfo.put("selector", "");
				webElementInfo.put("action", "");
				webElementInfo.put("SelType", "");
				index++;

				webElementsInfo.remove(key);
				webElementsInfo.put(key, webElementInfo);
			}
			//Keeping this for Debugging purposes.  
			logs.debug(Arrays.asList(webElementsInfo));
			SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo, isValidationStep);
			logs.debug(Arrays.asList(webElementsInfo));

			for (String key : webElementsInfo.keySet()) {
				LinkedHashMap<String, Object> webElementInfo = webElementsInfo.get(key);
				textValue = SelectorUtil.doAppropriateAction(webElementInfo);
			}

			
			Thread.sleep(1000);
			
			valuesArr.clear();
			subStrArr.clear();
			
			logs.debug("FINISHED doing correct action on elements: " +Arrays.asList(webElementsInfo));

		}
}
