package com.stepdefinitions.automationGuild;

import com.framework.driver.WebDriverManager;
import com.pageObjects.GuildHomePage;
import com.pageObjects.GuildLoginPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AutomationGuildSteps {

    WebDriver driver;
    GuildLoginPage guildLoginPage;
    GuildHomePage guildHomePage;

    public AutomationGuildSteps(){
        driver = WebDriverManager.getDriver();
    }


    @When("^AutomationGuild Website \"([^\"]*)\" is opened$")
    public void automationguild_Website_is_opened(String url) throws Throwable {
        //ExtentListeners.testReport.get().info("automationguild_Website_is_opened");
        //ExtentListeners.testReport.get().createNode(new GherkinKeyword("when"),"AutomationGuild Website :"+url+" is opened");
        guildLoginPage = new GuildLoginPage().open(url);
    }

    @Then("^AutomationGuild login page should be displayed by default$")
    public void automationguild_login_page_should_opened_by_default() throws Throwable {
        //ExtentListeners.testReport.get().createNode(new GherkinKeyword("Then"),"AutomationGuild login page should be displayed by default");
        Assert.assertTrue(guildLoginPage.getLoginBtn().isDisplayed(), "LoginBtn is displayed");
    }


    @When("^user logged in with below \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_logged_in_with_below_and(String username, String password) throws Throwable {
        //ExtentListeners.testReport.get().createNode(new GherkinKeyword("When"),"user logged in with below username:"+username+" and "+" password:"+password);
         guildHomePage = guildLoginPage.login(username, password);
    }

    @Then("^AutomationGuild Home Page should be displayed$")
    public void automationguild_Home_Page_should_opened() throws Throwable {
        //ExtentListeners.testReport.get().createNode(new GherkinKeyword("Then"),"AutomationGuild Home Page should be displayed");
        Assert.assertTrue(guildHomePage.getUserTitle().isDisplayed(), "GuildLogo is displayed");
    }


}
