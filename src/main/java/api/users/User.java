package api.books;

import lombok.*;
import utils.DateTimeUtil;
import utils.FakerUtil;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {
    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String excerpt;
    private String publishDate;

    public Book createBookModel(int id, String title, String description, int pageCount, String excerpt, String publishDate) {
        return Book.builder()
                .id(id)
                .title(title)
                .description(description)
                .pageCount(id)
                .excerpt(excerpt)
                .publishDate(publishDate)
                .build();
    };

    public Book createRandomBookModel() {
        return Book.builder()
                .id(FakerUtil.randomNumber(1000, 100000))
                .title(FakerUtil.randomTitle())
                .description(description)
                .pageCount(FakerUtil.randomNumber(1000, 100000))
                .excerpt(FakerUtil.randomSentence())
                .publishDate(DateTimeUtil.formatDateToString(LocalDateTime.now(), DateTimeUtil.FULL_ZONED_UTC_DATE_TIME_FORMAT))
                .build();
    }
}
