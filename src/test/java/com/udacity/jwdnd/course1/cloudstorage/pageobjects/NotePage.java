package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "addNoteBtn")
    private WebElement addNoteBtn;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmitButton")
    private WebElement noteSubmitBtn;

    @FindBy(id = "note-title-display")
    private  WebElement savedTitle;

    @FindBy(id = "note_description-display")
    private WebElement savedDescription;

    @FindBy(id = "noteEditBtn")
    private  WebElement noteEditBtn;

    @FindBy(id = "noteDeleteBtn")
    private  WebElement noteDeleteBtn;

    @FindBy(id="success-note-message")
    private WebElement success_note_message;

    public NotePage(WebDriver driver,WebDriverWait wait) {
        this.driver = driver;
        this.wait=wait;
        PageFactory.initElements(driver, this);
    }

    public void clickNoteTab() {
        wait.until(ExpectedConditions.visibilityOf(noteTab));
        noteTab.click();
    }

    public void clickAddNoteBtn() {
        wait.until(ExpectedConditions.visibilityOf(addNoteBtn));
        addNoteBtn.click();
    }

    public void enterNoteTitle(String title) {
        wait.until(ExpectedConditions.visibilityOf(noteTitle));
        noteTitle.clear();
        noteTitle.sendKeys(title);

    }

    public void enterNoteDescription(String description) {
        wait.until(ExpectedConditions.visibilityOf(noteDescription));
        noteDescription.clear();
        noteDescription.sendKeys(description);

    }

    public void clickSaveNotes() {
        wait.until(ExpectedConditions.visibilityOf(noteSubmitBtn));
        noteSubmitBtn.click();

    }

    public String getSavedNoteTitle() {
        wait.until(ExpectedConditions.visibilityOf(savedTitle));
        return savedTitle.getText().trim();
    }

    public String getSavedNoteDes() {
        wait.until(ExpectedConditions.visibilityOf(savedDescription));
        return savedDescription.getText().trim();
    }

    public void clickNoteEditBtn() {
        wait.until(ExpectedConditions.visibilityOf(noteEditBtn));
        noteEditBtn.click();
    }

    public void clickNoteDeleteBtn() {
        wait.until(ExpectedConditions.visibilityOf(noteDeleteBtn));
        noteDeleteBtn.click();
    }

public String getNoteSuccessMessage()
{
    wait.until(ExpectedConditions.visibilityOf(success_note_message));
    return success_note_message.getText();
}

}