package api.engine;

import lombok.Getter;

@Getter
public enum BookStoreObject implements HasName {
    AUTHORS("Authors"),
    BOOKS("Books"),
    ACTIVITIES("Activities"),
    COVER_PHOTOS("CoverPhotos"),
    USERS("Users");

    private final String name;

    BookStoreObject(String name) {
        this.name = name;
    }
}