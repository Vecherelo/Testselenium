import io.qameta.allure.Step;
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

public class PasswordPageTest {


        private WebDriver driver;
        private PasswordPage passwordPage;
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

            driver.get("https://gmail.com"); // открываем
            wait = (new WebDriverWait(driver, 20));
            LoginPage loginPage = new LoginPage(driver);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));
            passwordPage = loginPage.registeremail("ivanov05022020@gmail.com");   //вводим логин и заходим на страницу ввода пароля
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordNext")));
        }

        // проверака что мы на нужной странице после ввода пароля
        public void checkOpenNewWindow(String textForCheck) {
            Assert.assertEquals("Входящие",textForCheck);;
        }
        // вводим пароль и нажимаем кнопку
        @Test
        public void nextClickToForm() throws InterruptedException {
            MailPage mailPage = passwordPage.inputpassword("testivan");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='button']/span/span[2]")));
            String textForCheck = mailPage.readTextForCheck();
            checkOpenNewWindow(textForCheck);

        }

        @After
        public void tearDown() {
            driver.quit();
        }  //закрыть браузер

}
