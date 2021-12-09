package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

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
    private WebElement signUpSuccess;


    public LoginPage(WebDriver driver) {PageFactory.initElements(driver,this);}

    public void Login(String uname, String pass){
        userName.clear();
        userName.sendKeys(uname);
        passWord.clear();
        passWord.sendKeys(pass);
        loginBtn.click();
    }



}