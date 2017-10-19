package com.generic.selenium.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.generic.selenium.report.ReportUtil;
import com.generic.selenium.setup.SelTestCase;


public class TestUtilities extends SelTestCase {
	
	public static void prepareLogs() throws Exception {
		logs.debug("Clearing logs file");
		String logs_dir = System.getProperty("user.dir") + "/"
                + SelTestCase.getCONFIG().getProperty("logs");
		String log_file = "Application.log";
		String log_abs_path = logs_dir + "/" + log_file;
		
		PrintWriter writer = new PrintWriter(log_abs_path);
		writer.print("");
		writer.close();
		logs.debug("Clearing logs file done");
	}

     public static void reportSetup() throws Exception {

            try {
                if(runReportSetup) {
                    logs.debug("Execute reportSetup");
                    setBrowserName(getCONFIG().getProperty("browser"));
                    try {
                        mainDir = System.getProperty("user.dir") + "/"
                                + SelTestCase.getCONFIG().getProperty("reportFolderName");
                        
                        logs.debug("LogsDir: "+mainDir);
                        
                        File dir1 = new File(mainDir);
                        boolean exists = dir1.exists();
                        if (!exists) {
                            logs.debug("the main directory you are searching does not exist : "
                                    + exists);
                            dir1.mkdir(); // creating main directory if it doesn't exist
                            createSubDir();
                        } else {
                            logs.debug("the main directory you are searching exists : "
                                    + exists);
                            createSubDir();
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                        logs.debug("FAILS TO CREATE REPORT FOLDERS");
                    }

                    ReportUtil.startTesting(subDir + "//index.html",
                            ReportUtil.now(SelTestCase.time_date_format),
                            SelTestCase.getCONFIG().getProperty("testEnvironment"),
                            getBrowserName() );

                    ReportUtil.startSuite("Suite1");
                    runReportSetup = false;
                }

            } catch (Throwable t) {
                t.printStackTrace();
                throw new Exception(t);
            }

        }

     public static void createSubDir() {
         Date dNow = new Date( );
         SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
    	 subDir = mainDir + "/" + tempTCID + ft.format(dNow) + "-Time-" + sdf.format(cal.getTime());
         new File(subDir).mkdir();

     }

    public static void initialize() throws Exception{
    	getCurrentFunctionName(true);
        logs.debug("Execute initialize function");

        // config property file
        setCONFIG(new Properties());
        FileInputStream fn =new FileInputStream(System.getProperty("user.dir")+"//src//com//generic//selenium//config//config.properties");
        getCONFIG().load(fn);
        logs.debug("adding environment: " + getCONFIG().getProperty("testEnvironment"));
        getCONFIG().setProperty("testSiteName", "https://"+getCONFIG().getProperty("testEnvironment")+"/"+getCONFIG().getProperty("testSiteName"));
        //getCONFIG().setProperty("logout", "https://"+getCONFIG().getProperty("testEnvironment")+"."+getCONFIG().getProperty("logout"));

        logs.debug("tempTCID is : " + tempTCID );
        
       //set the max wait time
       setWaitTime(Integer.parseInt(getCONFIG().getProperty("waitTime")));

       
        getCurrentFunctionName(false);
    }


}
