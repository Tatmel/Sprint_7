import clients.CourierClient;
import dataprovider.CourierProvider;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.CreateCourierRequest;
import pojo.LoginCourierRequest;

public class CreateCourierTest {
    private CourierClient courierClient = new CourierClient();
    private Integer id;
    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @Test
    public void courierShouldBeCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        //создание
        courierClient.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));

        //логин
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest)
                .statusCode(200)
                .extract().jsonPath().get("id");
    }

    @Test
    public void sameCourierDontBeCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        //создание
        courierClient.create(createCourierRequest);

        courierClient.create(createCourierRequest)
                .statusCode(409)
                .body("message", Matchers.equalTo("Этот логин уже используется. Попробуйте другой."));
        //логин
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest)
                .extract().jsonPath().get("id");
    }
    @Test
    public void courierBeCreatedWithoutFirstName() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomWithoutFirstNameCourierRequest();
        //создание
        courierClient.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));

        //логин
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest)
                .statusCode(200)
                .extract().jsonPath().get("id");
    }
    @Test
    public void courierDontBeCreatedWithoutLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomWithoutLoginCourierRequest();

        //создание
        courierClient.create(createCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    public void courierDontBeCreatedWithoutPassword() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomWithoutPasswordCourierRequest();

        //создание
        courierClient.create(createCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }
    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                    .statusCode(200);
        }
    }
}