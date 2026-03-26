package api.coverPhotos;

import lombok.*;
import utils.FakerUtil;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoverPhoto {
    private int id;
    private int idBook;
    private String url;

    public CoverPhoto createCoverPhotoModel(int coverPhotoId, int bookId, String url) {
        return CoverPhoto.builder()
                .id(coverPhotoId)
                .idBook(bookId)
                .url(url)
                .build();
    }

    public CoverPhoto createRandomCoverPhotoModel() {
        return CoverPhoto.builder()
                .id(FakerUtil.randomNumber(1000, 100000))
                .idBook(FakerUtil.randomNumber(1000, 100000))
                .url(FakerUtil.randomUrl())
                .build();
    }
}