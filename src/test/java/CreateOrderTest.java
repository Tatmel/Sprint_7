import clients.CourierClient;
import clients.OrderClient;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private Integer track;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Выбранный цвет/Выбранные цвета: {8}")
    public static Object[][] createOrder() {
        return new Object[][] {
                {"John", "Doe", "123 Main St", "Central Station", "1234567890", 3, "2023-05-30", "Test comment", new String[]{"BLACK"}},
                {"Jane", "Smith", "456 Elm St", "City Station", "0987654321", 5, "2023-06-01", "Another comment", new String[]{"GREY"}},
                {"Alice", "Johnson", "789 Oak St", "Downtown Station", "5555555555", 7, "2023-06-05", "Additional comment", new String[]{"BLACK", "GREY"}},
                {"Bob", "Williams", "321 Pine St", "Subway Station", "7777777777", 4, "2023-06-02", "Extra comment", new String[]{}}
        };
    }
    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @Test
    public void createOrderBeSuccessful() {
    track =    given()
                .contentType(ContentType.JSON)
                .baseUri("http://qa-scooter.praktikum-services.ru/")
                .body(createOrder())
                .when()
                .post("/api/v1/orders")
                .then()
                .statusCode(201)
                .assertThat().body("track", Matchers.notNullValue())
                .extract().jsonPath().get("track");
    }
   @After
    public void tearDown() {
        if (track != null) {
            given()
                    .contentType(ContentType.JSON)
                    .baseUri("http://qa-scooter.praktikum-services.ru/")
                    .pathParam("track", track)
                    .when()
                    .put("/api/v1/orders/cancel/{track}");
        }
    }
}
