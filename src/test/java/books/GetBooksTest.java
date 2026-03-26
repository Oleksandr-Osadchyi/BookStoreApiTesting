package books;


import api.books.Book;
import api.books.BooksClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import testengine.BaseTest;

import java.util.List;

public class GetBooksTest extends BaseTest {

    @Feature("Get Books")
    @Description("Check Books getting test")
    @Test
    public void checkGetBooksTest() {
        BooksClient booksClient = new BooksClient();
        List<Book> bookList = booksClient.getBooks();

        softly.assertThat(bookList)
                .withFailMessage("books list is empty")
                .isNotEmpty();
        bookList.forEach(author -> softly.assertThat(author.getId())
                .withFailMessage(author + " has null value for mandatory field id")
                .isNotNull());
        bookList.forEach(author -> softly.assertThat(author.getPageCount())
                .withFailMessage(author + " has null value for mandatory field pageCount")
                .isNotNull());
        bookList.forEach(author -> softly.assertThat(author.getPublishDate())
                .withFailMessage(author + " has null value for mandatory field publishDate")
                .isNotNull());
        softly.assertAll();
    }
}
