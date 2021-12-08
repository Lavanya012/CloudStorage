package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver webDriver;
    public HomePage(WebDriver webDriver) {
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id="logout")
    WebElement logoutBtn;

    public void clickLogoutBtn(){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",logoutBtn);
    }
}