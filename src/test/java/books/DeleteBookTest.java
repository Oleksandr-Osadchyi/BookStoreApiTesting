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

@Feature("Delete Book")
public class DeleteBookTest extends BaseTest {

    BooksClient booksClient = new BooksClient();

    @Description("Delete existing book by id")
    @Test
    public void deleteBooksTest() {
        List<Book> booksList = booksClient.getBooks();
        Book randomBook = RandomUtil.getRandomItem(booksList);

        booksClient.deleteBook(randomBook.getId());

        List<Book> bookListById = booksClient.getBooks()
                .stream()
                .filter(Book -> Book.getId() == randomBook.getId()).collect(Collectors.toList());
        softly.assertThat(bookListById)
                .withFailMessage(StringUtil.buildString(
                        Arrays.asList(
                                "deleted Book" + randomBook,
                                "was found in list of Books from get /api/v1/Books api result"
                        ),
                        "\n"
                ))
                .isEmpty();
        softly.assertAll();
    }

    @Description("Delete book by not exising id")
    @Test
    public void deleteBookByNotExistingTest() {
        new BookStoreObjectApiClient(BookStoreObject.BOOKS)
                .deleteObjectNegativeCase(FakerUtil.nonExistingEntityId());
    }
}
