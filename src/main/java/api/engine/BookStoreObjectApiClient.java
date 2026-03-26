package api.engine;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BookStoreObjectApiClient {

    private final RequestSpecifications requestSpecifications;
    private final BookStoreObject bookStoreObject;
    private ValidatableResponse validatableResponse;

    public BookStoreObjectApiClient(BookStoreObject bookStoreObject) {
        this.bookStoreObject = bookStoreObject;
        requestSpecifications = new RequestSpecifications();
    }

    @Step()
    public ResponseValidator postObject(Object body) {
        validatableResponse = given()
                .spec(requestSpecifications.objectsPathSpec(bookStoreObject))
                .body(body)
                .when()
                .post()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    @Step
    public ResponseValidator updateObject(int objectId, Object body) {
        validatableResponse = given()
                .spec(requestSpecifications.objectPathSpec(bookStoreObject, objectId))
                .body(body)
                .when()
                .put()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    @Step
    public ResponseValidator deleteObject(int objectId) {
        validatableResponse = given()
                .spec(requestSpecifications.objectPathSpec(bookStoreObject, objectId))
                .when()
                .delete()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    @Step
    public ResponseValidator getObject(int objectId) {
        validatableResponse = given()
                .spec(requestSpecifications.objectPathSpec(bookStoreObject, objectId))
                .when()
                .get()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    @Step
    public ResponseValidator getObjects() {
        validatableResponse = given()
                .spec(requestSpecifications.objectsPathSpec(bookStoreObject))
                .when()
                .get()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    public ResponseValidator getObjectsBy(String specialPath) {
        validatableResponse = given()
                .spec(requestSpecifications.specialSpec(specialPath))
                .when()
                .get()
                .then().assertThat();
        return new ResponseValidator(validatableResponse);
    }

    @Step
    public ResponseValidator getObjectNegativeCase(int objectId) {
        validatableResponse = given()
                .spec(requestSpecifications.objectPathSpec(bookStoreObject, objectId))
                .when()
                .get()
                .then().assertThat();
        return new ResponseValidator(validatableResponse).validate(ResponseSpecifications.objectNotFoundSpec());
    }

    @Step
    public ResponseValidator deleteObjectNegativeCase(int objectId) {
        validatableResponse = given()
                .spec(requestSpecifications.objectPathSpec(bookStoreObject, objectId))
                .when()
                .delete()
                .then().assertThat();
        return new ResponseValidator(validatableResponse).validate(ResponseSpecifications.responseCode404Spec());
    }

    @Step
    public ResponseValidator updateObjectNegativeCase(int objectId, Object body) {
        validatableResponse = given()
                .spec(requestSpecifications.objectPathSpec(bookStoreObject, objectId))
                .body(body)
                .when()
                .put()
                .then().assertThat();
        return new ResponseValidator(validatableResponse).validate(ResponseSpecifications.responseCode404Spec());
    }

    @Step
    public ResponseValidator postObjectNegativeCase(Object body) {
        validatableResponse = given()
                .spec(requestSpecifications.objectsPathSpec(bookStoreObject))
                .body(body)
                .when()
                .post()
                .then().assertThat();
        return new ResponseValidator(validatableResponse).validate(ResponseSpecifications.responseCode404Spec());
    }
}

