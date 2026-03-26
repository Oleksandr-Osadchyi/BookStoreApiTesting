package api.coverPhotos;


import api.engine.BookStoreObject;
import api.engine.BookStoreObjectApiClient;
import api.engine.ResponseSpecifications;

import java.util.List;

public class CoverPhotosClient {

    private static final BookStoreObjectApiClient bookStoreObjectApiService
            = new BookStoreObjectApiClient(BookStoreObject.COVER_PHOTOS);

    public CoverPhoto createCoverPhoto(CoverPhoto coverPhoto) {
        return bookStoreObjectApiService
                .postObject(coverPhoto)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecord(CoverPhoto.class);
    }

    public List<CoverPhoto> getCoverPhotos() {
        return bookStoreObjectApiService
                .getObjects()
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecords(CoverPhoto.class);
    }

    public List<CoverPhoto> getCoverPhotosByBookId(String bookId) {
        return bookStoreObjectApiService
                .getObjectsBy("/CoverPhotos/books/covers/" + bookId)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecords(CoverPhoto.class);
    }

    public CoverPhoto getCoverPhoto(int coverPhotoId) {
        return bookStoreObjectApiService
                .getObject(coverPhotoId)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecord(CoverPhoto.class);
    }

    public void updateCoverPhoto(int coverPhotoId, CoverPhoto coverPhoto) {
        bookStoreObjectApiService
                .updateObject(coverPhotoId, coverPhoto)
                .validate(ResponseSpecifications.responseCode200Spec());
    }

    public void deleteCoverPhoto(int coverPhotoId) {
        bookStoreObjectApiService
                .deleteObject(coverPhotoId)
                .validate(ResponseSpecifications.responseCode200Spec());
    }
}
