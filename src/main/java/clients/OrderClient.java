package clients;

import io.restassured.response.ValidatableResponse;
import pojo.*;

import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient {
    private int t;
    private int courierId;
    private int id;
    public ValidatableResponse create(CreateOrderRequest createOrderRequest) {
        return  given()
                .spec(getSpec())
                .body(createOrderRequest)
                .when()
                .post("/api/v1/orders")
                .then();
    }
    public ValidatableResponse getList(GetListOfOrderRequest getListOfOrderRequest) {
        return  given()
                .spec(getSpec())
                .body(getListOfOrderRequest)
                .when()
                .get("/api/v1/orders")
                .then();
    }
    public ValidatableResponse getListWithCourierId(int courierId) {
        return given()
                .spec(getSpec())
                .pathParam("courierId", courierId)
                .when()
                .get("/api/v1/orders?courierId={courierId}")
                .then();
    }
    public ValidatableResponse accept(int id, int courierId) {
        return given()
                .spec(getSpec())
                .pathParam("id", id)
                .pathParam("courierId", courierId)
                .when()
                .put("/api/v1/orders/accept/{id}?courierId={courierId}")
                .then();
    }
    public ValidatableResponse getOrderBytrack(int t) {
        return given()
                .spec(getSpec())
                .pathParam("t", t)
                .when()
                .get("/api/v1/orders/track?t={t}")
                .then();
    }
}
