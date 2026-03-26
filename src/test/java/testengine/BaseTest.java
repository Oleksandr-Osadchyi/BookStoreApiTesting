package testengine;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import org.testng.asserts.SoftAssert;

public abstract class BaseTest {

    // AssertJ soft assert
    public SoftAssertions softly;

    // TestNG soft assert
    public SoftAssert soft;

    @BeforeClass(alwaysRun = true)
    public void beforeActions() {
        soft = new SoftAssert();
        softly = new SoftAssertions();
    }

    @AfterSuite(alwaysRun = true)
    public void afterActions() {
        //new ReportGenerator(ConfigurationBuilder.bundled().build()).generate(destination, source)
    }


}
