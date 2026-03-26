package books;

import api.books.Book;
import api.books.BooksClient;
import api.engine.BookStoreObject;
import api.engine.BookStoreObjectApiClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import testengine.BaseTest;
import utils.FakerUtil;
import utils.RandomUtil;

import java.util.List;

@Feature("Get Book")
public class GetBookTest extends BaseTest {

    BooksClient booksClient = new BooksClient();

    @Description("Get existing book by id")
    @Test
    public void getBookByIdTest() {
        List<Book> bookList = booksClient.getBooks();
        Book randomBook = RandomUtil.getRandomItem(bookList);
        Book bookById = booksClient.getBook(randomBook.getId());

        softly.assertThat(randomBook) // a good assertion message is already built in for this case
                .isEqualTo(bookById);
        softly.assertAll();
    }

    @Description("Get book by not existing id")
    @Test
    public void getBookByNotExistingIdTest() {
        new BookStoreObjectApiClient(BookStoreObject.BOOKS)
                .getObjectNegativeCase(FakerUtil.nonExistingEntityId());
    }
}
