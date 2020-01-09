package com.generic.setup;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class CustomSoftAssert extends SoftAssert {

    @Override
    public void onAssertFailure(IAssert<?> a, AssertionError ex) {
    	SASLogger logs = new SASLogger("Assert");
    	logs.debug("<font color=\"red\">Assert failed</font>");
    	ReportUtil.takeScreenShot(SelTestCase.getDriver(), RandomUtilities.getRandomNumber() );
    }
}