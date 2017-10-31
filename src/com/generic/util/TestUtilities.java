package com.generic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.generic.datatable.Xls_Reader;
import com.generic.report.ReportUtil;
import com.generic.setup.SelTestCase;


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

     // get the data from xls file
     public static Object[][] getData(String testName){
     	getCurrentFunctionName(true);
         if(SelTestCase.getDatatable() == null){
             SelTestCase.setDatatable(new Xls_Reader(System.getProperty("user.dir")+"//src//com//generic//testdata//DataSheet.xlsx"));
         }
     	System.out.println("Test name to be run: "+testName);
         int rows=SelTestCase.getDatatable().getRowCount(testName)-1;
         if(rows <=0){
             Object[][] testData =new Object[1][0];
             return testData;

         }
         rows = SelTestCase.getDatatable().getRowCount(testName);  // 3
         int cols = SelTestCase.getDatatable().getColumnCount(testName);
         logs.debug("Test Name -- "+testName);
         Object data[][] = new Object[rows-1][cols];

         for(int rowNum = 2 ; rowNum <= rows ; rowNum++){

             for(int colNum=0 ; colNum< cols; colNum++){
                 data[rowNum-2][colNum]=SelTestCase.getDatatable().getCellData(testName, colNum, rowNum);
             }
         }

         getCurrentFunctionName(false);
         return data;
     }
     
    public static void initialize() throws Exception{
    	getCurrentFunctionName(true);
        logs.debug("Execute initialize function");

        // config property file
        setCONFIG(new Properties());
        FileInputStream fn =new FileInputStream(System.getProperty("user.dir")+"//src//com//generic//config//config.properties");
        getCONFIG().load(fn);
        logs.debug("adding environment: " + getCONFIG().getProperty("testEnvironment"));
        getCONFIG().setProperty("testSiteName", "https://"+getCONFIG().getProperty("testEnvironment")+"/"+getCONFIG().getProperty("testSiteName"));
        //getCONFIG().setProperty("logout", "https://"+getCONFIG().getProperty("testEnvironment")+"."+getCONFIG().getProperty("logout"));

        logs.debug("tempTCID is : " + tempTCID );
        
        setDatatable(new Xls_Reader(System.getProperty("user.dir")+"//src//com//generic//testdata//DataSheet.xlsx"));

        //set the max wait time
        setWaitTime(Integer.parseInt(getCONFIG().getProperty("waitTime")));

       
        getCurrentFunctionName(false);
    }


}
