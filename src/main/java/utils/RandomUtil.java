package utils;

import java.util.Collection;

public class RandomUtil {

    public static <T> T getRandomItem(Collection<T> collection) {
        return collection
                .stream()
                .skip((int) (collection.size() * Math.random()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Collection is empty"));
    }
}
