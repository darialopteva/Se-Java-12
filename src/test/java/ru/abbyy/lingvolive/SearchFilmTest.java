package ru.abbyy.lingvolive;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchFilmTest extends ru.abbyy.lingvolive.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void testSearch() throws Exception {
    driver.get(baseUrl + "/php4dvd/");
    List<WebElement> films = driver.findElements(By.className("movie_box"));
    String filmTitle = films.get(0).findElement(By.className("title")).getText();
    String filmId = films.get(0).getAttribute("id");
    WebElement searchField = driver.findElement(By.id("q"));
	searchField.clear();
    searchField.sendKeys(filmTitle + Keys.RETURN);
    assertTrue(isElementPresent(By.id(filmId)));
  }

  @Test
  public void testSearchNegative() throws Exception {
    driver.get(baseUrl + "/php4dvd/");
    List<WebElement> films = driver.findElements(By.className("movie_box"));
    WebElement searchField = driver.findElement(By.id("q"));
	searchField.clear();
    searchField.sendKeys("rainbow" + Keys.RETURN);
    Thread.sleep(500);
    List<WebElement> newFilms = driver.findElements(By.className("movie_box"));
    assertTrue(newFilms.isEmpty());
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
