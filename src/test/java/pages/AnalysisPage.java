package pages;

//url=https://xtool.ru/analyze/_itmo.ru/

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AnalysisPage {

    public AnalysisPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/main/div[2]/h1")
    private WebElement header;

    @FindBy(xpath = "//li[text()='Изменения XT']")
    private WebElement xtBtn;

    @FindBy(xpath = "//button[normalize-space(text()) = 'Закрыть']")
    private WebElement unauthorizedBtn;

    @FindBy(xpath = "//div[@style='border: solid 1px #ddd; max-width: 700px; border-radius: 10px; padding: 20px 0; margin-top: 20px;']")
    private WebElement userNoLoginDiv;

    @FindBy(xpath = "//div[@class='trust_info']")
    private WebElement trustInfoDiv;


    public String tryGetXtUnauthorized(){
        xtBtn.click();
        return userNoLoginDiv.getText();
    }

    public String tryGetXtAuthorized() throws InterruptedException{
        xtBtn.click();
        Thread.sleep(1000);
        return trustInfoDiv.getText();
    }

    public void closeUnauthorizedAlert(){
        unauthorizedBtn.click();
    }
}
