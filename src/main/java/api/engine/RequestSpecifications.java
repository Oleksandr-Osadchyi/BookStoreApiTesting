package api.engine;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {

    @Step
    public RequestSpecification objectPathSpec(BookStoreObject object, int objectId) {
        return getBaseSpec()
                .basePath(object.getName() + "/" + objectId);
    }

    @Step
    public RequestSpecification objectsPathSpec(BookStoreObject object) {
        return getBaseSpec()
                .basePath(object.getName() + "/");
    }

    @Step
    public RequestSpecification specialSpec(String specialPath) {
        return getBaseSpec()
                .basePath(specialPath);
    }

    private RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(PropertyHelper.readFromPropertyFile("base_url"))
                .setContentType(ContentType.JSON)
                .build();
    }
}
