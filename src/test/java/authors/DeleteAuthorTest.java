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

@Feature("Delete Author")
public class DeleteAuthorTest extends BaseTest {

    AuthorsClient authorsClient = new AuthorsClient();

    @Description("Delete existing author by id")
    @Test
    public void deleteAuthorTest() {
        List<Author> authorList = authorsClient.getAuthors();
        Author randomAuthor = RandomUtil.getRandomItem(authorList);

        authorsClient.deleteAuthor(randomAuthor.getId());

        List<Author> authorListById = authorsClient.getAuthors()
                .stream()
                .filter(author -> author.getId() == randomAuthor.getId()).collect(Collectors.toList());
        softly.assertThat(authorListById)
                .withFailMessage(StringUtil.buildString(
                        Arrays.asList(
                                "deleted author" + randomAuthor,
                                "was found in list of authors from get /api/v1/Authors api result"
                        ),
                        "\n"
                ))
                .isEmpty();

        List<Author> authorListByBookId = authorsClient.getAuthorsByBookId(randomAuthor.getIdBook())
                .stream()
                .filter(author -> author.getId() == randomAuthor.getId()).collect(Collectors.toList());
        softly.assertThat(authorListByBookId)
                .withFailMessage(StringUtil.buildString(
                        Arrays.asList(
                                "deleted author" + randomAuthor,
                                "was found in list of authors from get /api/v1/Authors/authors/books/{idBook} api result"
                        ),
                        "\n"
                ))
                .isEmpty();
        softly.assertAll();
    }

    @Description("Delete author by not exising id")
    @Test
    public void deleteAuthorByNotExistingTest() {
        new BookStoreObjectApiClient(BookStoreObject.AUTHORS)
                .deleteObjectNegativeCase(FakerUtil.nonExistingEntityId());
    }
}
