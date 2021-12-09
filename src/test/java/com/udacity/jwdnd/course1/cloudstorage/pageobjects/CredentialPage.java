package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPage {
    private WebDriver driver;
    private WebDriverWait wait;



    public CredentialPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "nav-credentials-tab")
    private WebElement crenTab;
    @FindBy(id = "addCrenBtnId")
    private WebElement addCrenBtn;
    @FindBy(id = "credential-url")
    private WebElement crenUrl;
    @FindBy(id = "credential-username")
    private WebElement crenUser;
    @FindBy(id = "credential-password")
    private WebElement crenPasswd;
    @FindBy(id = "crenSubmitBtn")
    private WebElement crenSubmitBtn;
    @FindBy(id="credUrlText")
    private WebElement credUrlText;
    @FindBy(id="credUsernameText")
    private WebElement credUsernameText;
    @FindBy(id="credPasswordText")
    private WebElement credPasswordText;
    @FindBy(id="editCredBtn")
    private WebElement editCredBtn;
    @FindBy(id="deleteCredBtn")
    private WebElement deleteCredBtn;
    @FindBy(id="success-cred-message")
    private WebElement successMeessagee;



    public void clickCrenTab() {
        wait.until(ExpectedConditions.visibilityOf(crenTab));
        crenTab.click();
    }


    public void clickAddCrenBtn() {
        wait.until(ExpectedConditions.visibilityOf(addCrenBtn));
        addCrenBtn.click();
    }

    public void enterUrl(String url) {
        wait.until(ExpectedConditions.visibilityOf(crenUrl));
        crenUrl.clear();
        crenUrl.sendKeys(url);
    }

    public void enterUserName(String uName) {
        wait.until(ExpectedConditions.visibilityOf(crenUser));
        crenUser.clear();
        crenUser.sendKeys(uName);
    }

    public void enterPasswd(String pass) {
        wait.until(ExpectedConditions.visibilityOf(crenPasswd));
        crenPasswd.clear();
        crenPasswd.sendKeys(pass);
    }

    public void clickCrenSubmitBtn() {
        wait.until(ExpectedConditions.visibilityOf(crenSubmitBtn));
        crenSubmitBtn.click();
    }

    public String getCredUrlText() {
        wait.until(ExpectedConditions.visibilityOf(credUrlText));
       return credUrlText.getText();
    }

    public String getCredUsernameText() {
        wait.until(ExpectedConditions.visibilityOf(credUsernameText));
        return credUsernameText.getText();
    }

    public String getCredPasswordText() {
        wait.until(ExpectedConditions.visibilityOf(credPasswordText));
        return credPasswordText.getText();
    }

    public String getCredPasswordTextFromEditForm() {
        wait.until(ExpectedConditions.visibilityOf(credPasswordText));
        return credPasswordText.getAttribute("value");
    }
    public void clickEditCredBtn() {
        wait.until(ExpectedConditions.visibilityOf(editCredBtn));
        editCredBtn.click();
    }

    public void clickDeleteCredBtn() {
        wait.until(ExpectedConditions.visibilityOf(deleteCredBtn));
        deleteCredBtn.click();
    }


    public void addCredentials(String url,String userName,String password)
    {
        wait.until(ExpectedConditions.visibilityOf(addCrenBtn));
        addCrenBtn.click();
        wait.until(ExpectedConditions.visibilityOf(crenUrl));
        crenUrl.sendKeys(url);
        crenUser.sendKeys(userName);
        crenPasswd.sendKeys(password);
        crenSubmitBtn.click();

    }

    public String getDecryptedPass()
    {
       return credPasswordText.getText();
    }
public String getSuccessMasaage()
{
    wait.until(ExpectedConditions.visibilityOf(successMeessagee));
   return successMeessagee.getText();
}

}