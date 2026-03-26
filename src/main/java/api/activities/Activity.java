package api.authors;

import lombok.*;
import utils.FakerUtil;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {
    private int id;
    private int idBook;
    private String firstName;
    private String lastName;

    public Author createAuthorModel(int authorId,int bookId, String firstName, String lastName) {
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
}