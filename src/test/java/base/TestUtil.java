package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtil extends DataProviders {
    public WebDriver driver;
    private String testURL, browser;
    private int implicitWait;

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @BeforeMethod
    public void setupDriverAndOpenTestAddress(){
        readConfig("src/test/resources/config.properties");
        setupDriver();
        driver.get(testURL);
    }

    private void readConfig(String fullPathToConfigFile){
        try{
            FileInputStream fileInputStream = new FileInputStream(fullPathToConfigFile);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            testURL = properties.getProperty("URL");
            browser = properties.getProperty("browser");
            implicitWait = Integer.parseInt(properties.getProperty("implicitWait"));
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private void setupDriver(){
        switch (browser){
            case "safari":
                driver = setupSafariDriver();
                break;
            case "firefox":
                driver = setupFireFoxDriver();
                break;
            default:
                driver = setupChromeDriver();
        }
    }

    private WebDriver setupChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private WebDriver setupFireFoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private WebDriver setupSafariDriver(){
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }
    //web hook trigger build45
}
