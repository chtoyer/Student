import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PobedaTest {
    private WebDriver driver;
    private PobedaPage pobedaPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.pobeda.aero");
        pobedaPage = new PobedaPage(driver);
    }

    @Test
    public void testPobedaInfoPopup() {
        String expectedTitle = "Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы";
        assertEquals(expectedTitle, pobedaPage.getTitle());
        assertTrue(pobedaPage.isLogoDisplayed());

        pobedaPage.hoverInfoMenu();

        assertTrue(pobedaPage.isHeaderPresentInPopup("Подготовка к полету"));
        assertTrue(pobedaPage.isHeaderPresentInPopup("Полезная информация"));
        assertTrue(pobedaPage.isHeaderPresentInPopup("О компании"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}