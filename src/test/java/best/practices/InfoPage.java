package best.practices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InfoPage {
    //Private driver for a page object so that it's never accessible to our tests
    private WebDriver driver;
    //Private element locator that is never open to our tests
    private WebElement getFirstNameField(){
        return driver.findElement(By.id("first-name"));
    }
    private WebElement getLastNameField(){
        return driver.findElement(By.id("last-name"));
    }
    private WebElement getZipCodeField(){
        return driver.findElement(By.id("postal-code"));
    }
    //Public start checkout method, what the user does
    public void fillOutPersonalInformation(){
        getFirstNameField().sendKeys("firstName");
        getLastNameField().sendKeys("lastName");
        getZipCodeField().sendKeys("zipCode");
    }
}
