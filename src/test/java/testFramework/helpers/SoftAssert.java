package testFramework.helpers;

public class SoftAssert {

    private boolean hasFailed = false;

    /**
     * To be called at interim assertions. Causes this instance to remember if there has been a failure
     *
     * @param isGood - any expression resulting in a boolean value
     * @param msg    - to be written into the log if the isGood == false
     */
    public void accumulate(boolean isGood, String msg) {
        if (!isGood) {
            hasFailed = true;
            ReportWriter.writeToHtmlReport(msg);
        }
    }

    /**
     * Get the cumulative state of this soft assertion. Can be uses in an assertion
     * eg Assert.AssertTrue(sa.allGood(), "it really should not have failed");
     *
     * @return - if there has been any failure signalled (by a setState) => true
     */
    public boolean allGood() { return !hasFailed; }
}
