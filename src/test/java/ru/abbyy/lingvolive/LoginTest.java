package ru.abbyy.lingvolive;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import ru.abbyy.lingvolive.util.PropertyLoader;

public class LoginTest extends ru.abbyy.lingvolive.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void testLoginToAplication() throws Exception {
    driver.get(baseUrl + "/php4dvd/");
	String siteUsername = PropertyLoader.loadProperty("site.username");
	String sitePassword = PropertyLoader.loadProperty("site.password");
    WebElement usernameField = driver.findElement(By.id("username"));
	usernameField.clear();
    usernameField.sendKeys(siteUsername);
    WebElement passwordField = driver.findElement(By.name("password"));
	passwordField.clear();
    passwordField.sendKeys(sitePassword);
    driver.findElement(By.name("submit")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
