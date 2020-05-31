package StepDefs;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Cuke {
//    ConfigFileReader sutProperties = new ConfigFileReader("src/sut/resources/sut.properties");
//    ConfigFileReader frameworkProperties = new ConfigFileReader("src/test/java/framework/framework.properties");

    @Given("^I say \"([^\"]*)\"$")
    public void iSay(String arg0) throws InterruptedException {

        System.out.println(arg0);

        // Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();

        //Launch the Online Store Website
        driver.get("http://stage.middleearthhub.com");

        // Print a Log In message to the screen
        System.out.println("Successfully opened the website");

        //Wait for 5 Sec
        Thread.sleep(5000);

        // Close the driver
        driver.quit();
    }
}
