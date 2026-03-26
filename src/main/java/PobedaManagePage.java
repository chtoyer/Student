import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class PobedaManagePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By logo = By.cssSelector("img[alt='Авиакомпания «Победа»']");
    private By manageBookingTab = By.xpath("//button[contains(text(),'Управление бронированием')]");
    private By orderNumberField = By.name("pnr");
    private By lastNameField = By.name("lastName");
    private By searchButton = By.xpath("//button[contains(.,'Поиск')]");
    private By errorText = By.xpath("//div[contains(text(),'Заказ с указанными параметрами не найден')]");

    public PobedaManagePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
    }

    public void openManageBooking() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(manageBookingTab));
        ((JavascriptExecutor) driver).executeScript("arguments.scrollIntoView(true);", element);
        element.click();
    }

    public boolean areFieldsVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberField)).isDisplayed() &&
                driver.findElement(lastNameField).isDisplayed() &&
                driver.findElement(searchButton).isDisplayed();
    }

    public void searchOrder(String pnr, String lastName) {
        driver.findElement(orderNumberField).sendKeys(pnr);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(searchButton).click();
    }

    public void switchToNewTab() {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorText)).getText();
    }
}