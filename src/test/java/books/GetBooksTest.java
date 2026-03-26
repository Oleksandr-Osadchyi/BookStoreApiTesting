package authors;


import api.authors.Author;
import api.authors.AuthorsClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import testengine.BaseTest;

import java.util.List;

public class GetAuthorsTest extends BaseTest {

    @Feature("Get Authors")
    @Description("Check Authors getting test")
    @Test
    public void checkBooksGetTest() {
        AuthorsClient authorsClient = new AuthorsClient();
        List<Author> authorList = authorsClient.getAuthors();

        softly.assertThat(authorList)
                .withFailMessage("authors list is empty")
                .isNotEmpty();
        authorList.forEach(author -> softly.assertThat(author.getId())
                .withFailMessage(author + " has null value for mandatory field id")
                .isNotNull());
        authorList.forEach(author -> softly.assertThat(author.getIdBook())
                .withFailMessage(author + " has null value for mandatory field idBook")
                .isNotNull());
        softly.assertAll();
    }
}
