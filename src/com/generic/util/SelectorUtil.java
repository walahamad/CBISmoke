package com.generic.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.generic.setup.ActionDriver;
import com.generic.setup.SelTestCase;


public class SelectorUtil extends SelTestCase {
	
	public static Boolean isAnErrorSelector = Boolean.FALSE;
	public static String textValue;
	
	 public static void initializeElementsSelectorsMaps(LinkedHashMap<String, LinkedHashMap> webElementsInfo , boolean isValidationStep) throws IOException
	 {
		 	getCurrentFunctionName(true);
	    	Elements foundElements = null;
	    	String selectorType = null;
	    	
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
				 if (foundElements.isEmpty())
	    	     {
	    	    	 //logs.debug("in xpath");
	    	    	 foundElements = doc.select("*:containsOwn("+ subStr +")");
	    	    	 selectorType = (!(foundElements.isEmpty()) ? subStr:selectorType);
	    	     }
				 
				 if (foundElements != null && !foundElements.isEmpty())
				 {
					  Map <String, Object> webElementInfo = webElementsInfo.get(subStr);
					  webElementInfo.put("selector",getStringSelectorForElements(foundElements, selectorType));
					  webElementInfo.put("SelType",selectorType);
					  webElementInfo.put("by",getBySelectorForElements(foundElements,selectorType));
					  webElementInfo.put("action",getActiontype(foundElements));
					  SelTestCase.logs.debug("Found a valid selector: " + Arrays.asList(webElementInfo));
				  
				 } 
				 else
				 {
					 logs.debug("XXX>> NO Valid Selector Found <<XXX");
				 }
			 index++;
			}
			getCurrentFunctionName(false);
	    	
	    }
	    
	    private static String getActiontype(Elements foundElements) {
			String ActionType = "";
			//TODO fix multiple elements 
			for (org.jsoup.nodes.Element e : foundElements) {
					//logs.debug(e.toString());//Debugging purposes
				
					if (e.tagName().equals("input") && (e.attr("type").equals("text") || e.attr("type").equals("password") )) {
						return "type";
					} else if (e.tagName().equals("select")) {
						return "selectByText";
					} else if (e.tagName().equals("input") && e.attr("type").equals("checkbox") ){
				        	return "check";
					} else if (e.tagName().equals("button") ||
							e.tagName().equals("img") ||
							e.tagName().equals("a") )
					{
						return "click";
					} else if (e.tagName().equals("input") && e.attr("type").equals("submit")) {
						return "click";
					}
					else if (e.tagName().equals("p")) {
						return "gettext";
					}else if (e.tagName().equals("div"))
					{
						return "click,gettext";
					}
					else
					{
						logs.debug("Returning the default action");
						return "Validate"; 
					}
			}
    	return ActionType;
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
					case "title":
						selector = By.xpath("//*[contains(@"+selType+",'"+ e.attr(selType) + "')]");
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
					case "title":
						selector = "//*[contains(@"+selType+",'"+ e.attr(selType) + "')]";
						break;
						
					default:
						selector = "//button[contains(@text,'"+ selType + "')]";
						break;
					}
				}
			getCurrentFunctionName(false);
			return selector;
	    }
	    
	    public static String doAppropriateAction(Map <String, Object> webElementInfo ) throws Exception {
	    	getCurrentFunctionName(true);
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
						   JavascriptExecutor jse = (JavascriptExecutor)getDriver();
						   jse.executeScript("arguments[0].scrollIntoView()", getDriver().findElement(byAction)); 
						   ActionDriver.click(byAction);
					   }
					   else if (action.equals("check"))
					   {
						   WebElement checkboxE = getDriver().findElement(byAction);  
						   logs.debug("check box  " +  byAction.toString() + " " + value);
						   if(value.contains("true"))
						   {
							   if (!checkboxE.isSelected())
							   {
								   logs.debug("checking the check box- check box status is not checked");
								   getDriver().findElement(byAction).click();
							   }
							   else
							   {
								   logs.debug("checking the check box- check box status is checked");
							   }
						   }
						   else
						   {
							   if (checkboxE.isSelected())
							   {
								   logs.debug("unchecking the check box- check box status is checked");
								   getDriver().findElement(byAction).click();
							   }
							   else
							   {
								   logs.debug("checking the check box- check box status is checked");
							   }
						   }
					   }
					   else if (action.equals("gettext"))
					   {
						   logs.debug("getting txt from " +  byAction.toString());
						   return getDriver().findElement(byAction).getText();
					   }
					   else if (action.equals("click,gettext"))
					   {
						   logs.debug("getting txt, click from " +  byAction.toString());
						   
						   WebElement element = getDriver().findElement(byAction);
						   
						   JavascriptExecutor jse = (JavascriptExecutor)getDriver();
						   jse.executeScript("arguments[0].scrollIntoView()", element); 
						   
						   String textVal= "";
						   try 
						   {
							   textVal = element.getText();
						   }catch(Exception e)
						   {
						   		logs.debug("Failed to get text");
						   }
						   try 
						   {
							   element.click();
						   }catch(Exception e)
						   {
						   		logs.debug("Failed to click");
						   }
						   
						   return textVal;
					   }
					   else if (action.equals("selectByText"))
					   {
						   logs.debug("selecting value " + byAction.toString()); 
						   Select select = new Select(getDriver().findElement(byAction));
						   
//						   //Keep the block below for debugging purposes  
//						   List<WebElement> options = select.getOptions();
//						   logs.debug(options.toString());
//						   for(int i=1; i<options.size(); i++) {
//						   	    logs.debug(options.get(i).getText());
//							}
						   
						   try {
							   select.selectByVisibleText(value);
						   }
						   catch(Exception e)
						   {
							   logs.debug("Try alternative way: ");
							   List<WebElement> options = select.getOptions();
							   for(int i=0; i<options.size(); i++)
							   {
								  // logs.debug(options.get(i).getText().trim());
							   	    if (options.get(i).getText().toLowerCase().trim().contains(value.toLowerCase()))
							   	    {
										logs.debug("selected index is" + i ); 
							   	    	select.selectByIndex(i);
							   	    }
							   	}
						   }
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
	    		else
	    		{
	    			//TODO: edit message 
	    			assertNull("no operation nor selctor found");
	    		}
	        }
	    	catch (Exception e)
	    	{
	    		SelTestCase.logs.debug("XXX>> Error in selecotr: " + e.getClass().getCanonicalName() +
	    				" = " +
	    				e.getMessage().split("\n")[0] );
	    		throw e; 
	    	}
	    	getCurrentFunctionName(false);
	    	return "";
		}
	    
	    
		public static void initializeSelectorsAndDoActions(List<String> subStrArr, List<String> valuesArr) throws Exception {
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
