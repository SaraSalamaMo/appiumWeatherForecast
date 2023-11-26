package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import java.time.Duration;
import java.util.Arrays;
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
    private final By timeFormatSelectedSetting = AppiumBy.id("com.info.weather.forecast:id/tv_timeformat_setting");

    private final By tempUnitDropDownId = AppiumBy.id("com.info.weather.forecast:id/iv_temp_dropdown");
    private final By tempUnitCelsius = AppiumBy.xpath("//android.widget.FrameLayout[@index='2']//android.widget.TextView[@index='0']");
    private final By tempSelectedSetting = AppiumBy.id("com.info.weather.forecast:id/tv_temp_setting");

    private final By navigationSetting = AppiumBy.id("com.info.weather.forecast:id/iv_bt_navigation_setting");
    private final By unitSettingId = AppiumBy.id("com.info.weather.forecast:id/ll_item_unit_setting");

    private final By hourlyInfoList = AppiumBy.id("com.info.weather.forecast:id/ll_hourly_info_list");
    private final By hoursList = AppiumBy.xpath("//android.widget.TextView[@resource-id='com.info.weather.forecast:id/tv_hour_item']");
    private final By rainList =  AppiumBy.xpath("//android.widget.TextView[@resource-id='com.info.weather.forecast:id/tv_rain_chance']");
    private final By humidityList = AppiumBy.xpath("//android.widget.TextView[@resource-id='com.info.weather.forecast:id/tv_humidity']");

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
        wait.until(ExpectedConditions.elementToBeClickable(timeFormatDropDownId)).click();
        driver.findElement(timeFormat24Hour).click();
        String timeFormat = driver.findElement(timeFormatSelectedSetting).getText();

        Assert.assertEquals(timeFormat, "24 Hour");

        driver.findElement(settingDoneButton).click();
    }

    public void changeTempUnitToCelsius(){
        wait.until(ExpectedConditions.elementToBeClickable(tempUnitDropDownId)).click();
        driver.findElement(tempUnitCelsius).click();
        String tempUnit = driver.findElement(tempSelectedSetting).getText();

        Assert.assertEquals(tempUnit, "C");

        driver.findElement(settingDoneButton).click();
    }


    public void rainAndHumidityValues(int hours){

        List<WebElement> times = driver.findElements(hoursList);
        List<WebElement> rains = driver.findElements(rainList);
        List<WebElement> humidities = driver.findElements(humidityList);

        String time ; String rain; String humidity;
        for (int i = 0; i <=hours-1 ; i++) {

            time = times.get(i).getText();
            rain = rains.get(i).getText();
            humidity = humidities.get(i + 1).getText();

            Assert.assertNotNull(time);
            System.out.println(time);

            Assert.assertNotNull(rain);
            System.out.println(rain);

            Assert.assertNotNull(humidity);
            System.out.println(humidity);
        }

        //TODO swipe to get the more elements
        WebElement hoursLInfoList = driver.findElement(hourlyInfoList);
        int x = hoursLInfoList.getLocation().getX();
        int y = hoursLInfoList.getLocation().getY();
        swipe(x,y);

    }

    //TODO need modifications
    public void swipe(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x+50, y));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));
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
