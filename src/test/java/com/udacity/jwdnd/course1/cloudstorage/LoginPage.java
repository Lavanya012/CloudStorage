package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id="inputUsername")
    private WebElement userName;

    @FindBy(id="inputPassword")
    private WebElement passWord;

    @FindBy(id="submit-button")
    private WebElement loginBtn;

    @FindBy(id="signup-success-msg")
    private WebElement sigupOkMsg;


    public LoginPage(WebDriver webDriver) {PageFactory.initElements(webDriver,this);}

    public void Login(String uname, String passw){
        userName.clear();
        userName.sendKeys(uname);
        passWord.clear();
        passWord.sendKeys(passw);
        loginBtn.click();
    }

    public boolean sigupOkMsgDisplayed() { return sigupOkMsg.isDisplayed(); }


}