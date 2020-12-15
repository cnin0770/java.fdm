package com.fdm.BarrierOptionMonitor.Selenium;

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-template.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniuimTest {

    @Value("${webdriver.path}")
    private String driverPath;

    private WebDriver driver;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testTest() throws Exception {
        driver.get("http://localhost:" + port + "/");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("admin");
        WebElement submit = driver.findElement(By.cssSelector("button"));
        submit.click();
        Thread.sleep(2000);
//        driver.manage().window().fullscreen();

        driver.findElement(By.xpath("//*[@id=\"navbarDropdown_options\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[2]/div/a[2]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/table/tbody/tr[1]/td[4]/a")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div/button")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[3]/a")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/table/tbody/tr[3]/td[5]/button")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div/button")).click();
        Thread.sleep(8000);
        WebElement clients = driver.findElement(By.id("navbarDropdown_clients"));
        clients.click();
        Thread.sleep(2000);
        WebElement viewClients = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[1]/div/a"));
        viewClients.click();
        Thread.sleep(2000);
//        driver.manage().window().fullscreen();

        try {
            driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[2]/td[2]/button[2]")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.sleep(1000);
        }
        WebElement addClient = driver.findElement(By.className("float-right"));
        addClient.click();
        Thread.sleep(2000);
//        driver.manage().window().fullscreen();

        WebElement clientName = driver.findElement(By.id("name"));
        clientName.sendKeys("Tim");
        Thread.sleep(2000);
//        driver.manage().window().fullscreen();

        WebElement submitClient = driver.findElement(By.xpath("//*[@id=\"clientForm\"]/div[2]/input"));
        submitClient.click();
        Thread.sleep(2000);
//        driver.manage().window().fullscreen();

        WebElement viewClient = driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[2]/td[2]/button[3]"));
        viewClient.click();
        Thread.sleep(2000);
//        driver.manage().window().fullscreen();

        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[1]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/table/tbody/tr/td[1]/input")).sendKeys("Tim");
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/table/tbody/tr/td[2]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/table/tbody/tr/td[2]/input")).sendKeys("100");
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/table/tbody/tr/td[3]/button")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/table/tbody/tr/td[1]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/table/tbody/tr/td[1]/input")).sendKeys("USD");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/table/tbody/tr/td[3]/button")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/table/tbody/tr/td[4]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"balance\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"balance\"]")).sendKeys("50");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/table[2]/tbody/tr/td[2]/button")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[2]/button[1]")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Testing");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"clientForm\"]/div[2]/input")).submit();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[2]/td[2]/button[2]")).click();
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Chuan");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"clientForm\"]/div[2]/input")).submit();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbarDropdown_options\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[2]/div/a[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[14]/button")).click();
        Thread.sleep(1000);

        //Add Options, show validation
        //Add Knocked in Up Put
        driver.findElement(By.xpath("//*[@id=\"symbol_input\"]")).sendKeys("ANZ.AX");
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"premium\"]")).sendKeys("5");
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        Select american = new Select(driver.findElement(By.xpath("//*[@id=\"is_american\"]")));
        american.selectByValue("true");
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        Select put = new Select(driver.findElement(By.xpath("//*[@id=\"is_call\"]")));
        put.selectByValue("true");
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        Select knockIn = new Select(driver.findElement(By.xpath("//*[@id=\"is_knock_in\"]")));
        knockIn.selectByValue("true");
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"strike_price\"]")).sendKeys("14");
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys("2");
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        Select up = new Select(driver.findElement(By.xpath("//*[@id=\"barrier_direction\"]")));
        up.selectByValue("true");
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        Thread.sleep(2000);
        WebElement currentPrice = driver.findElement(By.xpath("//*[@id=\"options\"]/p[9]/input"));
        String price = currentPrice.getAttribute("value");
        price = price.replaceAll("^[\\s\\.\\d]+", "");
        DecimalFormat df = new DecimalFormat("#.00");
        price = price.replaceAll("[^\\d.]", "");
        price = df.format(Double.parseDouble(price) - 1);
        driver.findElement(By.xpath("//*[@id=\"barrier_level\"]")).sendKeys(price);
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"expiry_date\"]")).sendKeys("24082020");
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(1000);
        Select client = new Select(driver.findElement(By.xpath("//*[@id=\"client_selected\"]")));
        client.selectByIndex(1);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[15]/button")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"navbarDropdown_options\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[2]/div/a[1]")).click();
        Thread.sleep(2000);

        //Add Knocked in Down Call
        driver.findElement(By.id("symbol_input")).sendKeys("ANZ.AX");
        driver.findElement(By.xpath("//*[@id=\"premium\"]")).sendKeys("5");
        american = new Select(driver.findElement(By.xpath("//*[@id=\"is_american\"]")));
        american.selectByValue("true");
        put = new Select(driver.findElement(By.xpath("//*[@id=\"is_call\"]")));
        put.selectByValue("false");
        knockIn = new Select(driver.findElement(By.xpath("//*[@id=\"is_knock_in\"]")));
        knockIn.selectByValue("true");
        driver.findElement(By.xpath("//*[@id=\"strike_price\"]")).sendKeys("14");
        driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys("2");
        Select down = new Select(driver.findElement(By.xpath("//*[@id=\"barrier_direction\"]")));
        down.selectByValue("false");
        Thread.sleep(2000);
        currentPrice = driver.findElement(By.xpath("//*[@id=\"options\"]/p[9]/input"));
        price = currentPrice.getAttribute("value");
        price = price.replaceAll("^[\\s\\.\\d]+", "");
        price = price.replaceAll("[^\\d.]", "");
        price = df.format(Double.parseDouble(price) + 1);
        driver.findElement(By.xpath("//*[@id=\"barrier_level\"]")).sendKeys(price);
        driver.findElement(By.xpath("//*[@id=\"expiry_date\"]")).sendKeys("24082020");
        client = new Select(driver.findElement(By.xpath("//*[@id=\"client_selected\"]")));
        client.selectByIndex(1);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[14]/button")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"navbarDropdown_options\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[2]/div/a[1]")).click();
        Thread.sleep(2000);

        //Add Knocked Out Up Put
        driver.findElement(By.id("symbol_input")).sendKeys("ANZ.AX");
        driver.findElement(By.xpath("//*[@id=\"premium\"]")).sendKeys("5");
        american = new Select(driver.findElement(By.xpath("//*[@id=\"is_american\"]")));
        american.selectByValue("true");
        put = new Select(driver.findElement(By.xpath("//*[@id=\"is_call\"]")));
        put.selectByValue("true");
        knockIn = new Select(driver.findElement(By.xpath("//*[@id=\"is_knock_in\"]")));
        knockIn.selectByValue("false");
        driver.findElement(By.xpath("//*[@id=\"strike_price\"]")).sendKeys("14");
        driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys("2");
        up = new Select(driver.findElement(By.xpath("//*[@id=\"barrier_direction\"]")));
        up.selectByValue("true");
        Thread.sleep(2000);
        currentPrice = driver.findElement(By.xpath("//*[@id=\"options\"]/p[9]/input"));
        price = currentPrice.getAttribute("value");
        price = price.replaceAll("^[\\s\\.\\d]+", "");
        price = price.replaceAll("[^\\d.]", "");
        price = df.format(Double.parseDouble(price) - 1);
        driver.findElement(By.xpath("//*[@id=\"barrier_level\"]")).sendKeys(price);
        driver.findElement(By.xpath("//*[@id=\"expiry_date\"]")).sendKeys("24082020");
        client = new Select(driver.findElement(By.xpath("//*[@id=\"client_selected\"]")));
        client.selectByIndex(1);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[14]/button")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"navbarDropdown_options\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[2]/div/a[1]")).click();
        Thread.sleep(2000);

        //Add Knocked Out Down Call
        driver.findElement(By.id("symbol_input")).sendKeys("ANZ.AX");
        driver.findElement(By.xpath("//*[@id=\"premium\"]")).sendKeys("5");
        american = new Select(driver.findElement(By.xpath("//*[@id=\"is_american\"]")));
        american.selectByValue("true");
        put = new Select(driver.findElement(By.xpath("//*[@id=\"is_call\"]")));
        put.selectByValue("true");
        knockIn = new Select(driver.findElement(By.xpath("//*[@id=\"is_knock_in\"]")));
        knockIn.selectByValue("false");
        driver.findElement(By.xpath("//*[@id=\"strike_price\"]")).sendKeys("14");
        driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys("2");
        down = new Select(driver.findElement(By.xpath("//*[@id=\"barrier_direction\"]")));
        down.selectByValue("false");
        Thread.sleep(2000);
        currentPrice = driver.findElement(By.xpath("//*[@id=\"options\"]/p[9]/input"));
        price = currentPrice.getAttribute("value");
        price = price.replaceAll("^[\\s\\.\\d]+", "");
        price = price.replaceAll("[^\\d.]", "");
        price = df.format(Double.parseDouble(price) + 1);
        driver.findElement(By.xpath("//*[@id=\"barrier_level\"]")).sendKeys(price);
        driver.findElement(By.xpath("//*[@id=\"expiry_date\"]")).sendKeys("24082020");
        client = new Select(driver.findElement(By.xpath("//*[@id=\"client_selected\"]")));
        client.selectByIndex(1);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[14]/button")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"navbarDropdown_options\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[2]/div/a[1]")).click();
        Thread.sleep(2000);

        //Add Knocked in Up Euro Put
        driver.findElement(By.id("symbol_input")).sendKeys("RIO.AX");
        driver.findElement(By.xpath("//*[@id=\"premium\"]")).sendKeys("10");
        american = new Select(driver.findElement(By.xpath("//*[@id=\"is_american\"]")));
        american.selectByValue("false");
        put = new Select(driver.findElement(By.xpath("//*[@id=\"is_call\"]")));
        put.selectByValue("false");
        knockIn = new Select(driver.findElement(By.xpath("//*[@id=\"is_knock_in\"]")));
        knockIn.selectByValue("true");
        driver.findElement(By.xpath("//*[@id=\"strike_price\"]")).sendKeys("75");
        driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys("1");
        up = new Select(driver.findElement(By.xpath("//*[@id=\"barrier_direction\"]")));
        up.selectByValue("true");
        Thread.sleep(2000);
        currentPrice = driver.findElement(By.xpath("//*[@id=\"options\"]/p[9]/input"));
        price = currentPrice.getAttribute("value");
        price = price.replaceAll("^[\\s\\.\\d]+", "");
        price = price.replaceAll("[^\\d.]", "");
        price = df.format(Double.parseDouble(price) - 1);
        driver.findElement(By.xpath("//*[@id=\"barrier_level\"]")).sendKeys(price);
        driver.findElement(By.xpath("//*[@id=\"expiry_date\"]")).sendKeys("24082020");
        client = new Select(driver.findElement(By.xpath("//*[@id=\"client_selected\"]")));
        client.selectByIndex(1);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"options\"]/p[14]/button")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(6000);


        // Select Notifications
        driver.findElement(By.xpath("//*[@id=\"navbarDropdownMenuLink-5\"]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[2]/li/div/a[1]")).click();
        Thread.sleep(7000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/table/tbody/tr[8]/td[4]/a")).click();
        Thread.sleep(7000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div/button")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/table/tbody/tr[4]/td[13]/a")).click();
        Thread.sleep(7000);

        //View Clients
        driver.findElement(By.id("navbarDropdown_clients")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[1]/div/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[2]/td[2]/button[3]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/button")).click();

        //View Options Exercise again
        driver.findElement(By.xpath("//*[@id=\"navbarDropdown_options\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[2]/div/a[2]")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/table/tbody/tr[5]/td[13]/a")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("navbarDropdown_clients")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[1]/div/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[2]/td[2]/button[3]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/button")).click();
        Thread.sleep(2000);

        //View Stocks Rio Tinto
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[3]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/table/tbody/tr[3]/td[5]/button")).click();
        Thread.sleep(3500);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div/button")).click();
        Thread.sleep(2000);

        //Logout
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/form/button")).click();
        Thread.sleep(5000);

        //Loging as User
        driver.findElement(By.id("username")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("user");
        driver.findElement(By.cssSelector("button")).click();
        Thread.sleep(2000);

        //Select Notifications
        driver.findElement(By.xpath("//*[@id=\"navbarDropdownMenuLink-5\"]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[2]/li/div/a[1]")).click();
        Thread.sleep(7000);

        //View Clients
        driver.findElement(By.id("navbarDropdown_clients")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[1]/div/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[2]/td[2]/button[2]")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

        //Add Knocked in Up Euro Put
        //View Stocks Graphs- New graph and Old one
        //Notification- follow it
        //View Options- Graph
        //Exercise Put and Call
    }
}
