package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MobileObjects {

    @FindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    MobileElement textString;

    @FindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    MobileElement inputString;

    @FindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    MobileElement buttonChange;

    @FindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    MobileElement buttonActivity;

    @FindBy(id = "ru.netology.testing.uiautomator:id/text")
    MobileElement textActivity;

    private AppiumDriver driver;
    public MobileObjects(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

}
