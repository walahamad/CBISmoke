package com.generic.setup;

import org.testng.util.Strings;

public class EnvironmentFiles {
	//config.properties file path,getter and setter
	public static String configFilePath = System.getProperty("user.dir")+"//src//com//generic//config//config.properties";
	//DataSheet.xlsx file path,getter and setter
	public static String dataSheetPath = System.getProperty("user.dir")+"//src//com//generic//config//DataSheet.xlsx";
	//log file path,getter and setter
	public static String logFilePath = null;
	//log file name,getter and setter
	public static String logFileName = "Application.log";
	//reports folder directory path,getter and setter
	public static String reportsFolderPath = null;
	//Failed Test Case Template HTML File
	public static String failedTestCaseReportTemplate = System.getProperty("user.dir")+"//src//com//generic//reports//reportsTemplates//failedTCTemplate.html";
	//Index Test Cases Template HTML File
	public static String testCasesIndexReportTemplate = System.getProperty("user.dir")+"//src//com//generic//reports//reportsTemplates//indexTemplate.html";
	
	public static String getConfigFilePath() {
		return configFilePath;
	}

	public static void setConfigFilePath(String configFilePath) {
		EnvironmentFiles.configFilePath = configFilePath;
	}

	public static String getDataSheetPath() {
		return dataSheetPath;
	}

	public static void setDataSheetPath(String dataSheetPath) {
		EnvironmentFiles.dataSheetPath = dataSheetPath;
	}
	
	public static String getLogFilePath() {
		if(Strings.isNullOrEmpty(logFilePath)) {
			SelTestCase.logs.debug("The Log file path is null, then generating it");
			return System.getProperty("user.dir") + "/" + SelTestCase.getCONFIG().getProperty("logs");
		} else {
			return logFilePath;
		}
	}

	public static void setLogFilePath(String logFilePath) {
		EnvironmentFiles.logFilePath = logFilePath;
	}

	public static String getLogFileName() {
		return logFileName;
	}

	public static void setLogFileName(String logFileName) {
		EnvironmentFiles.logFileName = logFileName;
	}
	
	public static String getReportsFolderPath() {
		if(Strings.isNullOrEmpty(reportsFolderPath)) {
			SelTestCase.logs.debug("The Reports file path is null, then generating it");
			return System.getProperty("user.dir") + "/" + SelTestCase.getCONFIG().getProperty("reportFolderName");
		} else {
			return reportsFolderPath;
		}
	}

	public static void setReportsFolderPath(String reportsFolderPath) {
		EnvironmentFiles.reportsFolderPath = reportsFolderPath;
	}
	
	public static String getFailedTestCaseReportTemplate() {
		return failedTestCaseReportTemplate;
	}

	public static void setFailedTestCaseReportTemplate(String failedTestCaseReportTemplate) {
		EnvironmentFiles.failedTestCaseReportTemplate = failedTestCaseReportTemplate;
	}

	/**
	 * @return the testCasesIndexReportTemplate
	 */
	public static String getTestCasesIndexReportTemplate() {
		return testCasesIndexReportTemplate;
	}

	/**
	 * @param testCasesIndexReportTemplate the testCasesIndexReportTemplate to set
	 */
	public static void setTestCasesIndexReportTemplate(String testCasesIndexReportTemplate) {
		EnvironmentFiles.testCasesIndexReportTemplate = testCasesIndexReportTemplate;
	}
	
}
