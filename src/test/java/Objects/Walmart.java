package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Walmart {
    WebDriver driver;
    public Walmart(WebDriver webDriver){
        this.driver=webDriver;

    }
    By fashion_link= By.xpath("//span[text()='Fashion']");
    By men_link = By.xpath("//span[text()='Men']");
    By shoe_link = By.xpath("//a[text()='Generic Name']");

    public void searchFashion()throws InterruptedException{
        driver.findElement(fashion_link).click();
        driver.findElement(men_link).click();
        driver.findElement(shoe_link).click();

        Thread.sleep(10000);
    }
}
