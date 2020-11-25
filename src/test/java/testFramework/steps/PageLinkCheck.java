package testFramework.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import testFramework.helpers.ResourceLocator;
import testFramework.objects.W3cLinkChecker;

public class PageLinkCheck {
    final long timeOutSeconds = 120; // the link checker can be quite slow

    private W3cLinkChecker w3cLinkChecker;
    private String url;

    @Given("the w3c link checker reviews the file {string}")
    public void theW3CLinkCheckerReviewsTheFile(String urlOfFile) {
        url = ResourceLocator.interpretURL(urlOfFile);
        try {
            w3cLinkChecker = new W3cLinkChecker(url, timeOutSeconds);
        } catch (TimeoutException e) {
            Assert.fail("Failed to find results from:" + url + ": in " + timeOutSeconds + " seconds");
        }
    }

    @Then("the w3c link checker reports compliance")
    public void theW3CLinkCheckerReportsCompliance() {
        Assert.assertTrue("Links on page :" + url + ": appear broken", w3cLinkChecker.fileValidates());
    }
}
