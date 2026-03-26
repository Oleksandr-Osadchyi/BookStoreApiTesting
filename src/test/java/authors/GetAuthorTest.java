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

@Feature("Get Author")
public class GetAuthorTest extends BaseTest {

    AuthorsClient authorsClient = new AuthorsClient();

    @Description("Get existing author by id")
    @Test
    public void getAuthorByIdTest() {
        List<Author> authorList = authorsClient.getAuthors();
        Author randomAuthor = RandomUtil.getRandomItem(authorList);
        Author authorById = authorsClient.getAuthor(randomAuthor.getId());

        softly.assertThat(randomAuthor) // a good assertion message is already built in for this case
                .isEqualTo(authorById);
        softly.assertAll();
    }

    @Description("Get author by not existing id")
    @Test
    public void getAuthorByNotExistingIdTest() {
        new BookStoreObjectApiClient(BookStoreObject.AUTHORS)
                .getObjectNegativeCase(FakerUtil.nonExistingEntityId());
    }
}
