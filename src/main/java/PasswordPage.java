import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordPage {
    private WebDriver driver;

    public PasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    private By nextButtonPassword = By.id("passwordNext");              // Список локаторов
    private By passwordField = By.xpath("//input[@type='password']");
    private By heading = By.id("profileIdentifier");
    private By headingUp = By.xpath("//h1[@id='headingText']/span");

    /**
     * click button next
     * @return
     */
    public MailPage nextButtonPasswordClick(){
        driver.findElement(nextButtonPassword).click();
        return new MailPage(driver);
    }

    /**
     * type password
     * @param password - password
     * @return
     */
    public PasswordPage typePasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    /**
     * type password and click
     * @param password - password
     * @return
     */
    public MailPage inputpassword(String password){
        this.typePasswordField(password);
        driver.findElement(nextButtonPassword).click();
        return new MailPage(driver);
    }

    /**
     * get text from element
     * @return
     */
    public String getHeadingText(){
        return  driver.findElement(heading).getText();
    }

}
