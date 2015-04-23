package ru.abbyy.lingvolive;

import java.io.File;
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

public class AddFilmTest extends ru.abbyy.lingvolive.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  @Test
  public void testAddFilmPositive() throws Exception {
	driver.get(baseUrl + "/php4dvd/");
	WebElement addMovieButton = driver.findElement(By.cssSelector("img[alt=\"Add movie\"]"));
	addMovieButton.click();
	
	WebDriverWait wait = new WebDriverWait(driver,50);
    WebElement imdbidField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("imdbid")));
	imdbidField.clear();
    imdbidField.sendKeys("1111");
    
    String filmTitle = "My Test Film";
    WebElement filmTitleField = driver.findElement(By.name("name"));
	filmTitleField.clear();
    filmTitleField.sendKeys(filmTitle);
    
    WebElement akaField = driver.findElement(By.name("aka"));
	akaField.clear();
    akaField.sendKeys("Selenium First Test");
    
    int year = 1989;
    String yearToStr = new Integer(year).toString();
    WebElement yearField = driver.findElement(By.name("year"));
	yearField.clear();
    yearField.sendKeys(yearToStr);
    
    WebElement durationField = driver.findElement(By.name("duration"));
	durationField.clear();
    durationField.sendKeys("120");
    
    WebElement ratingField = driver.findElement(By.name("rating"));
	ratingField.clear();
    ratingField.sendKeys("5");
    
    WebElement formatField = driver.findElement(By.id("formats"));
	formatField.clear();
    formatField.sendKeys("DVD");
    
    WebElement ownRadioButtonNo = driver.findElement(By.id("own_no"));
	ownRadioButtonNo.click();
    
    WebElement seenRadioButtonNo = driver.findElement(By.id("seen_no"));
	seenRadioButtonNo.click();
    
    WebElement loanedRadioButtonYes = driver.findElement(By.id("loaned_yes"));
	loanedRadioButtonYes.click();
    
    WebElement loanNameField = driver.findElement(By.name("loanname"));
	loanNameField.clear();
    loanNameField.sendKeys("John Doe");
    
    WebElement loanDateField = driver.findElement(By.name("loandate"));
	loanDateField.clear();
    loanDateField.sendKeys("2015-04-24");
    
    driver.findElement(By.id("cover")).sendKeys((new File("cover.jpg").getAbsolutePath()));
    
    WebElement trailerUrlField = driver.findElement(By.name("trailer"));
	trailerUrlField.clear();
    trailerUrlField.sendKeys("http://youtube.com");
    
    WebElement notesField = driver.findElement(By.name("notes"));
	notesField.clear();
    notesField.sendKeys("notes");
    
    WebElement taglinesField = driver.findElement(By.name("taglines"));
	taglinesField.clear();
    taglinesField.sendKeys("tag");
    
    WebElement plotOutLinesField = driver.findElement(By.name("plotoutline"));
	plotOutLinesField.clear();
    plotOutLinesField.sendKeys("plot_outline");
    
    WebElement plotsField = driver.findElement(By.name("plots"));
	plotsField.clear();
    plotsField.sendKeys("plots");
    
    WebElement languagesField = driver.findElement(By.id("text_languages_0"));
	languagesField.clear();
    languagesField.sendKeys("langs");
    
    WebElement subtitlesField = driver.findElement(By.name("subtitles"));
	subtitlesField.clear();
    subtitlesField.sendKeys("subtitles");
    
    WebElement audioField = driver.findElement(By.name("audio"));
	audioField.clear();
    audioField.sendKeys("audio");
    
    WebElement videoField = driver.findElement(By.name("video"));
	videoField.clear();
    videoField.sendKeys("video");
    
    WebElement countryField = driver.findElement(By.name("country"));
	countryField.clear();
    countryField.sendKeys("country");
    
    WebElement genresField = driver.findElement(By.name("genres"));
	genresField.clear();
    genresField.sendKeys("genres");
    
    WebElement directorField = driver.findElement(By.name("director"));
	directorField.clear();
    directorField.sendKeys("director");
    
    WebElement writerField = driver.findElement(By.name("writer"));
	writerField.clear();
    writerField.sendKeys("writer");
    
    WebElement producerField = driver.findElement(By.name("producer"));
	producerField.clear();
    producerField.sendKeys("producer");
    
    WebElement musicField = driver.findElement(By.name("music"));
	musicField.clear();
    musicField.sendKeys("music");
    
    WebElement castField = driver.findElement(By.name("cast"));
	castField.clear();
    castField.sendKeys("cast");
    
    WebElement saveFilmButton = driver.findElement(By.id("submit"));
	saveFilmButton.click();
    
	
	WebElement movie = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("movie")));
	WebElement titleAndYear = movie.findElement(By.tagName("h2"));
	assertTrue(titleAndYear.getText().equals(filmTitle + " (" + yearToStr + ")"));
	
	WebElement goHomeButton = driver.findElement(By.linkText("Home"));
	goHomeButton.click();
	WebElement movies = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("results")));
	assertTrue(isElementPresent(movies, (By.cssSelector("img[alt=\"" + filmTitle + "\"]"))));
  }
  
  
  
  @Test
  public void testAddFilmFromImdb() throws Exception {
	driver.get(baseUrl + "/php4dvd/");
	WebElement addMovieButton = driver.findElement(By.cssSelector("img[alt=\"Add movie\"]"));
	addMovieButton.click();
	WebDriverWait wait = new WebDriverWait(driver,100);
	
	WebElement imdbSearchField =  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("imdbsearch")));
	imdbSearchField.clear();
    imdbSearchField.sendKeys("123456");
    
    WebElement imdbSearchButton = driver.findElement(By.xpath("/html/body/div/div/div/section/div/form/table/tbody/tr[2]/td/input"));
	imdbSearchButton.click();
    
	
	WebElement searchedMovie = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Home and Away")));
    
	searchedMovie.click();
    
    WebElement saveFilmButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));
	saveFilmButton.click();
    
	WebElement movie = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("movie")));
	WebElement titleAndYear = movie.findElement(By.tagName("h2"));
	assertTrue(titleAndYear.getText().equals("Home and Away (1988)")); 
	
	WebElement goHomeButton = driver.findElement(By.linkText("Home"));
	goHomeButton.click();
  }

  @Test
  public void testAddFilmNoYear() throws Exception {
	driver.get(baseUrl + "/php4dvd/");
	WebElement addMovieButton = driver.findElement(By.cssSelector("img[alt=\"Add movie\"]"));
	addMovieButton.click();
	
	WebElement filmTitleField = driver.findElement(By.name("name"));
	filmTitleField.clear();
    filmTitleField.sendKeys("My Test Film");
    
    WebElement saveFilmButton = driver.findElement(By.id("submit"));
	saveFilmButton.click();
    
    WebElement errorLabel = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/form/table/tbody/tr[4]/td[2]/label"));
    assertTrue(errorLabel.isDisplayed());
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
