package Test_Cases;

import Objects.GoogleSearch;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestGoogle
{
    WebDriver driver;
    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @Test
    public void SearchOperation(){
        GoogleSearch page=new GoogleSearch(driver);
       try {
           page.searchGoogle("facebook");
       }catch (InterruptedException e){
       }
    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
