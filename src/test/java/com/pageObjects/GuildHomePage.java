package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GuildHomePage extends BasePage {


    @FindBy(className = "site-title")
    private WebElement guildLogo;

    @FindBy(xpath = "//h2[text()='Hey Onkar!']")
    private WebElement userTitle;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(userTitle);
    }


    public WebElement getGuildLogo() {
        return  guildLogo;
    }

    public WebElement getUserTitle(){
        return userTitle;
    }
}
