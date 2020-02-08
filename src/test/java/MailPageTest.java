import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class MailPageTest {
    private WebDriver driver;
    private MailPage mailPage;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        Capabilities capabilities = DesiredCapabilities.firefox();
        try {
            driver = new RemoteWebDriver(
                    new URL("http://192.168.1.133:4444/wd/hub"),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.get("https://gmail.com");
        wait = (new WebDriverWait(driver, 10));
        LoginPage loginPage = new LoginPage(driver);
        PasswordPage passwordPage = loginPage.registeremail("ivanov05022020@gmail.com");//вводим логин и заходим на страницу ввода пароля
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordNext")));
        mailPage=passwordPage.inputpassword("testivan");                                 //   вводим пароль и переходим в саму почту
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='button']/span/span[2]")));
    }

    /**
     * создаем окно для написания письма  заполняем и отпроавляем
     * @throws InterruptedException
     */
    @Test
    public void ClickToNewMail() throws InterruptedException {
        mailPage.newMessageClick();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='to']")));
        String textForCheck = mailPage.readTextForCheckInCreateMail();
        mailPage.typeToEmail("Mogorin@gmail.com","Тестовое задание Могорин",String.format("Количество писем %s",mailPage.showCountEmail()));
        Assert.assertEquals("Отправить",textForCheck);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
