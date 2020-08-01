package com.stepdefinitions.hooks;
import com.framework.driver.WebDriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class Hooks {
    WebDriverManager driverManager;
    public static ThreadLocal <Scenario> currentScenario =new ThreadLocal<>();

    public static Scenario getScenario(){
        return currentScenario.get();
    }

    public static void setScenario(Scenario scenario){
        currentScenario.set(scenario);
    }

  /*  public Hooks() {
        *//*System.out.println("Hooks getting start!!!");
        driverManager =  new WebDriverManager();*//*
    }*/

    @Before
    public void BeforeScenario(Scenario scenario) {
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/
       // driver = WebDriverManager.getDriver();
        //ExtentListeners.scenario =  ExtentListeners.testReport.get().createNode(scenario.getName());
        //ExtentListeners.testReport.set(ExtentListeners.testReport.get().createNode(scenario.getName()));
        System.out.println("Im running inside BeforeScenario !!!");
       // ExtentListeners.testReport.get().info(scenario.getName() + " is started");
        driverManager =  new WebDriverManager();
        driverManager.createDriver();
        setScenario(scenario);
    }

    @After
    public void AfterScenario(Scenario scenario) {
        if (scenario.isFailed()) {

            try {
               //ExtentListeners.testReport.get().info(scenario.getName() + " is Failed");
                //ExtentListeners.testReport.get().createNode(scenario.getName()+"is failed");
                final byte[] screenshot = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png"); // ... and embed it in
            } catch (WebDriverException e) {
                e.printStackTrace();
            }

        } else {
            try {
                //ExtentListeners.testReport.get().info(scenario.getName() + " is pass");
                //ExtentListeners.testReport.get().createNode(scenario.getName()+"is passed");
                scenario.embed(((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       // ExtentListeners.testReport.get().info(scenario.getName() + " is done !");
        //ExtentListeners.testReport.get().createNode(scenario.getName()+"is done !");
        driverManager.closeDriver();
    }

}