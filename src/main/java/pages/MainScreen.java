package pages;

import com.beust.ah.A;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;

public class MainScreen {

    private AppiumDriver driver;
    private WebDriverWait wait;
    private final By settingDoneButton = AppiumBy.id("com.info.weather.forecast:id/tv_button_done");
    private final By privacyPolicy = AppiumBy.id("com.info.weather.forecast:id/ll_got_it");
    private final By temperatureUnit = AppiumBy.id("com.info.weather.forecast:id/tv_current_temper_unit");
    private final By tvDateId = AppiumBy.id("com.info.weather.forecast:id/tv_date");
    private final By timeFormatDropDownId = AppiumBy.id("com.info.weather.forecast:id/iv_timeformat_dropdown");
    private final By timeFormat24Hour = AppiumBy.xpath("//android.widget.FrameLayout[@index='2']//android.widget.TextView[@index='1']");
    private final By timeFormatSetting = AppiumBy.id("com.info.weather.forecast:id/tv_timeformat_setting");
    private final By navigationSetting = AppiumBy.id("com.info.weather.forecast:id/iv_bt_navigation_setting");
    private final By unitSettingId = AppiumBy.id("com.info.weather.forecast:id/ll_item_unit_setting");


    public MainScreen(AppiumDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver , Duration.ofSeconds(10));
    }

    public void getMainScreen(){
        driver.findElement(settingDoneButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(privacyPolicy)).click();

    }

    public void openNavigationSettings(){
        driver.findElement(navigationSetting).click();
    }

    public void selectUnitSettings(){
        driver.findElement(unitSettingId).click();
    }


    public void changeTimeFormatTo24hours(){
        driver.findElement(timeFormatDropDownId).click();
        driver.findElement(timeFormat24Hour).click();
        String timeFormat = driver.findElement(timeFormatSetting).getText();

        Assert.assertEquals(timeFormat, "24 Hour");

        driver.findElement(settingDoneButton).click();
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
