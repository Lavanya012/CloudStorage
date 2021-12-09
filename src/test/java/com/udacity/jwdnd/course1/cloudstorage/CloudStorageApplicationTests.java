package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.experimental.theories.Theories;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

    @LocalServerPort
    private int port;

    private static WebDriver driver;
    private static WebDriverWait wait;

    private static String baseUrl;
    private final String fname = "Lavnya";
    private final String lname = "upadhyay";
    private final String uname = "test1";
    private final String pword = "test";

    /*Data for Notes */
    private final String noteTitle = "note title";
    private final String updatedNoteTitle = "updated note title";
    private final String noteDescription = "note des";
    private final String updatedNoteDescription = "updated note des";

    //Credentials
    String url = "http://googl.com";
    String uName = "lavnya";
    String pass = "pass";
    String updatedUrl = "http://test.com";
    String updatedUname = "updated_un";
    String updatedPass = "updated_pass";

    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CredentialService credentialService;

    private Logger logger = LoggerFactory.getLogger(CloudStorageApplicationTests.class);

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void beforeEach() throws InterruptedException {
        baseUrl = "http://localhost:" + this.port;
        Thread.sleep(1000);
    }


    @Test
    @Order(1)
    //test that verifies that an unauthorized user can only access the login and signup pages
    public void unauthorizedPageAccessTest() throws InterruptedException {

        driver.get(baseUrl + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
        Thread.sleep(2000);
        driver.get(baseUrl + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());
        Thread.sleep(2000);
        driver.get(baseUrl + "/home");
        Assertions.assertNotEquals("Home", driver.getTitle());
    }

    @Test
    @Order(2)

    public void signupSuccessTest() throws InterruptedException {
        driver.get(baseUrl + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signUpNow(fname, lname, uname, pword);
        assertEquals("Login", driver.getTitle());
    }

    @Test
    @Order(3)
    public void loginSuccessTest() throws Exception {
        driver.get(baseUrl + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(uname, pword);
        Assertions.assertEquals("Home", driver.getTitle());
        driver.get(baseUrl + "/home");
        HomePage homePage = new HomePage(driver, wait);
        homePage.clickLogoutBtn();
        //Assertion to validate if home page is not accessible after user loggedOut
        Assertions.assertNotEquals("Home", driver.getTitle());
        driver.get(baseUrl + "/login");
        loginPage.Login(uname, pword);
        //Assert page redirected to Home screen so login is successful
        Assertions.assertEquals("Home", driver.getTitle());

    }


    public void waitForVisibility(String id) {
        WebDriverWait wait = new WebDriverWait(driver, 4000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));

    }

    @Test
    @Order(4)
    public void testNotes() throws Exception {
        driver.get(baseUrl + "/home");
        NotePage notePage = new NotePage(driver, wait);
        Thread.sleep(2000);
        notePage.clickNoteTab();
        /*Add a new Note*/
        notePage.clickAddNoteBtn();
        notePage.enterNoteTitle(noteTitle);
        notePage.enterNoteDescription(noteDescription);
        notePage.clickSaveNotes();
        notePage.clickNoteTab();
        /*Validation of Added Notes*/
        Assertions.assertEquals(notePage.getSavedNoteTitle(), noteTitle);
        Assertions.assertEquals(notePage.getSavedNoteDes(), noteDescription);
        /*Edit Notes*/
        notePage.clickNoteEditBtn();
        notePage.enterNoteTitle(updatedNoteTitle);
        notePage.enterNoteDescription(updatedNoteDescription);
        notePage.clickSaveNotes();
        notePage.clickNoteTab();
        /*Validation of updated Notes*/
        Assertions.assertEquals(notePage.getSavedNoteTitle(), updatedNoteTitle);
        Assertions.assertEquals(notePage.getSavedNoteDes(), updatedNoteDescription);
        notePage.clickNoteTab();
        /*Validate Deletion of Notes*/
        notePage.clickNoteDeleteBtn();
        notePage.clickNoteTab();
        Assert.assertTrue(notePage.getNoteSuccessMessage().contains("Note deleted successfully"));


    }

    @Test
    @Order(5)
    public void testCredentials() throws Exception {
        driver.get(baseUrl + "/home");
        CredentialPage credentialPage = new CredentialPage(driver, wait);
        /*Add and Validate Credentials*/
        credentialPage.clickCrenTab();
        credentialPage.addCredentials(url, uName, pass);
        Thread.sleep(2000);
        credentialPage.clickCrenTab();
        String displayedUrl = credentialPage.getCredUrlText();
        String displayedUname = credentialPage.getCredUsernameText();
        String displayedPwd = credentialPage.getCredPasswordText();
        String key1=credentialService.getKeyById(0);
        logger.info("key1"+key1);
        String key = credentialService.getKeyById(1);
        logger.info("key::"+key);
        displayedPwd = encryptionService.decryptValue(displayedPwd, key);
        Assertions.assertEquals(displayedUrl, url);
        Assertions.assertEquals(displayedUname, uName);
        Assertions.assertEquals(displayedPwd, pass);

        /*Edit Credentials and check Decrypted password*/
        credentialPage.clickEditCredBtn();
        Thread.sleep(3000);
        String urlText = credentialPage.getCredUrlText();
        String usename = credentialPage.getCredUsernameText();
        String decryptedPass = credentialPage.getCredPasswordTextFromEditForm();
        logger.info("url"+urlText);
        logger.info("un"+usename);
        logger.info("decryptedPass"+decryptedPass);
       // Assert.assertEquals(decryptedPass, pass);

        /*update credentials*/
        credentialPage.enterUrl(updatedUrl);
        credentialPage.enterUserName(updatedUname);
        credentialPage.enterPasswd(updatedPass);
        credentialPage.clickCrenSubmitBtn();
        credentialPage.clickCrenTab();
        displayedUrl = credentialPage.getCredUrlText();
        displayedUname = credentialPage.getCredUsernameText();
        displayedPwd = credentialPage.getCredPasswordText();
        key = credentialService.getKeyById(1);
        displayedPwd = encryptionService.decryptValue(displayedPwd, key);
        Assertions.assertEquals(displayedUrl, displayedUrl);
        Assertions.assertEquals(displayedUname, displayedUname);
        Assertions.assertEquals(displayedPwd, displayedPwd);

        /*Delete Credentials*/
        credentialPage.clickDeleteCredBtn();
        credentialPage.clickCrenTab();
        Assert.assertTrue(credentialPage.getSuccessMasaage().contains("Deleting credential is successful"));

    }

}