package locating.elemets;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class LocatingElementsTest {
    WebDriver driver;
    @Before
    public void setUp(){
        //Telling the system where to find chrome driver.
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        //1.Instantiate the driver
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
        //7.Quit the driver
        driver.quit();
    }

    @Test
    public void elementsQuiz1(){
        //2.Navigate to the URL
        driver.get("https://www.saucedemo.com/");
        //3.Find the element //4. Check the state
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("user-name"))
        );
        //5. Take action //6. Record the result
        assertTrue(element.isDisplayed());

    }
    @Test
    public void typesOfLocators(){
        //2.Navigate to the URL
        driver.get("https://www.saucedemo.com/");
        //3.Find the element //4. Check the state
        WebElement element;
        //ID
        element = driver.findElement(By.id("user-name"));
        //Name
        element = driver.findElement(By.name("user-name"));
        //Class name
        //driver.findElement(By.className("input_error form_input"));
        //Tag name
        driver.findElement(By.tagName("input"));
        //CSS selector
        driver.findElement(By.cssSelector("#user-name"));
        //Xpath
        driver.findElement(By.xpath("//*[@id=\"user-name\"]"));


        //2.Navigate to the URL
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation/");
        //3.Find the element //4. Check the state
        WebElement element2;
        //Link text
        driver.findElement(By.linkText("Click this link"));
        //Partial link text
        driver.findElement(By.partialLinkText("this link"));
        //Class name
        driver.findElement(By.className("et_pb_blurb_description"));

    }

    @Test
    public void locatorExam()
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //navigate to url
        driver.get("https://www.saucedemo.com/");

        //use css locators
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        //use css.xpath
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();

        //use best locator
        driver.findElement(By.id("first-name")).sendKeys("first name");
        driver.findElement(By.id("last-name")).sendKeys("last name");
        driver.findElement(By.id("postal-code")).sendKeys("zip");
        driver.findElement(By.id("continue")).click();

        //use link text
        driver.findElement(By.name("finish")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("#checkout_complete_container")).isDisplayed());


    }
}
