package com.generic.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.generic.setup.ActionDriver;
import com.generic.setup.SelTestCase;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;


public class SelectorUtil extends SelTestCase {
	
	public static Boolean isAnErrorSelector = Boolean.FALSE;
	public static String textValue;
	public static int numberOfFoundElements;
	
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
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "exact class"));
					 foundElements = doc.select("[class="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
				 }
				 
				 if (foundElements.isEmpty())
				 {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "i class"));
					 foundElements = doc.select("[class="+subStr+" i]");
					 selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
				 }
				 
				 if (foundElements.isEmpty())
				 {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "exact name"));
					 foundElements = doc.select("[name="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "name":selectorType);
				 }
				 
				 if(foundElements.isEmpty())
				 {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*id"));
				  //use regular expression (register.)?firstName for example
				  foundElements = doc.select("[id~=(register.)?"+subStr+"$]");
				  selectorType = (!(foundElements.isEmpty()) ? "id":selectorType);
				 }
				 if (foundElements.isEmpty())
				 {
					 //logs.debug("in *id");
					 foundElements = doc.select("[id*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "id":selectorType);
				 }
				 if (foundElements.isEmpty())
				 {
				  //use * to mean contains
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*class"));
					 foundElements = doc.select("[class*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
				 }
				 if (foundElements.isEmpty()) {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*name"));
					 foundElements = doc.select("[name*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "name":selectorType);
				 }
				 if (foundElements.isEmpty())
				 {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*title"));  
					 foundElements = doc.select("[title*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "title":selectorType);
				 }
				 if (foundElements.isEmpty())
	    	     {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "xpath"));
	    	    	 foundElements = doc.select("*:containsOwn("+ subStr +")");
	    	    	 selectorType = (!(foundElements.isEmpty()) ? subStr:selectorType);
	    	     }
				 
				 if (foundElements.isEmpty())
	    	     {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "xpath"));
	    	    	 foundElements = doc.select(subStr);
	    	    	 String selType = "css," + subStr;
	    	    	 selectorType = (!(foundElements.isEmpty()) ? selType:selectorType);
	    	     }
				 
				 if ((webElementsInfo.get(subStr).get("value")).toString().contains("child") && !foundElements.isEmpty()) {
					 String tempValue = (webElementsInfo.get(subStr).get("value")).toString();
					 Element e = foundElements.first();
					 String childSelStr = tempValue.split(",")[1];
					 foundElements = e.select("[id="+ childSelStr  +"]");
					 selectorType = (!(foundElements.isEmpty()) ? "id":selectorType);
				 }
				 
				 if (foundElements != null && !foundElements.isEmpty())
				 {
					  Map <String, Object> webElementInfo = webElementsInfo.get(subStr);
					  webElementInfo.put("selector",getStringSelectorForElements(foundElements, selectorType));
					  webElementInfo.put("SelType",selectorType);
					  webElementInfo.put("by",getBySelectorForElements(foundElements,selectorType));
					  webElementInfo.put("action",getActiontype(foundElements));
					  numberOfFoundElements = foundElements.size();
					  SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.VALID_SEL_MSG, Arrays.asList(webElementInfo)));
				  
				 } 
				 else
				 {
					 logs.debug(LoggingMsg.NO_VALID_SEL_ERROR_MSG);
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
				
					if (e.tagName().equals("input") && (e.attr("type").equals("text") || e.attr("type").equals("password") || e.attr("type").equals("") )) {
						return "type";
					} else if (e.tagName().equals("select")) {
						return "selectByText";
					} else if (e.tagName().equals("input") && e.attr("type").equals("checkbox") ){
				        	return "check";
					} else if (e.tagName().equals("button") ||
							e.tagName().equals("img") ||
							e.tagName().equals("a")||
							e.tagName().equals("li") ||
							e.tagName().equals("form"))
					{
						return "click";
					} else if (e.tagName().equals("input") && e.attr("type").equals("submit")) {
						return "click";
					}
					else if (e.tagName().equals("p")||
							e.tagName().equals("body")) {
						return "gettext";
					}else if (e.tagName().equals("div") || e.tagName().equals("span"))
					{
						return "click,gettext";
					}
					else
					{
						logs.debug(LoggingMsg.DEFAULT_ACTION_MSG);
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
						if (selType.contains("css")) {
							String selTemp = selType.split(",")[1];
							selector = By.cssSelector(selTemp);
						} else {
							selector = By.xpath("//*[contains(text(),'"+ selType + "')]");
						}
						break;
					}
		    		
				}
			return selector;
	    	
	    }
	    

	    public static String getStringSelectorForElements(Elements foundElements, String selType) {
	    	getCurrentFunctionName(true);
	    	
	    	logs.debug(MessageFormat.format(LoggingMsg.SEL_TYPE, selType));
	    	
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
						if (selType.contains("css")) {
							String selTemp = selType.split(",")[1];
							selector = selTemp;
						} else {
							selector = "//*[contains(@text,'"+ selType + "')]";
						}
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
						  logs.debug(MessageFormat.format(LoggingMsg.WRITING_TO_SEL, "", value, byAction.toString()));
						  WebElement field = getDriver().findElement(byAction);
						  field.clear();
						  String tempVal = value;
						  if (value.contains("pressEnter")) {
							  tempVal = value.split(",")[0];
						  }
						  field.sendKeys(tempVal);
						  if (!tempVal.equals(value)) {
							field.sendKeys(Keys.ENTER);  
						  }
					   }
					   else if (action.equals("click"))
					   {
						   logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
						   JavascriptExecutor jse = (JavascriptExecutor)getDriver();
						   jse.executeScript("arguments[0].scrollIntoView()", getDriver().findElement(byAction)); 
						   ActionDriver.click(byAction);
					   }
					   else if (action.equals("check"))
					   {
						   WebElement checkboxE = getDriver().findElement(byAction);  
						   logs.debug(MessageFormat.format(LoggingMsg.CHECKBOX_SEL_VAL, byAction.toString(), value));
						   if(value.contains("true"))
						   {
							   if (!checkboxE.isSelected())
							   {
								   logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", "not "));
								   getDriver().findElement(byAction).click();
							   }
							   else
							   {
								   logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", ""));
							   }
						   }
						   else
						   {
							   if (checkboxE.isSelected())
							   {
								   logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG,"un",""));
								   getDriver().findElement(byAction).click();
							   }
							   else
							   {
								   logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", ""));
							   }
						   }
					   }
					   else if (action.equals("gettext"))
					   {
						   logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL,"txt", byAction.toString()));
						   return getDriver().findElement(byAction).getText();
					   }
					   else if (action.equals("click,gettext"))
					   {
						   logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, "txt, click", byAction.toString()));
						   
						   WebElement element = getDriver().findElement(byAction);
						   
						   JavascriptExecutor jse = (JavascriptExecutor)getDriver();
						   jse.executeScript("arguments[0].scrollIntoView()", element); 
						   
						   String textVal= "";
						   try 
						   {
							   textVal = element.getText();
						   }catch(Exception e)
						   {
						   		logs.debug(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "get text"));
						   }
						   try 
						   {
							   if (value.isEmpty()) {
								   element.click(); 
							   }
						   }catch(Exception e)
						   {
						   		logs.debug(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "click"));
						   }
						   
						   return textVal;
					   }
					   else if (action.equals("selectByText"))
					   {
						   logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "value", byAction.toString())); 
						   Select select = new Select(getDriver().findElement(byAction));
						   
//						   //Keep the block below for debugging purposes  
//						   List<WebElement> options = select.getOptions();
//						   logs.debug(options.toString());
//						   for(int i=1; i<options.size(); i++) {
//						   	    logs.debug(options.get(i).getText());
//							}
						   String textVal= "";
						   try {
							   if (!value.isEmpty()) {
								   select.selectByVisibleText(value); 
							   } else {
								   textVal = select.getFirstSelectedOption().getText();
							   }
							   
						   }
						   catch(Exception e)
						   {
							   logs.debug(LoggingMsg.TRY_ALT_WAY_MSG);
							   List<WebElement> options = select.getOptions();
							   for(int i=0; i<options.size(); i++)
							   {
								  // logs.debug(options.get(i).getText().trim());
							   	    if (options.get(i).getText().toLowerCase().trim().contains(value.toLowerCase()))
							   	    {
										logs.debug(MessageFormat.format(LoggingMsg.SELECTED_INDEX, i )); 
							   	    	select.selectByIndex(i);
							   	    }
							   	}
						   }
						   return textVal;
					   }
		    		}
		    		else if (action.equals("Validate") && SelectorUtil.isAnErrorSelector)
		    		{
				       if (!value.isEmpty())
				       {
				        Assert.assertEquals("The "+ selector + " has incorrect error msg", getDriver().findElement(byAction).getText(), value);
				        logs.debug(MessageFormat.format(LoggingMsg.ERROR_VERIFICATION_SEL_MSG, selector));
				       }
		    		}
	    		}
	    		else
	    		{
	    			throw new Exception (ExceptionMsg.noValidSelector);
	    		}
	        }
	    	catch (Exception e)
	    	{
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
			try
			{
				//Keeping this for Debugging purposes.
				logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));
				SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo, isValidationStep);
				logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));
	
				for (String key : webElementsInfo.keySet()) {
					LinkedHashMap<String, Object> webElementInfo = webElementsInfo.get(key);
					textValue = SelectorUtil.doAppropriateAction(webElementInfo);
				}
	
				
				Thread.sleep(1000);
			}catch(Exception e)
			{
				logs.debug(MessageFormat.format(LoggingMsg.FORMATTED_ERROR_MSG, e.getMessage()));
				throw new NoSuchElementException("No such element: "  + Arrays.asList(webElementsInfo)); 
				
			}finally
			{
				valuesArr.clear();
				subStrArr.clear();
			}
			
			
			logs.debug(MessageFormat.format(LoggingMsg.FINISHED_ACTION_ON_ELEMENTS_MSG, Arrays.asList(webElementsInfo)));

		}
}
