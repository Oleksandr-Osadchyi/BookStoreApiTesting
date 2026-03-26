package authors;

import api.authors.Author;
import api.authors.AuthorsClient;
import api.engine.BookStoreObject;
import api.engine.BookStoreObjectApiClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import testengine.BaseTest;
import utils.FakerUtil;
import utils.RandomUtil;
import utils.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Feature("Update Author")
public class UpdateAuthorTest extends BaseTest {

    AuthorsClient authorsClient = new AuthorsClient();
    BookStoreObjectApiClient bookStoreObjectApiClient = new BookStoreObjectApiClient(BookStoreObject.AUTHORS);

    @Description("Update existing author by id")
    @Test
    public void updateAuthorTest() {
        List<Author> authorList = authorsClient.getAuthors();
        Author randomAuthor = RandomUtil.getRandomItem(authorList);

        Author updateAuthorModel = new Author().createRandomAuthorModel();
        authorsClient.updateAuthor(randomAuthor.getId(), updateAuthorModel);

        List<Author> authorListByOldId = authorsClient.getAuthors()
                .stream()
                .filter(author -> author.getId() == randomAuthor.getId()).collect(Collectors.toList());
        softly.assertThat(authorListByOldId)
                .withFailMessage(StringUtil.buildString(
                        Arrays.asList(
                                "old author " + randomAuthor,
                                "was found in list of authors from get /api/v1/Authors api result",
                                "by old id after update"
                        ),
                        "\n"
                ))
                .isEmpty();

        List<Author> authorListByOldBookId = authorsClient.getAuthorsByBookId(randomAuthor.getIdBook())
                .stream()
                .filter(author -> author.getId() == randomAuthor.getId()).collect(Collectors.toList());
        softly.assertThat(authorListByOldBookId)
                .withFailMessage(StringUtil.buildString(
                        Arrays.asList(
                                "old author " + randomAuthor,
                                "was found in list of authors from get /api/v1/Authors/authors/books/{idBook}",
                                "by old id after update"
                        ),
                        "\n"
                ))
                .isEmpty();

        List<Author> authorListByUpdatedId = authorsClient.getAuthors()
                .stream()
                .filter(author -> author.getId() == updateAuthorModel.getId()).collect(Collectors.toList());
        softly.assertThat(authorListByUpdatedId)
                .withFailMessage(StringUtil.buildString(
                        Arrays.asList(
                                "updated author " + updateAuthorModel,
                                "was not found in list of authors from get /api/v1/Authors api result",
                                "by new id after update"
                        ),
                        "\n"
                ))
                .isNotEmpty();

        if (!authorListByUpdatedId.isEmpty()) {
            softly.assertThat(authorListByUpdatedId.stream().findFirst().get())
                    .isEqualTo(updateAuthorModel);
        }

        List<Author> authorListByUpdatedBookId = authorsClient.getAuthorsByBookId(randomAuthor.getIdBook())
                .stream()
                .filter(author -> author.getId() == updateAuthorModel.getId()).collect(Collectors.toList());
        softly.assertThat(authorListByUpdatedBookId)
                .withFailMessage(StringUtil.buildString(
                        Arrays.asList(
                                "updated author " + randomAuthor,
                                "was not found in list of authors from get /api/v1/Authors/authors/books/{idBook}",
                                "by new id after update"
                        ),
                        "\n"
                ))
                .isNotEmpty();
        if (!authorListByUpdatedBookId.isEmpty()) {
            softly.assertThat(authorListByUpdatedBookId.stream().findFirst().get())
                    .isEqualTo(updateAuthorModel);
        }
        softly.assertAll();
    }

    @Description("Update author by not exising id")
    @Test
    public void updateAuthorByNotExistingIdTest() {
        bookStoreObjectApiClient
                .updateNonExistingObject(FakerUtil.nonExistingEntityId(), new Author().createRandomAuthorModel());
    }

    @Description("Update author with nullable fields")
    @Test
    public void updateAuthorWithNullableFieldsTest() {
        List<Author> authorList = authorsClient.getAuthors();
        Author randomAuthor = RandomUtil.getRandomItem(authorList);

        randomAuthor.setFirstName(null);
        randomAuthor.setLastName(null);
        authorsClient.updateAuthor(randomAuthor.getId(), randomAuthor);

        Author authorById = authorsClient.getAuthor(randomAuthor.getId());
        softly.assertThat(authorById)
                .isEqualTo(randomAuthor);
        softly.assertAll();
    }

    @Description("Update author with not nullable fields")
    @Test
    public void updateAuthorWithNotNullableFieldsTest() {
        List<Author> authorList = authorsClient.getAuthors();
        Author randomAuthor = RandomUtil.getRandomItem(authorList);

        Author updateAuthorModelWithoutMandatoryFields = Author.builder()
                .firstName(FakerUtil.randomFirstName())
                .lastName(FakerUtil.randomLastName())
                .build();
        bookStoreObjectApiClient
                .updateObjectWithoutMandatoryFields(randomAuthor.getId(), updateAuthorModelWithoutMandatoryFields);
    }
}
