package api.engine;

import io.qameta.allure.Step;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.*;

public class ResponseSpecifications {

    @Step
    public static ResponseSpecification responseCode200Spec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(StatusCodes.STATUS_CODE_200)
                .build();
    }

    @Step
    public static ResponseSpecification objectNotFoundSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(StatusCodes.STATUS_CODE_404)
                .expectBody("title", is("Not Found"))
                .build();
    }

    @Step
    public static ResponseSpecification responseCode404Spec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(StatusCodes.STATUS_CODE_404)
                .build();
    }

    @Step
    public static ResponseSpecification responseCode400Spec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(StatusCodes.STATUS_CODE_400)
                .build();
    }
}
