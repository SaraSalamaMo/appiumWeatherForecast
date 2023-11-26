package tests;

import org.testng.annotations.Test;

public class testScenarios extends baseTest{

    @Test(priority = 1)
    public void getMainScreenWith12HoursFormatAndFahrenheitUnit(){
        mainScreen.getMainScreen();
        mainScreen.assertTempUnitInFahrenheit();
        mainScreen.assert12HoursFormat();
    }

    @Test(priority = 2)
    public void changeTimeFormatTo24Hours(){
        mainScreen.openNavigationSettings();
        mainScreen.selectUnitSettings();
        mainScreen.changeTimeFormatTo24hours();
        mainScreen.assert24HoursFormat();
    }

    @Test(priority = 3)
    public void changeTemperatureUnitToCelsius(){
        mainScreen.openNavigationSettings();
        mainScreen.selectUnitSettings();
        mainScreen.changeTempUnitToCelsius();
        mainScreen.assertTempUnitInCelsius();
    }
}
