package testFramework.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;

public class W3cHtmlChecker {
    //    Context.defaultDriver.findElement(By.xpath("/html/body/ol/li[@class='error']/ancestor::ol")).getAttribute("outerHTML")
    private final By errorList = By.xpath("//li[@class='error']/ancestor::ol");

    /**
     * It is best to aim this directly at the single files that you create.
     * For example, Bootstrap's css invokes error messages (false negatives?) from this tester.
     *
     * @param urlOfHtmlFile - make it a single file.Scheme is not necessary
     */
    public W3cHtmlChecker(String urlOfHtmlFile, long timeOutSeconds) {
        String sut = "https://html5.validator.nu/?doc=";
        sut += urlOfHtmlFile;
        sut += "&parser=html";

        Context.defaultActor.getResource(sut);

        new WebDriverWait(Context.defaultDriver, timeOutSeconds).
                until(ExpectedConditions.presenceOfElementLocated(By.className("details"))
                );
    }

    /**
     * On both the success and failure pages, the first h3 tells you the result
     *
     * @return - whether it contains text that indicates success, or failure
     */
    public Boolean fileValidates() {
        String resultString;
        /*
        <p class="success">The document validates according to the specified schema(s).</p>
         */
        try {
            resultString = Context.defaultDriver.findElement(By.className("success")).getText();
            if (resultString.contains(" document is valid ")) {
                return true;
            }
        } catch (NoSuchElementException ignored) {
        }
        // make the default result to be failure
        return false;
    }

    public String getErrorList() {
        return Context.defaultDriver.findElement(errorList).getAttribute("outerHTML");
    }
}