package api.author;

import api.engine.BookStoreObject;
import api.engine.BookStoreObjectApiClient;
import api.engine.ResponseSpecifications;

import java.util.List;

public class AuthorClient {

    private static final BookStoreObjectApiClient bookStoreObjectApiService
            = new BookStoreObjectApiClient(BookStoreObject.AUTHORS);

    public Author createAuthor(Author author) {
        return bookStoreObjectApiService
                .postObject(author)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecord(Author.class);
    }

    public List<Author> getAuthors() {
        return bookStoreObjectApiService
                .getObjects()
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecords(Author.class);
    }

    public List<Author> getAuthorsByBookId(String bookId) {
        return bookStoreObjectApiService
                .getObjectsBy("/Authors/authors/books/" + bookId)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecords(Author.class);
    }

    public Author getAuthor(int authorId) {
        return bookStoreObjectApiService
                .getObject(authorId)
                .validate(ResponseSpecifications.responseCode200Spec())
                .getRecord(Author.class);
    }

    public void updateAuthor(int authorId, Author author) {
        bookStoreObjectApiService
                .updateObject(authorId, author)
                .validate(ResponseSpecifications.responseCode200Spec());
    }

    public void deleteAuthor(int authorId) {
        bookStoreObjectApiService
                .deleteObject(authorId)
                .validate(ResponseSpecifications.responseCode200Spec());
    }
}
