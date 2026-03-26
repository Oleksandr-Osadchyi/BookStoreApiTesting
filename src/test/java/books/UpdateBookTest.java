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
import utils.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Feature("Update Book")
public class UpdateBookTest extends BaseTest {

    BooksClient booksClient = new BooksClient();
    BookStoreObjectApiClient bookStoreObjectApiClient = new BookStoreObjectApiClient(BookStoreObject.BOOKS);

    @Description("Update existing Book by id")
    @Test
    public void updateBookTest() {
        List<Book> bookList = booksClient.getBooks();
        Book randomBook = RandomUtil.getRandomItem(bookList);

        Book updateBookModel = new Book().createRandomBookModel();
        booksClient.updateBook(randomBook.getId(), updateBookModel);

        List<Book> bookListByOldId = booksClient.getBooks()
                .stream()
                .filter(book -> book.getId() == randomBook.getId()).collect(Collectors.toList());
        softly.assertThat(bookListByOldId)
                .withFailMessage(StringUtil.buildString(
                        Arrays.asList(
                                "old Book " + randomBook,
                                "was found in list of Books from get /api/v1/Books api result",
                                "by old id after update"
                        ),
                        "\n"
                ))
                .isEmpty();


        List<Book> bookListByUpdatedId = booksClient.getBooks()
                .stream()
                .filter(Book -> Book.getId() == updateBookModel.getId()).collect(Collectors.toList());
        softly.assertThat(bookListByUpdatedId)
                .withFailMessage(StringUtil.buildString(
                        Arrays.asList(
                                "updated Book " + updateBookModel,
                                "was not found in list of Books from get /api/v1/Books api result",
                                "by new id after update"
                        ),
                        "\n"
                ))
                .isNotEmpty();

        if (!bookListByUpdatedId.isEmpty()) {
            softly.assertThat(bookListByUpdatedId.stream().findFirst().get())
                    .isEqualTo(updateBookModel);
        }
        softly.assertAll();
    }

    @Description("Update Book by not exising id")
    @Test
    public void updateBookByNotExistingIdTest() {
        bookStoreObjectApiClient
                .updateObjectNegativeCase(FakerUtil.nonExistingEntityId(), new Book().createRandomBookModel());
    }

    @Description("Update Book with nullable fields")
    @Test
    public void updateBookWithNullableFieldsTest() {
        List<Book> BookList = booksClient.getBooks();
        Book randomBook = RandomUtil.getRandomItem(BookList);
        randomBook.setTitle(null);
        randomBook.setDescription(null);
        randomBook.setExcerpt(null);
        booksClient.updateBook(randomBook.getId(), randomBook);

        Book bookById = booksClient.getBook(randomBook.getId());
        softly.assertThat(bookById)
                .isEqualTo(randomBook);
        softly.assertAll();
    }

    @Description("Update Book with not nullable fields")
    @Test
    public void updateBookWithNotNullableFieldsTest() {
        List<Book> BookList = booksClient.getBooks();
        Book randomBook = RandomUtil.getRandomItem(BookList);

        Book updateBookModelWithoutMandatoryFields = Book.builder()
                .title(FakerUtil.randomTitle())
                .description(FakerUtil.randomSentence())
                .excerpt(FakerUtil.randomSentence())
                .build();
        bookStoreObjectApiClient
                .updateObjectNegativeCase(randomBook.getId(), updateBookModelWithoutMandatoryFields);
    }
}
