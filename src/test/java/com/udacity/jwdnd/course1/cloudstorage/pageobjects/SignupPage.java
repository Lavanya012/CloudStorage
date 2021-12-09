package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    WebElement firstName;

    @FindBy(id = "inputLastName")
    WebElement lastName;

    @FindBy(id = "inputUsername")
    WebElement userName;

    @FindBy(id = "inputPassword")
    WebElement passWord;

    @FindBy(id = "signupBtn")
    WebElement signupBtn;

    @FindBy(id = "singup-ok-msg")
    WebElement signupSuccessMesg;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signUpNow(String fName, String lName, String uName, String pass) {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        userName.sendKeys(uName);
        passWord.sendKeys(pass);
        signupBtn.click();
    }

}