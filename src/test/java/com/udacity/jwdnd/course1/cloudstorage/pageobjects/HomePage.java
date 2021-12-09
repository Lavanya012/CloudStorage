package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    public HomePage(WebDriver driver,WebDriverWait wait) {
        this.driver=driver;
        this.wait=wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="logout")
    WebElement logoutBtn;

    public void clickLogoutBtn(){
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        logoutBtn.click();

    }
}