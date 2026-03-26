package api.engine;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResponseValidator {

    private final ValidatableResponse validatableResponse;

    public ResponseValidator(ValidatableResponse validatableResponse) {
        this.validatableResponse = validatableResponse;
    }

    public ResponseValidator validate(ResponseSpecification responseSpecification) {
        this.validatableResponse.spec(responseSpecification);
        return this;
    }

    public <T> T getRecord(Class<T> clazz) {
        return JacksonUtil.readObject(this.validatableResponse.extract().body().asString(), clazz);
    }

    public <T> List<T> getRecords(Class<T> clazz) {
        List<T> entityList = new ArrayList<>();
        JSONArray array1 = new JSONArray(this.validatableResponse.extract().body().asString());
        array1.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            entityList.add(JacksonUtil.readObject(obj.toString(), clazz));
        });
        return entityList;
    }
}
