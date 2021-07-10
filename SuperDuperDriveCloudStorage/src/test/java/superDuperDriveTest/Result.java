package superDuperDriveTest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Result {

    private WebDriver driver = null;

    @FindBy(id = "successReturnHomeLink")
    private WebElement successReturnHomeLink;

    public Result(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void successReturnHome(){
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].click();", successReturnHomeLink);
    }
}
