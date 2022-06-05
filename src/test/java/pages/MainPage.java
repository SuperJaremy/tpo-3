package pages;

import lombok.Getter;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// url=https://xtool.ru/


public class MainPage {
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-user_id='0']//*[@id='btn-login']")
    private WebElement btnLoginButton;

    @FindBy(xpath = "//div[@data-user_id='0']//*[@id='login']")
    private WebElement loginInput;

    @FindBy(xpath = "//div[@data-user_id='0']//*[@id='pass']")
    private WebElement passInput;

    public void tryLogin(String login, String password) throws UnhandledAlertException {
        loginInput.sendKeys(login);
        passInput.sendKeys(password);
        btnLoginButton.click();
    }

    @FindBy(xpath = "/html/body/header/div[3]/div/div[1]/div[1]")
    private WebElement login;

    public String getLogin(){
        return login.getText();
    }

    @FindBy(xpath = "//*[@id='url']")
    private WebElement siteInput;

    @FindBy(xpath = "//*[@id='check-submit']")
    private WebElement checkSubmitButton;

    public void checkSite(String url){
        siteInput.sendKeys(url);
        checkSubmitButton.click();
    }

}
