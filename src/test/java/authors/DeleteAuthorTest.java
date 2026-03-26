package authors;


import api.authors.Author;
import api.authors.AuthorsClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import testengine.BaseTest;
import utils.RandomUtil;

import java.util.List;

public class GetAuthorTest extends BaseTest {

    @Feature("Get Author")
    @Description("Get existing author by id")
    @Test
    public void getAuthorByIdTest() {
        AuthorsClient authorsClient = new AuthorsClient();
        List<Author> authorList = authorsClient.getAuthors();
        Author randomAuthor = RandomUtil.getRandomItem(authorList);
        Author authorById = authorsClient.getAuthor(randomAuthor.getId());

        softly.assertThat(randomAuthor) // a good assertion message is already built in for this case
                .isEqualTo(authorById);
        softly.assertAll();
    }
}
