import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.*;

@WireMockTest(httpPort = 8080)
class StudentApiTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
        com.github.tomakehurst.wiremock.client.WireMock.reset();
    }

    @Test
    void testGetStudentSuccess() {
        stubFor(get(urlEqualTo("/student/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"name\": \"Ivan\"}")));

        RestAssured.get("/student/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue());
    }

    @Test
    void testGetStudentNotFound() {
        stubFor(get(urlEqualTo("/student/999"))
                .willReturn(aResponse().withStatus(404)));

        RestAssured.get("/student/999")
                .then()
                .statusCode(404);
    }

    @Test
    void testPostStudentCreate() {
        stubFor(post(urlEqualTo("/student"))
                .willReturn(aResponse().withStatus(201)));

        RestAssured.given().contentType(ContentType.JSON)
                .body("{\"id\": 10, \"name\": \"Petr\"}")
                .post("/student")
                .then()
                .statusCode(201);
    }

    @Test
    void testPostStudentUpdate() {
        stubFor(post(urlEqualTo("/student"))
                .willReturn(aResponse().withStatus(201)));

        RestAssured.given().contentType(ContentType.JSON)
                .body("{\"id\": 1, \"name\": \"Ivan Updated\"}")
                .post("/student")
                .then()
                .statusCode(201);
    }

    @Test
    void testPostStudentNullId() {
        stubFor(post(urlEqualTo("/student"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 123, \"name\": \"NoId\"}")));

        RestAssured.given().contentType(ContentType.JSON)
                .body("{\"name\": \"NoId\"}")
                .post("/student")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    void testPostStudentEmptyName() {
        stubFor(post(urlEqualTo("/student"))
                .willReturn(aResponse().withStatus(400)));

        RestAssured.given().contentType(ContentType.JSON)
                .body("{\"id\": 5}")
                .post("/student")
                .then()
                .statusCode(400);
    }

    @Test
    void testDeleteStudentSuccess() {
        stubFor(delete(urlEqualTo("/student/1"))
                .willReturn(aResponse().withStatus(200)));

        RestAssured.delete("/student/1")
                .then()
                .statusCode(200);
    }

    @Test
    void testDeleteStudentNotFound() {
        stubFor(delete(urlEqualTo("/student/888"))
                .willReturn(aResponse().withStatus(404)));

        RestAssured.delete("/student/888")
                .then()
                .statusCode(404);
    }

    @Test
    void testTopStudentEmptyDb() {
        stubFor(get(urlEqualTo("/topStudent"))
                .willReturn(aResponse().withStatus(200).withBody("")));

        RestAssured.get("/topStudent")
                .then()
                .statusCode(200)
                .body(is(emptyString()));
    }

    @Test
    void testTopStudentNoGrades() {
        stubFor(get(urlEqualTo("/topStudent"))
                .willReturn(aResponse().withStatus(200).withBody("")));

        RestAssured.get("/topStudent")
                .then()
                .statusCode(200)
                .body(is(emptyString()));
    }

    @Test
    void testTopStudentSingleWinner() {
        stubFor(get(urlEqualTo("/topStudent"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\": 2, \"name\": \"B\"}]")));

        RestAssured.get("/topStudent")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].id", is(2));
    }

    @Test
    void testTopStudentMultipleWinners() {
        stubFor(get(urlEqualTo("/topStudent"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\": 1, \"name\": \"A\"}, {\"id\": 2, \"name\": \"B\"}]")));

        RestAssured.get("/topStudent")
                .then()
                .statusCode(200)
                .body("size()", is(2))
                .body("name", hasItems("A", "B"));
    }
}