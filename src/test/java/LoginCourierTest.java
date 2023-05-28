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

public class LoginCourierTest {
    private CourierClient courierClient = new CourierClient();
    private Integer id;
    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @Test
    public void courierShouldBeAuthorized() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        //создание
        courierClient.create(createCourierRequest);

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest)
                .statusCode(200)
                .assertThat().body("id", Matchers.notNullValue())
                .extract().jsonPath().get("id");
    }
    @Test
    public void nonExistCourierDontBeAuthorized() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest)
                .statusCode(404)
                .body("message", Matchers.equalTo("Учетная запись не найдена"))
                .extract().jsonPath().get("id");
    }
    @Test
    public void courierWithoutPasswordDontBeAuthorized() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        // Попытка авторизации без пароля
        loginCourierRequest.setPassword("");

        courierClient.login(loginCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }
    @Test
    public void courierWithoutLoginDontBeAuthorized() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        // Попытка авторизации без логина
        loginCourierRequest.setLogin("");

        courierClient.login(loginCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }
    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                    .statusCode(200);
        }
    }
}