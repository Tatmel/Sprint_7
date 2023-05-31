package clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import pojo.CreateCourierRequest;
import pojo.LoginCourierRequest;

import static io.restassured.RestAssured.given;

public class CourierClient extends BaseClient {
    private final String courierBaseUri = "/api/v1/courier";
    @Step("Создать курьера")
    public ValidatableResponse create(CreateCourierRequest createCourierRequest) {
        return  given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post(courierBaseUri)
                .then();
    }
    @Step("Авторизоваться под курьером")
    public ValidatableResponse login(LoginCourierRequest loginCourierRequest) {
        return given()
                .spec(getSpec())
                .body(loginCourierRequest)
                .when()
                .post(courierBaseUri + "/login")
                .then();
    }
    @Step("Постусловия: Удалить курьера")
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpec())
                .pathParam("id", id)
                .when()
                .delete(courierBaseUri + "/{id}")
                .then();
    }
}
