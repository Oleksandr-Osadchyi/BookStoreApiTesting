package api.books;

import lombok.*;
import utils.DateTimeUtil;
import utils.FakerUtil;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    @Nullable
    private String title;
    @Nullable
    private String description;
    private int pageCount;
    @Nullable
    private String excerpt;
    private String publishDate;

    public Book createBookModel(int id, String title, String description, int pageCount, String excerpt, String publishDate) {
        return Book.builder()
                .id(id)
                .title(title)
                .description(description)
                .pageCount(pageCount)
                .excerpt(excerpt)
                .publishDate(publishDate)
                .build();
    }

    ;

    public Book createRandomBookModel() {
        return Book.builder()
                .id(FakerUtil.randomNumber(1000, 100000))
                .title(FakerUtil.randomTitle())
                .description(FakerUtil.randomSentence())
                .pageCount(FakerUtil.randomNumber(1000, 100000))
                .excerpt(FakerUtil.randomSentence())
                .publishDate(DateTimeUtil.formatDateToString(LocalDateTime.now(), DateTimeUtil.FULL_ZONED_UTC_DATE_TIME_FORMAT))
                .build();
    }

    @Override
    public String toString() {
        return "Book { id:" + id + ", title:" + title + ", description:" + description + ", pageCount:" + pageCount + ", excerpt:" + excerpt + ", publishDate:" + publishDate + " }";
    }
}
