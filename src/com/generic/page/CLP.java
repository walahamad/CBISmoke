package com.generic.page;


import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;

import com.generic.selector.CLPSelectors;
import com.generic.selector.HomePageSelectors;
import com.generic.setup.EnvironmentFiles;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
/**
 * The Class HomePage.
 */
public class CLP extends SelTestCase {
	
	public static void prepareBaselineforLogs(String baseline) throws Exception {
		String VTAs =EnvironmentFiles.getVisualTestingAssetsPath();
		String baselineAbsPath = VTAs + "/" + baseline+ ".png";
		String logs_dir = EnvironmentFiles.getLogFilePath();	
    	File baseLineFile = new File(baselineAbsPath);
		FileUtils.copyFileToDirectory(baseLineFile, Paths.get(logs_dir).toFile());
		String baselinePathInLogs =  logs_dir + "/" + baseline + ".png";
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline + ".png"+"><img src=" + baseline + ".png"+" alt=" + baseline + ".png"+" style=\"width:150px\"></a>");
	}
	
	public static void updateHeaderBaseline(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(CLPSelectors.header);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+".png"+"><img src=" + baseline+".png"+" alt=" + baseline+".png"+" style=\"width:150px\"></a>");
		getCurrentFunctionName(false);
	}

	public static boolean verifyHeader(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(CLPSelectors.header);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getLogFilePath() + "/" + baseline+"_actual.png";
		BufferedImage actualImage = SelectorUtil.screenShot.get().getImage();
		
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+"_actual.png"+"><img src=" + baseline+"_actual.png"+" alt=" + baseline+"_actual.png"+" style=\"width:150px\"></a>");
		
		String BaseImagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		
		BufferedImage expectedImage = ImageIO.read(new File(BaseImagePath));
        
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		
		getCurrentFunctionName(false);
		return !diff.hasDiff();
	}
	public static boolean validateMobileIpadCLP() throws Exception {
		getCurrentFunctionName(true);
		boolean isValid = true;
		HomePage.openNavigationMenu();
		String pageUrl = SelectorUtil.getCurrentPageUrl();// to validate iPad
		List<WebElement> menueItems = new ArrayList<WebElement>();
		menueItems =  menueWithoutWhatsNew();
		WebElement randomMenuElement =  SelectorUtil.getRandomWebElement(menueItems);
		SelectorUtil.clickOnWebElement(randomMenuElement);
		// Navigate to leafMenuItems  items.
		List<WebElement> leafMenuItems = new ArrayList<WebElement>();
		leafMenuItems = getElementsList(HomePageSelectors.leafMenuItems.get());
	
		WebElement viewAllElement =  leafMenuItems.get(0);//get first index "view all" 
	
		boolean isIphone = getBrowserName().contains(GlobalVariables.browsers.iPhone) || getBrowserName().contains(GlobalVariables.browsers.Nexus);
		boolean validateViewAllElement = true;
		if (isIphone) {
			validateViewAllElement = SelectorUtil.isValidClickableItem(viewAllElement); 
		} else {// Check if the current page URL different than the previous page URL for iPad ; items didn't contains Href attribute	
			SelectorUtil.clickOnWebElement(viewAllElement);
			String currentPageUrl = SelectorUtil.getCurrentPageUrl();
			if (pageUrl.equalsIgnoreCase(currentPageUrl)){
				validateViewAllElement = false;
			}
		}
		if (validateViewAllElement) {
			isValid = validatePLP();
		}
		getCurrentFunctionName(false);

		return isValid;
	}


	public static boolean validateDesktopCLP() throws Exception {
		getCurrentFunctionName(true);
		boolean isValid = true;
		List<WebElement> menutems = new ArrayList<WebElement>();
		menutems = menueWithoutWhatsNew();
		WebElement menuElement =  SelectorUtil.getRandomWebElement(menutems);
		boolean isValidClickableElement = SelectorUtil.isValidClickableItem(menuElement);
		if (isValidClickableElement) {
			if (validatePLP()) { 
				isValid = true;
			}else {
				isValid = false;
			}
		}else {
			isValid = false;
		}
		getCurrentFunctionName(false);
		return isValid;
	}
	public static boolean validatePLP() throws Exception {
		getCurrentFunctionName(true);
		boolean isValid = true;
		List<WebElement> items = new ArrayList<WebElement>();
		items = getElementsList(CLPSelectors.CLPItems.get());
		WebElement PLPElement =  SelectorUtil.getRandomWebElement(items);
		// Get the current page URL.		
		boolean isValidClickableElement = SelectorUtil.isValidClickableItem(PLPElement);
		if (isValidClickableElement) { 
			isValid = true;
		}else {
			isValid = false;
		}
		getCurrentFunctionName(false);
		return isValid;
	}
	public static List<WebElement> menueWithoutWhatsNew() throws Exception {
		List<WebElement> items = new ArrayList<WebElement>();
		items = getElementsList(HomePageSelectors.menuItems.get());
		items.remove(0);//remove what's New item : First item
		return items;
	}
	
	public static List<WebElement> getElementsList(String selector) throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> elementsList = SelectorUtil.getAllElements(selector);
		getCurrentFunctionName(false);
		return elementsList;
	}
	
	
	
}
