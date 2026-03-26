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

import java.util.List;

@Feature("Create Author")
public class CreateAuthorTest extends BaseTest {

    AuthorsClient authorsClient = new AuthorsClient();
    BookStoreObjectApiClient bookStoreObjectApiClient = new BookStoreObjectApiClient(BookStoreObject.AUTHORS);

    @Description("Create author")
    @Test
    public void createAuthorTest() {
        Author createAuthorModel = new Author().createRandomAuthorModel();
        authorsClient.createAuthor(createAuthorModel);

        Author authorById = authorsClient.getAuthor(createAuthorModel.getId());
        softly.assertThat(authorById)
                .isEqualTo(createAuthorModel);
        softly.assertAll();
    }

    @Description("Create author with nullable fields")
    @Test
    public void createAuthorWithNullableFieldsTest() {
        Author newAuthor = new Author().createRandomAuthorModel();
        newAuthor.setFirstName(null);
        newAuthor.setLastName(null);
        authorsClient.createAuthor(newAuthor);

        Author authorById = authorsClient.getAuthor(newAuthor.getId());
        softly.assertThat(authorById)
                .isEqualTo(newAuthor);
        softly.assertAll();
    }

    @Description("Create author with already existing id")
    @Test
    public void createAuthorWithAlreadyExistingIdTest() {
        List<Author> authorList = authorsClient.getAuthors();
        Author randomAuthor = RandomUtil.getRandomItem(authorList);

        Author createAuthorModel = new Author().createRandomAuthorModel();
        createAuthorModel.setId(randomAuthor.getId());
        bookStoreObjectApiClient
                .postObjectNegativeCase(createAuthorModel);
    }

    @Description("Create author with not nullable fields")
    @Test
    public void createAuthorWithNotNullableFieldsTest() {
        Author updateAuthorModelWithoutMandatoryFields = Author.builder()
                .firstName(FakerUtil.randomFirstName())
                .lastName(FakerUtil.randomLastName())
                .build();
        bookStoreObjectApiClient
                .postObjectNegativeCase(updateAuthorModelWithoutMandatoryFields);
    }
}
