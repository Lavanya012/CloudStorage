package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CredentialPage {
    private WebDriver webDriver;

    private final String crenTabId="nav-credentials-tab";
    private final String addCrenBtnId="addCrenBtnId";
    private final String crenUrlId="credential-url";
    private final String crenUserId="credential-username";
    private final String crenPasswdId="credential-password";
    private final String crenSubmitBtnId="crenSubmitBtn";
    private final String credUrlTextId="credUrlText";
    private final String credUsernameTextId="credUsernameText";
    private final String credPasswordTextId="credPasswordText";
    private final String editCredBtnId="editCredBtn";
    private final String deleteCredBtnId="deleteCredBtn";

    public CredentialPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(id=crenTabId)
    WebElement crenTab;
    @FindBy(id=addCrenBtnId)
    WebElement addCrenBtn;
    @FindBy(id=crenUrlId)
    WebElement crenUrl;
    @FindBy(id=crenUserId)
    WebElement crenUser;
    @FindBy(id=crenPasswdId)
    WebElement crenPasswd;
    @FindBy(id=crenSubmitBtnId)
    WebElement crenSubmitBtn;

    public String getCrenTabId() { return crenTabId; }
    public String getAddCrenBtnId() { return addCrenBtnId;}

    public void clickCrenTab(){crenTab.click();}
    public void clickAddCrenBtn(){addCrenBtn.click();}
    public void inputUrl(String url){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].value='"+url+"';",this.crenUrl);
    }
    public void inputUserName(String uname){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].value='"+uname+"';",this.crenUser);
    }
    public void inputPasswd(String pw){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].value='"+pw+"';",this.crenPasswd);
    }
    public String getPasswdInModal(){
        return crenPasswd.getAttribute("value");
    }

    public void clickCrenSubmitBtn(){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.crenSubmitBtn);
    }

    public String getUrl(int pos){
        List<WebElement> urls=webDriver.findElements(By.id(credUrlTextId));
        return urls.get(pos).getAttribute("innerHTML");
    }
    public String getUname(int pos){
        List<WebElement> unames=webDriver.findElements(By.id(credUsernameTextId));
        return unames.get(pos).getAttribute("innerHTML");
    }
    public String getPw(int pos){
        List<WebElement> pwds=webDriver.findElements(By.id(credPasswordTextId));
        return pwds.get(pos).getAttribute("innerHTML");
    }

    public void clickEditCredBtn(int pos){
        List<WebElement> edbtns=webDriver.findElements(By.id(editCredBtnId));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",edbtns.get(pos));
    }

    public void clickDeleteCredBtn(int pos){
        List<WebElement> delbtns=webDriver.findElements(By.id(deleteCredBtnId));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",delbtns.get(pos));
    }

    public List<WebElement> getEditBtns(){ return webDriver.findElements(By.id(editCredBtnId));}

}