package api.authors;

import lombok.*;
import utils.FakerUtil;

import javax.annotation.Nullable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private int id;
    private int idBook;
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;

    public Author createAuthorModel(int authorId, int bookId, String firstName, String lastName) {
        return Author.builder()
                .id(authorId)
                .idBook(bookId)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    };

    public Author createRandomAuthorModel() {
        return Author.builder()
                .id(FakerUtil.randomNumber(1000, 100000))
                .idBook(FakerUtil.randomNumber(1000, 100000))
                .firstName(FakerUtil.randomFirstName())
                .lastName(FakerUtil.randomLastName())
                .build();
    }

    @Override
    public String toString() {
        return "Author { id:" + id + ", idBook:" + idBook + ", firstName:" + firstName + ", lastName:" + lastName + " }";
    }

}