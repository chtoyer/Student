import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PobedaPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By logo = By.cssSelector("img[alt='Авиакомпания «Победа»']");
    private By infoMenu = By.xpath("//button[contains(text(),'Информация')]");
    private By popupHeaders = By.xpath("//div[contains(@class, 'pobeda-header-menu-item')]//h3");

    public PobedaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
    }

    public void hoverInfoMenu() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(infoMenu));
        new Actions(driver).moveToElement(element).perform();
    }

    public boolean isHeaderPresentInPopup(String headerText) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(popupHeaders))
                .stream()
                .anyMatch(el -> el.getText().contains(headerText));
    }
}