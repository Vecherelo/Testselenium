import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private  WebDriverWait wait ;
    @Before
    public void setUp(){
        Capabilities capabilities = DesiredCapabilities.firefox();
        try {
            driver = new RemoteWebDriver(
                      new URL("http://192.168.1.133:4444/wd/hub"),
                      capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.get("https://gmail.com");   // открываем
        wait = (new WebDriverWait(driver,15));
        loginPage = new LoginPage(driver);
    }

    /**
     *  пишем логин и жмем кнопку и проверяем что за страница открылась
     * @throws InterruptedException
     */
    @Test
    public void nextClick() throws InterruptedException {
        PasswordPage passwordPage = loginPage.registeremail("ivanov05022020@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordNext")));
        String heading = passwordPage.getHeadingText();
        Assert.assertEquals("ivanov05022020@gmail.com",heading);
    }

    @After
    public void tearDown() {driver.quit();}
}
