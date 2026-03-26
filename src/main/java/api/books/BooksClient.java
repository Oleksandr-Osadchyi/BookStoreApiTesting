package api.book;

import api.engine.BookStoreObject;
import api.engine.BookStoreObjectApiClient;
import api.engine.ResponseSpecifications;

import java.util.List;

public class BookClient {

    private static final BookStoreObjectApiClient bookStoreObjectApiService = new BookStoreObjectApiClient(BookStoreObject.BOOKS);
    
    public Book createBook(Book book) {
        return bookStoreObjectApiService
                .postObject(book)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecord(Book.class);
    }

    public List<Book> getBooks() {
        return bookStoreObjectApiService
                .getObjects()
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecords(Book.class);
    }

    public Book getBook(int bookId) {
        return bookStoreObjectApiService
                .getObject(bookId)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecord(Book.class);
    }

    public void updateBook(int bookId, Book book) {
        bookStoreObjectApiService
                .updateObject(bookId, book)
                .validate(ResponseSpecifications.responseCode200Spec());
    }

    public void deleteBook(int bookId) {
        bookStoreObjectApiService
                .deleteObject(bookId)
                .validate(ResponseSpecifications.responseCode200Spec());
    }
}
