import api.authors.AuthorsClient;
import api.engine.BookStoreObjectApiClient;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public abstract class BaseTest {

    public static BookStoreObjectApiClient bookStoreObjectApiService;
    public static AuthorsClient authorsClient;

    // AssertJ soft assert
    public SoftAssertions softly;

    // TestNG soft assert
    public SoftAssert soft;

    @BeforeClass(alwaysRun = true)
    public void beforeActions() {
        authorsClient = new AuthorsClient();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        soft = new SoftAssert();
        softly = new SoftAssertions();
    }
}
