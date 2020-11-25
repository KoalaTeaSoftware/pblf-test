package testFramework.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import testFramework.helpers.ReportWriter;
import testFramework.helpers.ResourceLocator;
import testFramework.objects.W3cHtmlChecker;

public class HtmlSyntaxCheckSteps {
    final long timeOutSeconds = 40;
    private W3cHtmlChecker w3cHtmlValidator = null;
    private String url;

    @Given("the w3C HTML tester reviews the file {string}")
    public void theW3CHTMLTesterReviewsTheFile(String urlOfFile) {
        url = ResourceLocator.interpretURL(urlOfFile);
        try {
            w3cHtmlValidator = new W3cHtmlChecker(url, timeOutSeconds);
        } catch (TimeoutException e) {
            Assert.fail("Failed to find results from:" + url + ": in " + timeOutSeconds + " " +
                    "seconds");
        }

    }

    @Then("the w3c HTML tester reports compliance")
    public void theW3CHTMLTesterReportsCompliance() {
        if (!w3cHtmlValidator.fileValidates()) {
            ReportWriter.writeToHtmlReport(w3cHtmlValidator.getErrorList());
            Assert.fail(url + ": (unescaped) should be syntactically correct");
        }
    }
}
