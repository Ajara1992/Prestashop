package com.prestashop.tests.smoke_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;

public class AccountInfoScenarios {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
    }

    @Test(priority = 1)
    public void myAccount() {
        WebElement signInButton = driver.findElement(By.xpath("//a[@class='login']"));
        signInButton.click();
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("11aizhamal11@gmail.com");
        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys("196619941990a");
        WebElement button = driver.findElement(By.xpath("//button[@id='SubmitLogin'] /span\t"));
        button.click();
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("My account"));
        WebElement userName = driver.findElement(By.xpath("//a[@title='View my customer account']/span"));
        String name = userName.getText();
        Assert.assertEquals(name, "Aika Koch");
    }

    @Test(priority = 2)
    public void myPersonalInformation() {
        driver.findElement(By.xpath("//span[.='My personal information']")).click();
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Identity"));
        WebElement name = driver.findElement(By.id("firstname"));
        String nameStr = name.getAttribute("value");
        WebElement lastName = driver.findElement(By.id("lastname"));
        String lastNameStr = lastName.getAttribute("value");
        WebElement element = driver.findElement(By.xpath("//a[@title='View my customer account']/span"));
        String elementStr = element.getText();
        String NameLAstName = nameStr + " " + lastNameStr;
        Assert.assertEquals(NameLAstName, elementStr);
        System.out.println(NameLAstName + "==>" + elementStr);
        driver.findElement(By.xpath("//span[.='Save']")).click();
        WebElement errorMessage = driver.findElement(By.xpath("//li[.='The password you entered is incorrect.']"));
        Assert.assertEquals(errorMessage.getText(), "The password you entered is incorrect.");
        System.out.println(errorMessage.getText() + "==>" + "The password you entered is incorrect.");
        driver.findElement(By.xpath("(//a[@class='btn btn-default button button-small']/span)[2]")).click();
        Assert.assertTrue(driver.getTitle().contains("My account"));
    }
    @Test(priority = 3)
    public void myAdress() throws InterruptedException {
        driver.findElement(By.xpath("//span[.='My addresses']")).click();
        driver.findElement(By.xpath("//span[.='Add a new address']")).click();
        WebElement name=driver.findElement(By.id("firstname"));
        String nameStr=name.getAttribute("value");
        WebElement lastName=driver.findElement(By.id("lastname"));
        String lastNameStr=lastName.getAttribute("value");
        String fullName=nameStr+" "+lastNameStr;
        WebElement nameOnTop=driver.findElement(By.xpath("//span[.='Aika Koch']"));
        Assert.assertEquals(fullName, nameOnTop.getText());
        System.out.println(fullName+"==>"+nameOnTop.getText());
        Thread.sleep(2000);
        name.clear();
        driver.findElement(By.xpath("//button[@id='submitAddress']/span")).click();
        WebElement fullFirstName=driver.findElement(By.xpath("(//ol/li)[1]"));
        Assert.assertEquals(fullFirstName.getText(),"firstname is required.");
        System.out.println(fullFirstName.getText()+"==>"+"firstname is required.");
        System.out.println("yes");
           }
}
