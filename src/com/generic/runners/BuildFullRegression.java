package com.generic.runners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import org.xml.sax.SAXException;

import com.generic.setup.Common;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.SASLogger;
import com.generic.util.TestUtilities;

public class BuildFullRegression extends SelTestCase {
	private static String[] runners = null;
	private static String[] browsers = null;
	private static String[] envs = null;
	private static String[] brands = null;
	private static ArrayList<String> regressionsPathes = new ArrayList<String>();
	// used sheet in test
	public static final String testDataSheet = SheetVariables.RunnersRegressionSheet;
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		try {
			Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
			try {
				runners = System.getenv("Runners").split(",");
			} catch (Exception e) {
				logs.debug("The evn var is not set for runners ... pulling data from sheet");
				runners = Common.readRunners();
			}

			if (runners[0].equals("all"))
				runners = "HomePageRegression,CLPRegression,CheckOutRegression,PDPRegression,PayPalCheckOutRegression,CartRegression,PLPRegression,RegistrationRegression,LoginRegression,GiftRegistryRegression"
						.split(",");

			Testlogs.get().debug("Started in Regression");

			///////////////////////////////////////////////////////////////////////////////////////////////////////

			try {
				brands = System.getenv("Brands").split(",");
			} catch (Exception e) {
				logs.debug("The evn var is not set for runners ... pulling data from sheet");
				brands = Common.readBrands();
			}

			///////////////////////////////////////////////////////////////////////////////////////////////////////
			try {
				envs = System.getenv("Envs").split(",");
			} catch (Exception e) {
				logs.debug("The evn var is not set for runners ... pulling data from sheet");
				envs = Common.readEnvs();
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////

			try {
				browsers = System.getenv("Browsers").split(",");
			} catch (Exception e) {
				logs.debug("The evn var is not set for browsers ... pulling data from sheet");
				browsers = Common.readBrowsers();
			}
			String regSearch = "Regression";
			for (String brandName : brands)
				for (String regressionName : runners) {

					if (brandName == null || regressionName == null)
						break;

					regSearch = "Regression";

					String regPath = regressionName;
					if (regressionName.equals("PayPal")) {
						regPath = "checkout";
						regSearch = "PayPalCheckoutRegression";
					}

					if (regressionName.equals("Search") || regressionName.equals("PLP")) {
						regPath = "Search_PLP";
						regSearch = regressionName + "Regression";
					}

					if (regressionName.equals("GiftRegistryRegression")) {
						regPath = "PDP";
						regSearch = "GRRegression";
					}

					if (regressionName.equals("PDP")) {
						regSearch = regressionName + "Regression";
					}

					if (regressionName.equals("checkout")) {
						regSearch = regressionName + "Regression";
					}

					if (Files.exists(Paths.get(System.getProperty("user.dir") + "\\" + brandName
							+ "\\com\\generic\\tests\\" + brandName + "\\" + regPath))) {

						logs.debug("Adding runner path to paths: " + regressionName);
						regressionsPathes
								.add(TestUtilities
										.searchSpecificFile(
												new File(System.getProperty("user.dir") + "\\" + brandName
														+ "\\com\\generic\\tests\\" + brandName + "\\" + regPath),
												regSearch));
					}

				}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@Test
	public void generateFinalRegressionXML() {
		try {
			TestUtilities.writeFinalXMLRegression(regressionsPathes, browsers, envs);
			Common.testPass();
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}

}
