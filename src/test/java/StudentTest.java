import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@WireMockTest(httpPort = 5352)
class StudentTest {

    @Test
    void testAddGrade_Valid() throws Exception {
        stubFor(get(urlPathEqualTo("/checkGrade"))
                .withQueryParam("grade", equalTo("5"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("true")));

        Student student = new Student("Ivan");
        student.addGrade(5);

        assertEquals(1, student.getGrades().size());
        assertEquals(5, student.getGrades().get(0));
    }

    @Test
    void testAddGrade_Invalid() throws Exception {
        stubFor(get(urlPathEqualTo("/checkGrade"))
                .withQueryParam("grade", equalTo("1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("false")));

        Student student = new Student("Ivan");

        assertThrows(IllegalArgumentException.class, () -> student.addGrade(1));
        assertTrue(student.getGrades().isEmpty());
    }
}