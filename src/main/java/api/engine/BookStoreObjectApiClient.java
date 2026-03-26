package api.engine;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BookStoreObjectApiService {

    private final RequestSpecifications requestSpecifications;
    private ValidatableResponse validatableResponse;

    public BookStoreObjectApiService() {
        requestSpecifications = new RequestSpecifications();
    }

    @Step
    public <T extends Enum<T> & HasName> ResponseValidator sendPost(BookStoreObject sObject, GenericModel<T> model) {
        validatableResponse = given()
                .spec(requestSpecifications.createBookStoreObjectSpec(sObject, model))
                .when()
                .post()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    @Step
    public <T extends Enum<T> & HasName> ResponseValidator sendPut(BookStoreObject sObject, String resourceId, GenericModel<T> model) {
        validatableResponse = given()
                .spec(requestSpecifications.updateBookStoreObjectSpec(sObject, resourceId, model))
                .when()
                .put()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    @Step
    public ResponseValidator sendDelete(BookStoreObject sObject, String resourceId) {
        validatableResponse = given()
                .spec(requestSpecifications.bookStoreObject(sObject, resourceId))
                .when()
                .delete()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    @Step
    public ResponseValidator sendGetObject(BookStoreObject sObject, String id) {
        validatableResponse = given()
                .spec(requestSpecifications.bookStoreObject(sObject, id))
                .when()
                .get()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    @Step
    public ResponseValidator sendGetObjects(BookStoreObject sObject, String id) {
        validatableResponse = given()
                .spec(requestSpecifications.bookStoreObjects(sObject))
                .when()
                .get()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }
}

