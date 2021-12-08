package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id="inputFirstName")
    WebElement firstName;

    @FindBy(id="inputLastName")
    WebElement lastName;

    @FindBy(id="inputUsername")
    WebElement userName;

    @FindBy(id="inputPassword")
    WebElement passWord;

    @FindBy(id="signupBtn")
    WebElement signupBtn;

    @FindBy(id="singup-ok-msg")
    WebElement signupOkMsg;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public void signUpNow(String fname, String lname, String uname, String pword){
        firstName.clear(); firstName.sendKeys(fname);
        lastName.clear(); lastName.sendKeys(lname);
        userName.clear(); userName.sendKeys(uname);
        passWord.clear(); passWord.sendKeys(pword);
        signupBtn.click();
    }

    public boolean sigupOkMsgDisplayed() {return signupOkMsg.isDisplayed();}
}