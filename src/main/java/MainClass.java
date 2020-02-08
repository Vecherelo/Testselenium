import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainClass {
    static WebDriver driver;
    static WebDriverWait wait ;
    static String emailTo = "Mogorin@gmail.com";  // кому письмо отправить
    static  String subj = "Тестовое задание Могорин" ; // тема письма

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","C:\\IdeaProjects\\Testselenium\\drivers\\geckodriver.exe");
      //  System.setProperty("webdriver.chrome.driver","C:\\IdeaProjects\\Testselenium\\drivers\\chromedriver.exe");


         driver = new FirefoxDriver();
        // driver = new ChromeDriver();
        //driver = new EdgeDriver();
        //
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("https://gmail.com");
        wait = (new WebDriverWait(driver,10));
        LoginPage loginPage = new LoginPage(driver);
        PasswordPage passwordPage = loginPage.registeremail("ivanov05022020@gmail.com");  //заходим на первую страницу
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordNext")));
        MailPage mailPage=passwordPage.inputpassword("testivan");                           //заходим на вторую страницу
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='button']/span/span[2]")));
        mailPage.newMessageClick();                                                            //заходим на страницу почты
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='to']")));
        mailPage.typeToEmail(emailTo,subj,String.format("Количество писем %s",mailPage.showCountEmail())); //отправляем письмо
        //System.out.println(mailPage.showCountEmail());
        //Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='link_vsm']")));
        driver.findElement(By.xpath("//span[@id='link_undo']")).getText();
       //System.out.println(driver.findElement(By.xpath("//span[@id='link_vsm']")).getText());
        Thread.sleep(5000);
       driver.quit();
    }
}
