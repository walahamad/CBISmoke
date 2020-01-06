package com.generic.setup;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;

public class CustomSoftAssert extends SoftAssert {

    @Override
    public void onAssertFailure(IAssert<?> a, AssertionError ex) {
    	ReportUtil.takeScreenShot(SelTestCase.getDriver(), RandomUtilities.getRandomNumber() );
    }
}