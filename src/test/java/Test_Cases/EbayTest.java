package Test_Cases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EbayTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.ebay.com/");
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

        WebElement firstSearchResult = driver.findElement(By.xpath("//a//div//span[text()='Apple iPhone X A1901 - 64GB | 256GB - GSM Unlocked AT&T T-Mobile Metro Cricket']"));
        firstSearchResult.click();
    }

//    @Test(priority = 4)
//    public void selectProductOptions() {
//        String mainWindow = driver.getWindowHandle();
//        for (String windowHandle : driver.getWindowHandles()) {
//            if (!windowHandle.equals(mainWindow)) {
//                driver.switchTo().window(windowHandle);
//            }
//        }
//
//        WebElement conditionValue = driver.findElement(By.xpath("//div[contains(@class,'condition')]//span"));
//        String condition = conditionValue.getText();
//        System.out.println("Condition: " + condition);
//
//        WebElement quantityInput = driver.findElement(By.id("qtyTextBox"));
//        String quantity = quantityInput.getAttribute("value");
//        System.out.println("Quantity: " + quantity);
//
//        WebElement addToCartButton = driver.findElement(By.id("isCartBtn_btn"));
//        addToCartButton.click();
//    }

}

