import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PobedaSearchTest {
    private WebDriver driver;
    private PobedaSearchPage page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.pobeda.aero");
        page = new PobedaSearchPage(driver);
    }

    @Test
    public void testSearchValidation() {
        assertEquals("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы", page.getTitle());
        assertTrue(page.isLogoDisplayed());

        page.scrollToSearch();
        page.enterRoute("Москва", "Санкт-Петербург");
        page.submitSearch();

        assertTrue(page.isDateRed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}