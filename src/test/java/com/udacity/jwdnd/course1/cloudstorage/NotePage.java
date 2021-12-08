package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotePage {
    private WebDriver webDriver;

    private final String noteTabId = "nav-notes-tab";
    private final String addNoteBtnId = "addNoteBtn";
    private final String noteTitleId = "note-title";
    private final String noteDescriptionId = "note-description";
    private final String noteSubmitBtnId = "noteSubmit";
    private final String noteTitleDisplayId = "note-title-display";
    private final String noteDesDisplayId = "note_description-display";
    private final String noteEditBtnId = "noteEditBtn";
    private final String noteDeleteBtnId = "noteDeleteBtn";

    public String getNoteTabId() {
        return noteTabId;
    }

    public String getAddNoteBtnId() {
        return addNoteBtnId;
    }

    public String getNoteTitleId() {
        return noteTitleId;
    }

    public String getNoteDescriptionId() {
        return noteDescriptionId;
    }

    public String getNoteSubmitBtnId() {
        return noteSubmitBtnId;
    }

    public String getNoteTitleDisplayId() {
        return noteTitleDisplayId;
    }

    public String getNoteDesDisplayId() {
        return noteDesDisplayId;
    }

    public String getNoteEditBtnId() {
        return noteEditBtnId;
    }

    public String getNoteDeleteBtnId() {
        return noteDeleteBtnId;
    }

    @FindBy(id = noteTabId)
    private WebElement noteTab;

    @FindBy(id = addNoteBtnId)
    private WebElement addNoteBtn;

    @FindBy(id = noteTitleId)
    WebElement noteTitle;

    @FindBy(id = noteDescriptionId)
    WebElement noteDescription;

    @FindBy(id = noteSubmitBtnId)
    WebElement noteSubmitBtn;

    @FindBy(id = noteTitleDisplayId)
    WebElement noteTitleDisplay;

    @FindBy(id = noteDesDisplayId)
    WebElement noteDesDisplay;

    @FindBy(id = noteEditBtnId)
    WebElement noteEditBtn;
    @FindBy(id = noteDeleteBtnId)
    WebElement noteDeleteBtn;

    public NotePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickNoteTab() {
        noteTab.click();
    }

    public void clickAddNoteBtn() {
        addNoteBtn.click();
    }

    public void inputNoteTitle(String title) {

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + title + "';", this.noteTitle);
    }

    public void inputNoteDescription(String des) {

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + des + "';", this.noteDescription);
    }

    public void submitNote() {

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.noteSubmitBtn);
    }

    public String getNoteTitleDisplay() {
        return noteTitleDisplay.getAttribute("innerHTML");
    }

    public String getNoteDesDisplay() {
        return noteDesDisplay.getAttribute("innerHTML");
    }

    public void clickNoteEditBtn() {

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.noteEditBtn);
    }

    public void clickNoteDeleteBtn() {

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.noteDeleteBtn);
    }

    public List<WebElement> getNoteEditBtns() {
        return webDriver.findElements(By.id(noteEditBtnId));
    }

}