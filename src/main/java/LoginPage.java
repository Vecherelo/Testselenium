import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.Annotation;

/**
 *  First window login
 */
public class LoginPage {
     WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private By nextButton = By.id("identifierNext");  // locator button
    private By emailField = By.id("identifierId");    // locator fields

    /**
     * Click for button next
     * @return
     */
    public PasswordPage nextButtonClick(){
        driver.findElement(nextButton).click();
        return new PasswordPage(driver);
    }

    /**
     *  type email for login
     * @param email - email for login
     * @return
     */
    public LoginPage typeEmailField(String email){
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    /**
     *  type and click
     * @param email email for login
     * @return
     */
    public PasswordPage registeremail(String email){
        this.typeEmailField(email);
        driver.findElement(nextButton).click();
        return new PasswordPage(driver);
    }

}
