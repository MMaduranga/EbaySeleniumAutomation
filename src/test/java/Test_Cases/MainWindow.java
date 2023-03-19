package Test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainWindow {

   static WebDriver driver;



    @BeforeTest
    @Parameters({"url","browser"})
    public void setup(String url,String browser) {
       // if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
//        }
//        if(browser.equals("edge")){
//            System.setProperty("webdriver.chrome.driver","resources/msedgedriver.exe");
//            EdgeOptions options=new EdgeOptions();
//            options.addArguments("--remote-allow-origins=*");
//            driver = new EdgeDriver(options);
//        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Test(priority = 1)
    public void navigateToEbay() {

        String expectedUrl = "https://www.ebay.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Page URL is not matching");
    }

    @Test(priority = 2)
    public void searchForMobilePhone() {
        WebElement categoryDropdown = driver.findElement(By.id("gh-cat"));
        categoryDropdown.click();
        WebElement cellPhonesCategory = driver.findElement(By.xpath("//option[text()='Cell Phones & Accessories']"));
        cellPhonesCategory.click();

        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        searchBox.sendKeys("Mobile phone");

        WebElement searchButton = driver.findElement(By.id("gh-btn"));
        searchButton.click();
    }

    @Test(priority = 3)
    public void filterByBrand() {
        WebElement appleBrandCheckbox = driver.findElement(By.xpath("//input[@aria-label='Apple']"));
        appleBrandCheckbox.click();

        WebElement firstSearchResult = driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li[2]/div/div[2]/a/div/span"));
        firstSearchResult.click();


    }

    @AfterTest
    public void afterTest(){
       driver.quit();
    }

}

