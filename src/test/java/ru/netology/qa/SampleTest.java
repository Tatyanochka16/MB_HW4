package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
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

import ru.netology.qa.MobileObjects;

public class SampleTest {

    enum Platform {Android, iOS}

    ;
    Platform platform = Platform.Android;
    private AppiumDriver driver;
    private MobileObjects mobileObjects;
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
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        if (platform == Platform.Android) {
            desiredCapabilities.setCapability("platformName", "android");
            desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability("appium:appActivity", ".MainActivity");
            desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
            driver = new AndroidDriver(getUrl(), desiredCapabilities);
        } else {
            throw new IllegalArgumentException(String.format("Platform %s no supported", platform));
        }

        mobileObjects = new MobileObjects(driver);
    }

    @Test
    public void testChangeTextForEmpty() {
        if (platform == Platform.Android) {
            MobileElement initialString = mobileObjects.textString;
            mobileObjects.inputString.click();
            mobileObjects.inputString.sendKeys(TextToSet1);
            mobileObjects.buttonChange.click();
            Assertions.assertEquals(initialString.getText(), mobileObjects.textString.getText());
        } else {
            throw new IllegalArgumentException(String.format("Platform %s no supported", platform));
        }
    }

    @Test
    public void testChangeTextForActivity() {
        if (platform == Platform.Android) {
            mobileObjects.inputString.click();
            mobileObjects.inputString.sendKeys(TextToSet2);
            mobileObjects.buttonActivity.click();
            mobileObjects.textActivity.isDisplayed();
            Assertions.assertEquals(mobileObjects.textActivity.getText(), TextToSet2);
        } else {
            throw new IllegalArgumentException(String.format("Platform %s no supported", platform));
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}