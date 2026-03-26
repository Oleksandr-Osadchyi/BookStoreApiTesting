package api.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonUtil {

    private final ObjectMapper MAPPER = new ObjectMapper();

    public <T> T readObject(String json, Class<T> clazz) {
        try {
            return MAPPER
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .findAndRegisterModules().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                    .readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(String.format("Couldn't map json [%s] to class[%s]", json, clazz.getSimpleName()), e);
        }
    }
}
