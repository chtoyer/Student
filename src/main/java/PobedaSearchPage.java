import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PobedaSearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By logo = By.cssSelector("img[alt='Авиакомпания «Победа»']");
    private By inputFrom = By.xpath("//input[@placeholder='Откуда']");
    private By inputTo = By.xpath("//input[@placeholder='Куда']");
    private By searchButton = By.xpath("//button[contains(.,'Поиск')]");
    private By dateField = By.xpath("//input[@placeholder='Туда']/parent::div");

    public PobedaSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
    }

    public void scrollToSearch() {
        WebElement element = driver.findElement(inputFrom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void enterRoute(String from, String to) {
        WebElement fromEl = wait.until(ExpectedConditions.elementToBeClickable(inputFrom));
        fromEl.sendKeys(from);
        fromEl.sendKeys(Keys.ENTER);

        WebElement toEl = wait.until(ExpectedConditions.elementToBeClickable(inputTo));
        toEl.sendKeys(to);
        toEl.sendKeys(Keys.ENTER);
    }

    public void submitSearch() {
        WebElement btn = driver.findElement(searchButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public boolean isDateRed() {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
        return field.getAttribute("class").contains("error") || field.getAttribute("style").contains("red");
    }
}