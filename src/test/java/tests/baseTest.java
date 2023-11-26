package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pages.MainScreen;
import java.net.MalformedURLException;
import java.net.URL;

public class baseTest {

    public AppiumDriver driver;
    public MainScreen mainScreen;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        URL url = new URL("http://localhost:4723/wd/hub") ;
        String  path = System.getProperty("user.dir")+"/apps/WeatherForecast.apk";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:automationName","UiAutomator2");
        caps.setCapability("appium:deviceName","Android Emulator");
        caps.setCapability("appium:platformVersion", "12");
        caps.setCapability("appium:app", path);
        caps.setCapability("autoGrantPermissions", "true");

        driver = new AndroidDriver(url, caps);
        mainScreen = new MainScreen(driver);

    }
}
