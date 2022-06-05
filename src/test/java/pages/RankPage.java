package pages;

//url=https://xtool.ru/get-page-rank-google/_itmo.ru/

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RankPage {

    public RankPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Getter
    @FindBy(xpath = "/html/body/main/div[2]/h1")
    private WebElement header;
}
