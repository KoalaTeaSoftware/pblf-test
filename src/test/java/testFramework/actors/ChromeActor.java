package testFramework.actors;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import testFramework.Context;

import java.util.concurrent.TimeUnit;

public class ChromeActor extends Actor {
    @Override
    public void createDriver() {
        System.out.println("[info] Creating a Web Driver for Chrome");
        ChromeOptions options = new ChromeOptions();
        /*
        If you leave the page load strategy NORMAL, then you can get lots of errors like this
        [1584601749.116][SEVERE]: Timed out receiving message from renderer: 0.100
        (which aren't actually severe)
        If you set it to NONE (as in the following line),
        1) You don't get the errors
        2) the page's secondary stuff does not load before Selenium lets you go on
           so you will want to implement your own 'wait for load to complete' on the DriverManager's getPage()
        */
        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        // ChromeDriver is just AWFUL because every version, or two, it breaks unless you pass cryptic arguments
        // no sandbox tends to cure the random 'can't find open windows' failure and must be the 1st item
        // the others do not seem to be definitely needed, but are no harm either (so far as I can tell)
        options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
        options.addArguments("enable-features=NetworkServiceInProcess");
        options.addArguments("--disable-extensions");

        try {
            String windowSize = Context.testConfiguration.getProperty("windowSize");
            if (windowSize.length() > 0) {
                if (windowSize.equalsIgnoreCase("maximise"))
                    options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                else
                    options.addArguments("--window-size=" + Context.testConfiguration.getProperty("windowSize"));
            }
        } catch (NoSuchFieldException ignore) {}

        if (!System.getProperty("os.name").equalsIgnoreCase("Windows 10")) {
            // We are not on win 10, so we will be running as a GitHub Action
            // so ignore the configuration and make it headless
            options.addArguments("--headless");
        } else {
            try {
                if (Context.testConfiguration.getProperty("headless").equalsIgnoreCase("true"))
                    options.addArguments("--headless"); // only if you are ACTUALLY running headless
            } catch (NoSuchFieldException ignore) { }
        }

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Context.implicitWait, TimeUnit.SECONDS);
    }

    @Override
    protected void startService() {
        /*
        This is using the process for when you use the
         */
        WebDriverManager.chromedriver().setup();
    }

    /**
     * This is the process that you would follow if you were not using the DriverManager that is included in this
     * POM so that the thing will work easily as a GitHub Action
     * So, you would replace "WebDriverManager.chromedriver().setup();" with the following code
     * And declare a service ths, so it can be lazily instantiated
     * private ChromeDriverService service = null;
     */
    //
    //    if (null == service) {
    //        System.out.println("[info] Creating a Driver Service for Chrome");
    //        // i.e. Lazy Instantiation of the Service
    //        try {
    //            String path = Context.testConfiguration.getProperty("chromeDriverPath");
    //            //noinspection SpellCheckingInspection - webdriver is good
    //            System.setProperty("webdriver.chrome.driver", path);
    //            service = new ChromeDriverService.Builder()
    //                    .usingDriverExecutable(new File(path))
    //                    .usingAnyFreePort()
    //                    .build();
    //            service.start();
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            Assert.fail();
    //        }
    //    }
}
