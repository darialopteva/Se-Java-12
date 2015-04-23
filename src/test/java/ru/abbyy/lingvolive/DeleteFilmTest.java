package ru.abbyy.lingvolive;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DeleteFilmTest extends ru.abbyy.lingvolive.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void testDeleteFilmPositive() throws Exception {
    driver.get(baseUrl + "/php4dvd/");
    List<WebElement> films = driver.findElements(By.className("movie_box"));
    String filmId = films.get(0).getAttribute("id");
    films.get(0).click();
    WebDriverWait wait = new WebDriverWait(driver,50);
    WebElement deleteButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[alt=\"Remove\"]")));
    deleteButton.click();
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to remove this[\\s\\S]$"));
    Thread.sleep(100);
    List<WebElement> newFilms = driver.findElements(By.className("movie_box"));
    for (WebElement element : newFilms) {
		assertNotEquals(element.getAttribute("id"), filmId);
	}
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
