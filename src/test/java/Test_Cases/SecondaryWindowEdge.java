package Test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SecondaryWindowEdge extends MainWindowEdge {
    String strItemName;
    String strItemPrice;
    @Test(priority = 4)
    public void selectProductOptions() {
        String mainWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
        for (int i = 0; i < 6; i++) {
            try {
                WebElement dropdowndriver = driver.findElement(By.id("x-msku__select-box-100" + i));
                Select dropdown = new Select(dropdowndriver);
                try {
                    dropdown.selectByIndex(1);
                } catch (Exception ee) {
                    dropdown.selectByIndex(2);
                }

            } catch (Exception e) {
            }
        }

    }

    @Test(priority = 5)
    public void productName() {
        WebElement name = driver.findElement(By.xpath("//*[@id=\"LeftSummaryPanel\"]/div[1]/div[1]/div/h1/span"));
        strItemName = name.getText();
        System.out.println("\n\nProduct Details");
        System.out.println("item Name : " + name.getText());

        WebElement price = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/form/div[2]/div/div[1]/div[1]/div/div[2]/div/span[1]/span"));
        strItemPrice = price.getText();
        System.out.println("item Price : " + price.getText());

        WebElement condition = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/form/div[1]/div[1]/div/div[2]/div[1]/div/span/span[1]/span"));
        System.out.println("item Condition : \n" + condition.getText());

        WebElement quantity = driver.findElement(By.id("qtyTextBox"));
        System.out.println("item Quantity : " + quantity.getAttribute("value"));
    }

    @Test(priority = 6)
    public void addCart() {
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/form/div[2]/div/div[1]/div[2]/ul/li[2]/div/a/span/span"));
        btn.click();
    }

    @Test(priority = 7)
    public void assertShoppingCart() {
        WebElement name = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div/ul/li/div[1]/div/div/div[1]/div/div[2]/div/h3/span/a/span/span/label"));
        System.out.println("********************\n\nCheck Assertions");
        System.out.println("Assert item Name : " + name.getText());
        Assert.assertEquals(strItemName, name.getText(), "names are not matching");

        WebElement price = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div/ul/li/div[1]/div/div/div[1]/div/div[3]/div/div[1]/div[2]/div/div/span/span/span"));
        System.out.println("Assert item Price : " + price.getText());
        Assert.assertEquals(strItemPrice, price.getText(), "Prices are not matching");

        try {
            WebElement qty = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div/ul/li/div/div/div/div[1]/div/div[3]/div/div[1]/div[1]/div/span/span/select"));
            Select option = new Select(qty);
            System.out.println("item Quantity: " + option.getFirstSelectedOption().getText());
            Assert.assertNotEquals(option.getFirstSelectedOption().getText(), "0", "Quantity is empty");

        } catch (Exception e) {
//            WebElement qty = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div/ul/li/div/div/div/div[1]/div/div[3]/div/div[1]/div[1]/div/span/span/select"));
//            Select option=new Select(qty);
//            //assert qty.equals("Qty 0"):"Quantity is empty";
//            System.out.println("item "+option.getFirstSelectedOption().getText());
        }

        try {
            WebElement subTotal = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[4]/div/div[2]/div[4]/div[2]/span/span/span"));
            System.out.println("sub total :" + subTotal.getText());
            Assert.assertEquals(strItemPrice, subTotal.getText(), "sub Total is not matching");
        } catch (Exception e) {
            WebElement subTotal = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[4]/div/div[2]/div[3]/div[2]/span/span/span"));
            Assert.assertEquals(strItemPrice, subTotal.getText(), "sub Total is not matching");
            System.out.println("sub total :" + subTotal.getText());
        }

        try {
            WebElement shippingCharge = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[4]/div/div[2]/div[2]/div[2]/span/span/span"));
            System.out.println("Shipping charge : " + shippingCharge.getText());
        } catch (Exception e) {
        }

    }
}
