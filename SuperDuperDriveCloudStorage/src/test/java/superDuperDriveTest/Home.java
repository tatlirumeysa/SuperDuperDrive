package superDuperDriveTest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Home {

    private WebDriver driver = null;
    private static final int timeOutInSeconds = 5;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "addNewNoteButton")
    private WebElement addNewNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(id = "noteSubmitButton")
    private WebElement noteSubmit;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "noteTitle")
    private WebElement noteTitle;

    @FindBy(id="editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id="deleteNoteButton")
    private WebElement deleteNoteButton;

    @FindBy(id="btnSubmitDeleteNote")
    private WebElement submitDeleteButtonNote;

    @FindBy(id="btnSubmitDeleteCredential")
    private WebElement submitDeleteCredentialButton;

    @FindBy(css = "td#noteTitle")
    private List<WebElement> notes;

    @FindBy(id = "addNewCredentialButton")
    private WebElement addNewCredentialButton;

    @FindBy(id="credential-url")
    private WebElement credentialUrlField;
    @FindBy(id = "credential-username")
    private WebElement credentialUsernameField;
    @FindBy(id="credential-password")
    private WebElement credentialPasswordField;
    @FindBy(id="credentialSubmitButton")
    private WebElement credentialSubmitButton;

    @FindBy(id = "credentialUrl")
    private WebElement credentialUrl;

    @FindBy(id = "credentialPassword")
    private WebElement credentialPassword;

    @FindBy(id="editCredentialButton")
    private WebElement editCredentialButton;
    @FindBy(id="deleteCredentialButton")
    private WebElement deleteCredentialButton;

    @FindBy(css = "th#credentialUrl")
    private List<WebElement> credentials;

    public Home(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void logout() {
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].click();", logoutButton);
    }
    public void chooseNoteTab(){
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].click();", notesTab);
    }

    public void chooseCredentialTab(){
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].click();", credentialsTab);
    }

    public void openNewNoteModal(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(addNewNoteButton));
        this.addNewNoteButton.click();
    }

    public void openEditNoteModal(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(editNoteButton));
        this.editNoteButton.click();
    }

    public void openDeleteNoteModal(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(deleteNoteButton));
        this.deleteNoteButton.click();
    }

    public void submitNote(String noteTitle,String noteDescription){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(noteTitleField));
        wait.until(ExpectedConditions.visibilityOf(noteDescriptionField));
        wait.until(ExpectedConditions.elementToBeClickable(noteSubmit));

        this.noteTitleField.clear();
        this.noteDescriptionField.clear();
        this.noteTitleField.sendKeys(noteTitle);
        this.noteDescriptionField.sendKeys(noteDescription);

        this.noteSubmit.click();
    }
    public void submitDeleteNote(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(submitDeleteButtonNote));
        submitDeleteButtonNote.click();
    }

    public List<WebElement> getNotes(){
        return notes;
    }

    public List<WebElement> getCredentials(){
        return credentials;
    }

    public String getNotTitle(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.visibilityOf(noteTitle));
        return noteTitle.getText();
    }

    public String getCredentialUrl(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.visibilityOf(credentialUrl));
        return credentialUrl.getText();
    }

    public String getCredentialPassword(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.visibilityOf(credentialPassword));
        return credentialPassword.getText();
    }

    public void openNewCredentialModal(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(addNewCredentialButton));
        this.addNewCredentialButton.click();
    }

    public void openEditCredentialModal(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(editCredentialButton));
        this.editCredentialButton.click();
    }

    public void openDeleteCredentialModal(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(deleteCredentialButton));
        this.deleteCredentialButton.click();
    }

    public void submitCredential(String credentialUrl, String credentialUsername, String credentialPassword) {
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(credentialUrlField));
        wait.until(ExpectedConditions.visibilityOf(credentialUsernameField));
        wait.until(ExpectedConditions.visibilityOf(credentialPasswordField));
        wait.until(ExpectedConditions.elementToBeClickable(credentialSubmitButton));
        credentialUrlField.clear();
        credentialUsernameField.clear();
        credentialPasswordField.clear();

        credentialUrlField.sendKeys(credentialUrl);
        credentialUsernameField.sendKeys(credentialUsername);
        credentialPasswordField.sendKeys(credentialPassword);
        credentialSubmitButton.click();
    }

    public void submitDeleteCredential(){
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(submitDeleteCredentialButton));
        submitDeleteCredentialButton.click();
    }

}