package ru.netology.qa;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileSelector;
import io.appium.java_client.android.AndroidDriver;
//import org.graalvm.compiler.lir.LIRInstruction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class SampleTest {

    private AndroidDriver driver;
    private String TextToSet1 = "   ";
    private String TextToSet2 = "New Text";

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", ".MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
    }

    @Test
    public void testChangeTextForEmpty() {
        var InitialText = driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.click();
        el1.sendKeys(TextToSet1);
        MobileElement el2 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        Assertions.assertEquals(InitialText.getText(), el3.getText());
    }

    @Test
    public void testChangeTextForActivity() {

        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.click();
        el1.sendKeys(TextToSet2);
        MobileElement el2 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/text"));
        el3.isDisplayed();
        Assertions.assertEquals(el3.getText(), TextToSet2);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}