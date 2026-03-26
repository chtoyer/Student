import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PobedaManageTest {
    private WebDriver driver;
    private PobedaManagePage page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.pobeda.aero");
        page = new PobedaManagePage(driver);
    }

    @Test
    public void testOrderSearchError() {
        assertEquals("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы", page.getTitle());
        assertTrue(page.isLogoDisplayed());

        page.openManageBooking();
        assertTrue(page.areFieldsVisible());

        page.searchOrder("XXXXXX", "Qwerty");
        page.switchToNewTab();

        assertTrue(page.getErrorMessage().contains("Заказ с указанными параметрами не найден"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}