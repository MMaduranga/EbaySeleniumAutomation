package Test_Cases;

import Objects.GoogleSearch;
import Objects.Walmart;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestWalmart {

    WebDriver driver;
    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.walmart.com/");
    }

    @Test(priority = 1)
    public void navigateToWalmart() {
        String expectedUrl = "https://www.walmart.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Incorrect page is opened");
    }
    @Test(priority = 2)
    public void SearchOperation(){
        Walmart page=new Walmart(driver);
        try {
            page.searchFashion();
        }catch (InterruptedException e){
        }
    }


//    @AfterTest
//    public void afterTest(){
//        driver.quit();
//    }
}
