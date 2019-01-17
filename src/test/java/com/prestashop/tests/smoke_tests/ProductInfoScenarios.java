package com.prestashop.tests.smoke_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ProductInfoScenarios {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
    }
    @Test(priority=1)
    public void productNameAndPrice(){
    WebElement product=driver.findElement(By.linkText("Faded Short Sleeve T-shirts"));
    String str=product.getText();
    WebElement price=driver.findElement(By.xpath("(//span[@class='price product-price'])[2]"));
    String price1=price.getText();

       product.click();
       WebElement product1=driver.findElement(By.xpath("//div/h1"));
       String str2=product1.getText();
       WebElement price2=driver.findElement(By.xpath("//span[@id='our_price_display']"));
       String price3=price2.getText();
                      Assert.assertEquals(str,str2,"verifying product's title");
                      Assert.assertTrue(price1.equals(price3));

    }
    @Test(priority=2)
    public void defaultValues() throws InterruptedException {
        Thread.sleep(2000);
        WebElement quantity=driver.findElement(By.xpath("//p[@id='quantity_wanted_p']/input"));
      String str=quantity.getAttribute("value");
      Assert.assertEquals(str,"1");
      WebElement size=driver.findElement(By.id("group_1"));
      Select size1=new Select(size);
       String strr=size1.getFirstSelectedOption().getText();
       Assert.assertEquals(strr,"S");
        List<WebElement> options=size1.getOptions();
        for(int i=0;i<options.size();i++){
          Assert.assertTrue(options.contains(options.get(i)));
        }
    }
    @Test(priority=3)
    public void addToCart() throws InterruptedException {
        driver.findElement(By.xpath("//button[@name='Submit']/span")).click();
        Thread.sleep(2000);
        WebElement message=driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']//h2"));
        String str=message.getText();
        Assert.assertEquals(str,"Product successfully added to your shopping cart");
        WebElement quantity=driver.findElement(By.id("layer_cart_product_quantity"));
        String quantityStr=quantity.getText();
        Assert.assertEquals(quantityStr,"1");
        WebElement size=driver.findElement(By.id("layer_cart_product_attributes"));
        String size1=size.getText();
        Assert.assertTrue(size1.contains("S"));
        WebElement price=driver.findElement(By.id("layer_cart_product_price"));
        String priceceText=price.getText();
        WebElement name=driver.findElement(By.id("layer_cart_product_title"));
        String nameText=name.getText();
        driver.navigate().back();
        WebElement product=driver.findElement(By.linkText("Faded Short Sleeve T-shirts"));
        String strrr=product.getText();
        WebElement priceeee=driver.findElement(By.xpath("(//span[@class='price product-price'])[2]"));
        String price1=priceeee.getText();
        Assert.assertEquals(priceceText,price1);
        System.out.println(priceceText+"=="+price1);
        Assert.assertEquals(nameText,strrr);
        System.out.println(nameText+"=="+strrr);
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
