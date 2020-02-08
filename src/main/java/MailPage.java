import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MailPage {
    private WebDriver driver;

    public MailPage(WebDriver driver) {
        this.driver = driver;
    }
    private By textCheckName = By.xpath("//a[@href='https://mail.google.com/mail/u/0/#inbox']"); //Список локаторов
    private By countEmail = By.xpath("//div[@role='button']/span/span[2]");
    private By buttonNewMessage =  By.xpath("//div[text()='Написать']");
    private By toEmail = By.xpath("//*[@name='to']");
    private By toSubject = By.xpath("//*[@name='subjectbox']");
    private By toText = By.xpath("//div[@role='textbox']");
    private By sendButton = By.xpath("//div[contains(@data-tooltip,\"Enter\")and @role=\"button\"]");
    private By textSendDone =  By.xpath("//div[@role='alert']/div/div[2]//span[text()='Письмо отправлено.']");

    /**
     * Получаем количество писем
     * @return
     */
    public String showCountEmail(){
        return driver.findElement(countEmail).getText();
    }

    /**
     * текст элемента
     * @return
     */
    public String readTextForCheck() {return driver.findElement(textCheckName).getText();}
    /**
     * текст элемента что письмо отпроавлено
     * @return
     */
    public String readTextAfterSend() {return driver.findElement(textSendDone).getText();}

    /**
     * текст элемента
     * @return
     */
    public String readTextForCheckInCreateMail() {return driver.findElement(sendButton).getText();}

    /**
     * Click кнопка создать письмо
     * @return
     */
    public MailPage newMessageClick(){
        driver.findElement(buttonNewMessage).click();
        return this;
    }

    /**
     * Send email
     * @return
     */
    public MailPage sendButtonClick(){
        driver.findElement(sendButton).click();
        return this;
    }

    /**
     *
     * @param newEmail  Кому
     * @param subj      Тема
     * @param text       Тело
     * @return
     */
    public MailPage typeToEmail(String newEmail,String subj, String text){
        driver.findElement(toEmail).sendKeys(newEmail);
        driver.findElement(toSubject).sendKeys(subj);
        driver.findElement(toText).sendKeys(text);
        driver.findElement(sendButton).click();
        return this;
    }




}
