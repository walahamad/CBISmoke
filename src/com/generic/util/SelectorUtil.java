package com.generic.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
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

import com.generic.setup.SelTestCase;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;


public class SelectorUtil extends SelTestCase {
	
	public static Boolean isAnErrorSelector = Boolean.FALSE;
	public static String textValue;
	public static int numberOfFoundElements;
	private static By parentBy = null;
	
	public static void initializeElementsSelectorsMaps(LinkedHashMap<String, LinkedHashMap> webElementsInfo , boolean isValidationStep) throws IOException
	 {
		Document doc = Jsoup.parse(SelTestCase.getDriver().getPageSource());
		Element htmlDoc = doc.select("html").first();
		initializeElementsSelectorsMaps(webElementsInfo, isValidationStep, htmlDoc);
	 }
	
	 public static void initializeElementsSelectorsMaps(LinkedHashMap<String, LinkedHashMap> webElementsInfo , boolean isValidationStep, Element htmlDoc) throws IOException
	 {
		 	getCurrentFunctionName(true);
	    	Elements foundElements = null;
	    	String selectorType = null;
	    	
	    	
			for(String subStr: webElementsInfo.keySet()) {
				 if (subStr.contains("error") && !isAnErrorSelector && isValidationStep) {
				  isAnErrorSelector = Boolean.TRUE;
				 }
				 foundElements = htmlDoc.select("[id="+subStr+"]");
				 selectorType = (!(foundElements.isEmpty()) ? "id":selectorType);
				 
				 if (foundElements.isEmpty())
				 {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "exact class"));
					 foundElements = htmlDoc.select("[class="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
				 }
				 
				 if (foundElements.isEmpty())
				 {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "i class"));
					 foundElements = htmlDoc.select("[class="+subStr+" i]");
					 selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
				 }
				 
				 if (foundElements.isEmpty())
				 {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "exact name"));
					 foundElements = htmlDoc.select("[name="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "name":selectorType);
				 }
				 
				if(foundElements.isEmpty())
				 {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*id"));
				  //use regular expression (register.)?firstName for example
				  foundElements = htmlDoc.select("[id~=(register.)?"+subStr+"$]");
				  selectorType = (!(foundElements.isEmpty()) ? "id":selectorType);
				 }
				 if (foundElements.isEmpty())
				 {
					 //logs.debug("in *id");
					 foundElements = htmlDoc.select("[id*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "id":selectorType);
				 }
				 if (foundElements.isEmpty())
				 {
				  //use * to mean contains
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*class"));
					 foundElements = htmlDoc.select("[class*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "class":selectorType);
				 }
				 if (foundElements.isEmpty()) {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*name"));
					 foundElements = htmlDoc.select("[name*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "name":selectorType);
				 }
				 if (foundElements.isEmpty())
				 {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*title"));  
					 foundElements = htmlDoc.select("[title*="+subStr+"]");
					 selectorType = (!(foundElements.isEmpty()) ? "title":selectorType);
				 }
				 if (foundElements.isEmpty())
	    	     {
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "xpath"));
	    	    	 foundElements = htmlDoc.select("*:containsOwn("+ subStr +")");
	    	    	 selectorType = (!(foundElements.isEmpty()) ? subStr:selectorType);
	    	     }
				 
				 if (foundElements.isEmpty() && subStr.contains(":eq") )
	    	     { //TODO: check the if (eq) case 
					//logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "xpath"));
	    	    	 foundElements = htmlDoc.select(subStr);
	    	    	 //nth-child() is the Selenium equivalent to JSoup eq()
	    	    	 String subStrTemp = subStr;
	    	    	 if (subStr.contains(":eq")) {
	    	    		 int startIndex = subStr.indexOf("(")+1;
	    	    		 int endIndex = subStr.indexOf(")");
	    	    		 String nthIndex = subStr.substring(startIndex, endIndex);
	    	    		 //eq() is zero-base but nth-child() is one-base
	    	    		 int nthIndexVal = Integer.parseInt(nthIndex) + 1;
	    	    		 subStrTemp = subStr.replace(nthIndex, ""+nthIndexVal);
	    	    		 subStrTemp = subStrTemp.replace(":eq", ":nth-child");
	    	    	 }
	    	    	 String selType = "css," + subStrTemp;
	    	    	 selectorType = (!(foundElements.isEmpty()) ? selType:selectorType);
	    	     }
				 
				 //The following if is used to get the parent element from which we do a recursive call to get the child on which is our target
				 if (webElementsInfo.get(subStr) != null && (webElementsInfo.get(subStr).get("value")).toString().contains("child") && !foundElements.isEmpty()) {
					 String tempValue = (webElementsInfo.get(subStr).get("value")).toString();
					 Element e = foundElements.first();
					 String childSelStr = tempValue.split(",")[1];
					 List<String> subStrArr = new ArrayList<String>();
					 List<String> valuesArr = new ArrayList<String>();
					 subStrArr.add(childSelStr);
					 valuesArr.add("");
					 LinkedHashMap<String, Object> webElementInfo = new LinkedHashMap<>();
					 webElementInfo.put("value", "");
					 webElementInfo.put("selector", "");
					 webElementInfo.put("action", "");
					 webElementInfo.put("SelType", "");
						
					 webElementsInfo.remove(childSelStr);
					 webElementsInfo.remove(subStr);
					 webElementsInfo.put(childSelStr, webElementInfo);
					//Keeping this for Debugging purposes.
					 logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));
					 //parentBy is essential for driver getElement function used in doAppropriateAction() function to get the same element found by JSoup
					 parentBy = getBySelectorForElements(foundElements,selectorType);
					 //parent element found should be set to null as we don't have to do the if following this 
					 foundElements = null;
					 SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo, isValidationStep, e);
					 logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));
					 
				 }
				 
				 if (foundElements != null && !foundElements.isEmpty())
				 {
					  Map <String, Object> webElementInfo = webElementsInfo.get(subStr);
					  webElementInfo.put("selector",getStringSelectorForElements(foundElements, selectorType));
					  webElementInfo.put("SelType",selectorType);
					  webElementInfo.put("by",getBySelectorForElements(foundElements,selectorType));
					  webElementInfo.put("action",getActiontype(foundElements));
					  webElementInfo.put("parentBy", parentBy);
					  parentBy = null;
					  numberOfFoundElements = foundElements.size();
					  SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.VALID_SEL_MSG, Arrays.asList(webElementInfo)));
				  
				 } 
				 else
				 {
					 logs.debug(LoggingMsg.NO_VALID_SEL_ERROR_MSG);
				 }
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
							e.tagName().equals("body") || e.tagName().equals("td")) {
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
	    
	    public static void doAppropriateAction(Map <String, Object> webElementInfo ) throws Exception {
	    	getCurrentFunctionName(true);
	    	SelectorUtil.textValue = "";
	    	try
	        {
	    		String selector = (String) webElementInfo.get("selector");
	    		String action = (String) webElementInfo.get("action");
	    		String value = (String) webElementInfo.get("value");
	    		By byAction = (By) webElementInfo.get("by");
	    		By parentBy = (By) webElementInfo.get("parentBy");
	    		WebElement parent = null;
	    		WebElement field = null;
	    		// get element using parent-child relationship OR DOM-element relationship
	    		if (byAction != null) {
		    		if (parentBy != null) {
		    			parent = getDriver().findElement(parentBy);
		    			field = parent.findElement(byAction);
		    		} else {
		    			//used to get the element at specific index when there are multiple elements of the same selector
		    			if (value.contains("index")) {
		    				int elementIndex = Integer.parseInt(value.split(",")[1]);
		    				field = getDriver().findElements(byAction).get(elementIndex);
		    			} else {
		    				field = getDriver().findElement(byAction);
		    			}
		    		}
	    		}
	    		if (!selector.equals(""))
	    		{
		    		if (!SelectorUtil.isAnErrorSelector)
		    		{
					   if (action.equals("type"))
					   {
						  if (value.contains("getCurrentValue")) {
							  logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL,"txt", byAction.toString()));
							  JavascriptExecutor jse = (JavascriptExecutor)getDriver();
							   jse.executeScript("arguments[0].scrollIntoView()", field);
							  // I used the value attr instead of getText() as the input has the text as a value
							   SelectorUtil.textValue = field.getAttribute("value");
						  } else {

							  logs.debug(MessageFormat.format(LoggingMsg.WRITING_TO_SEL, "", value, byAction.toString()));
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
					   }
					   else if (action.equals("click"))
					   {
						   logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
						   JavascriptExecutor jse = (JavascriptExecutor)getDriver();
						   jse.executeScript("arguments[0].scrollIntoView()", field); 
						   //ActionDriver.click(byAction);
						   field.click();
					   }
					   else if (action.equals("check"))
					   {
						   logs.debug(MessageFormat.format(LoggingMsg.CHECKBOX_SEL_VAL, byAction.toString(), value));
						   if(value.contains("true"))
						   {
							   if (!field.isSelected())
							   {
								   logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", "not "));
								   field.click();
							   }
							   else
							   {
								   logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", ""));
							   }
						   }
						   else
						   {
							   if (field.isSelected())
							   {
								   logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG,"un",""));
								   field.click();
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
						   SelectorUtil.textValue = field.getText();
					   }
					   else if (action.equals("click,gettext"))
					   {
						   logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, "txt, click", byAction.toString()));
						   
						   JavascriptExecutor jse = (JavascriptExecutor)getDriver();
						   jse.executeScript("arguments[0].scrollIntoView()", field); 
						   
						   String textVal= "";
						   try 
						   {
							   textVal = field.getText();
							   SelectorUtil.textValue = textVal;
						   }catch(Exception e)
						   {
						   		logs.debug(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "get text"));
						   		throw new NoSuchElementException(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "get text"));
						   		
						   }
						   try 
						   {
							   if (value.isEmpty()) {
								   field.click(); 
							   }
						   }catch(Exception e)
						   {
						   		logs.debug(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "click"));
						   		throw new NoSuchElementException(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "click"));
						   }
						   
					   }
					   else if (action.equals("selectByText"))
					   {
						   logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "value", byAction.toString())); 
						   Select select = new Select(field);
						   
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
						   SelectorUtil.textValue = textVal;
					   }
		    		}
		    		else if (action.equals("Validate") && SelectorUtil.isAnErrorSelector)
		    		{
				       if (!value.isEmpty())
				       {
				        Assert.assertEquals("The "+ selector + " has incorrect error msg", field.getText(), value);
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
					SelectorUtil.doAppropriateAction(webElementInfo);
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
