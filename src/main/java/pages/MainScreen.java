package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;

public class MainScreen {

    private AppiumDriver driver;
    private WebDriverWait wait;
    private final By settingDoneButton = AppiumBy.id("com.info.weather.forecast:id/tv_button_done");
    private final By privacyPolicy = AppiumBy.id("com.info.weather.forecast:id/ll_got_it");
    private final By temperatureUnit = AppiumBy.id("com.info.weather.forecast:id/tv_current_temper_unit");
    private final By tvDateId = AppiumBy.id("com.info.weather.forecast:id/tv_date");


    public MainScreen(AppiumDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver , Duration.ofSeconds(10));
    }

    public void getMainScreen(){
        driver.findElement(settingDoneButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(privacyPolicy)).click();

    }

    public void assert12HoursFormat(){
        String tvDate = wait.until(ExpectedConditions.visibilityOfElementLocated(tvDateId)).getText();

        Assert.assertTrue(tvDate.contains("AM") || tvDate.contains("PM"));
    }

    public void assert24HoursFormat(){
        String tvDate = wait.until(ExpectedConditions.visibilityOfElementLocated(tvDateId)).getText();

        Assert.assertFalse(tvDate.contains("AM") || tvDate.contains("PM"));
    }


    public void assertTempUnitInCelsius(){
        String tempUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(temperatureUnit)).getText();

        Assert.assertEquals(tempUnit, "°C");
    }


    public void assertTempUnitInFahrenheit(){
        String tempUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(temperatureUnit)).getText();

        Assert.assertEquals(tempUnit, "°F");
    }


}
