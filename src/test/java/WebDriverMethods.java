import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebDriverMethods {

    WebDriver driver;
    WebElement element;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void windowsFrames(){
        driver.navigate().to("https://the-internet.herokuapp.com/windows");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://the-internet.herokuapp.com/windows/new');");

        String originalWindow = driver.getWindowHandle();
        Set handles = driver.getWindowHandles();
        handles.remove(originalWindow);

        String nextWindow = String.valueOf(handles.iterator().next());

        driver.switchTo().window(nextWindow);
        assertEquals("New Window", driver.getTitle());

        driver.close();
        driver.switchTo().window(originalWindow);
        assertEquals("The Internet", driver.getTitle());

    }

    @Test
    public void frames(){
        driver.navigate().to("https://the-internet.herokuapp.com/nested_frames");

        WebElement defaultFrame = driver.findElement(By.name("frame-top"));
        driver.switchTo().frame(1);

        assertEquals("BOTTOM", driver.findElement(By.tagName("body")).getText());

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");

        assertEquals("LEFT", driver.findElement(By.tagName("body")).getText());

        driver.switchTo().defaultContent();

        assertTrue(driver.findElement(By.name("frame-top")).getSize().height > 0);
    }

    @Test
    public void alerts(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//*[contains(normalize-space(text()), 'Click for JS Alert')]")).click();
        driver.switchTo().alert().dismiss();

        driver.findElement(By.xpath("//*[contains(normalize-space(text()), 'Click for JS Confirm')]")).click();
        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("//*[contains(normalize-space(text()), 'Click for JS Prompt')]")).click();
        Alert inputAlert = driver.switchTo().alert();
        String text = inputAlert.getText();
        inputAlert.sendKeys("Look Mom! I can automate alerts :)");
        inputAlert.accept();



    }
}
