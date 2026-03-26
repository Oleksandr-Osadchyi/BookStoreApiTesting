package authors;

import api.authors.Author;
import api.authors.AuthorsClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import testengine.BaseTest;
import utils.FakerUtil;
import utils.RandomUtil;

import java.util.List;
import java.util.stream.Collectors;

@Feature("Get Authors")
public class GetAuthorsTest extends BaseTest {

    AuthorsClient authorsClient = new AuthorsClient();
    List<Author> authorsList = authorsClient.getAuthors();

    @Description("Check Authors getting test")
    @Test
    public void getAuthorsTest() {
        softly.assertThat(authorsList)
                .withFailMessage("authors list is empty")
                .isNotEmpty();
        authorsList.forEach(author -> softly.assertThat(author.getId())
                .withFailMessage(author + " has null value for mandatory field id")
                .isNotNull());
        authorsList.forEach(author -> softly.assertThat(author.getIdBook())
                .withFailMessage(author + " has null value for mandatory field idBook")
                .isNotNull());
        softly.assertAll();
    }

    @Description("Get authors by bookId set for some authors")
    @Test
    public void getAuthorsByBookIdTest() {
        Author randomAuthor = RandomUtil.getRandomItem(authorsList);
        List<Author> authorsFilteredByBookId = authorsList
                .stream()
                .filter(author -> author.getIdBook() == randomAuthor.getIdBook()).collect(Collectors.toList());
        List<Author> authorsFetchedByBookId = authorsClient.getAuthorsByBookId(randomAuthor.getIdBook());

        softly.assertThat(authorsFilteredByBookId)
                // a good assertion message is already built in for this case
                .isEqualTo(authorsFetchedByBookId);
        softly.assertThat(authorsFetchedByBookId)
                .contains(randomAuthor);
        softly.assertAll();
    }

    @Description("Get authors by not existing book id")
    @Test
    public void getAuthorsByNotExistingBookIdTest() {
        List<Author> authorsFetchedByBookId = authorsClient.getAuthorsByBookId(FakerUtil.nonExistingEntityId());
        softly.assertThat(authorsFetchedByBookId)
                .isEmpty();
        softly.assertAll();
    }
}
