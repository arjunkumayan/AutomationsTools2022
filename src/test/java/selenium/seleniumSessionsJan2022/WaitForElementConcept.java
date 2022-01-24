package selenium.seleniumSessionsJan2022;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitForElementConcept {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        By userName = By.name("username");
        By password = By.name("password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement userElement= wait.until(ExpectedConditions.presenceOfElementLocated(userName));
        userElement.sendKeys("hello");

    }
}
