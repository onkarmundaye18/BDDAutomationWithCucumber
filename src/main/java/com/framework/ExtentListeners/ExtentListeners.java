package com.framework.ExtentListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.testng.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;


public class ExtentListeners implements ITestListener {

    static Logger log = LoggerHelper.getLogger(ExtentListeners.class);
    static Date d = new Date();
    static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

    private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);
    public static ExtentTest scenario ;
    public static ExtentTest feature;
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();


    public void onTestStart(ITestResult result) {
ExtentTest test = extent.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
        testReport.set(test);

    }

    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().pass(m);
    }

    public void onTestFailure(ITestResult result) {
        String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                + "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

        try {

            ExtentManager.captureScreenshot();
 ExtentManager.scenarioDef.fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
                    .build());

            testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
                            .build());
        } catch (IOException e) {

        }
        String failureLogg = "TEST CASE FAILED";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        testReport.get().log(Status.FAIL, m);
    }

    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        testReport.get().skip(m);

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context) {
        extent = ExtentManager.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);
        feature = extent.createTest(Feature.class,"Guild feature");
        testReport.set(feature);
    }

    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }


 public void onStart(ISuite iSuite) {
        ExtentTest test = extent.createTest("Suite name : - " + iSuite.getName());
        testReport.set(test);

    }

    public void onFinish(ISuite iSuite) {
        if (extent != null) {
            extent.flush();
        }
    }

}
