import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;

public class WebDriverMethodsQuiz {

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

    //https://the-internet.herohuapp.com/dropdown
    //opt 1 from dropdown, assert opt 1 select, assert opt 2 not select
    @Test
    public void dropDown(){
        driver.get("https://the-internet.herokuapp.com/dropdown");
        element = driver.findElement(By.id("dropdown"));
        element.click();
        WebElement option1 = element.findElement(By.cssSelector("option[value='1']"));
        WebElement option2 = element.findElement(By.cssSelector("option[value='2']"));
        option1.click();
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());

    }
    @Test
    public void hover(){
        driver.get("https://the-internet.herokuapp.com/hovers");
        element = driver.findElement(By.className("figure"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

        element = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5"));
        assertTrue("user1 should appear because we hovered over that image", element.isDisplayed());
    }
    @Test
    public void rightClick(){
        driver.get("https://the-internet.herokuapp.com/context_menu");
        element = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        driver.switchTo().alert().accept();
    }
    @Test
    public void keyPresses(){
        driver.navigate().to("https://the-internet.herokuapp.com/key_presses");
        element = driver.findElement(By.id("target"));
        element.click();

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_RIGHT).pause(1000).perform();
        element = driver.findElement(By.id("result"));
        assertEquals("Clicked right arrow key", "You entered: RIGHT", element.getText());
    }

    @Test
    public void getCSSValue(){
        driver.navigate().to("https://ultimateqa.com/simple-html-elements-for-automation/");
        element = driver.findElement(By.linkText("Clickable Icon"));
        String link = element.getAttribute("href");
        assertEquals("https://ultimateqa.com/link-success/", link);
        assertEquals("padding-box", element.getCssValue("background-origin"));
    }
}
