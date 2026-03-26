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

@Feature("Create Book")
public class CreateBookTest extends BaseTest {

    BooksClient booksClient = new BooksClient();
    BookStoreObjectApiClient bookStoreObjectApiClient = new BookStoreObjectApiClient(BookStoreObject.BOOKS);

    @Description("Create book")
    @Test
    public void createBookTest() {
        Book createBookModel = new Book().createRandomBookModel();
        booksClient.createBook(createBookModel);

        Book BookById = booksClient.getBook(createBookModel.getId());
        softly.assertThat(BookById)
                .isEqualTo(createBookModel);
        softly.assertAll();
    }

    @Description("Create Book with nullable fields")
    @Test
    public void createBookWithNullableFieldsTest() {
        Book newBook = new Book().createRandomBookModel();
        newBook.setTitle(null);
        newBook.setDescription(null);
        newBook.setExcerpt(null);
        booksClient.createBook(newBook);

        Book BookById = booksClient.getBook(newBook.getId());
        softly.assertThat(BookById)
                .isEqualTo(newBook);
        softly.assertAll();
    }

    @Description("Create Book with already existing id")
    @Test
    public void createBookWithAlreadyExistingIdTest() {
        List<Book> BookList = booksClient.getBooks();
        Book randomBook = RandomUtil.getRandomItem(BookList);

        Book createBookModel = new Book().createRandomBookModel();
        createBookModel.setId(randomBook.getId());
        bookStoreObjectApiClient
                .postObjectNegativeCase(createBookModel);
    }

    @Description("Create Book with not nullable fields")
    @Test
    public void createBookWithNotNullableFieldsTest() {
        Book updateBookModelWithoutMandatoryFields = Book.builder()
                .title(FakerUtil.randomTitle())
                .description(FakerUtil.randomSentence())
                .excerpt(FakerUtil.randomSentence())
                .build();
        bookStoreObjectApiClient
                .postObjectNegativeCase(updateBookModelWithoutMandatoryFields);
    }
}
