package com.kodilla.ebooklibrary;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HappyPathTests {
    WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/login");
    }
    @Test
    public void happyPath() {
        //ekran logowania poprawne dane

        WebElement rightLogin = driver.findElement(By.name("login"));
        rightLogin.sendKeys(""); //login on demand

        WebElement rightPassword = driver.findElement(By.name("password"));
        rightPassword.sendKeys(""); //pass on demand
        rightPassword.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titles")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-title-button")));

        //funkcjonalność dodawania tytułu „Add new”
        WebElement addNewTitle = driver.findElement(By.id("add-title-button"));
        addNewTitle.click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("f//*[@id=\"titles\"]/div/div/form")));
        WebElement addNewTitleField = driver.findElement(By.name("title"));
        addNewTitleField.sendKeys("Diamonds Are Forever");

        WebElement addNewAuthorField = driver.findElement(By.name("author"));
        addNewAuthorField.sendKeys("Ian Fleming");

        WebElement addNewYearField = driver.findElement(By.name("year"));
        addNewYearField.sendKeys("1956");

        WebElement addNewSubmitButton = driver.findElement(By.name("submit-button"));
        addNewSubmitButton.click();

        //usuwanie ostatnio dodanego tytułu
        WebElement removeLastAddedTitle = driver.findElement(By.xpath("(//BUTTON[@id='add-title-button']/..//BUTTON[@class='remove-btn btn--small btn btn--error'][text()='Remove'])[5]"));
        removeLastAddedTitle.click();

        //funkcjonalność wejścia w listę egzemplarzy („SHOW COPIES”)
        WebElement showCopies = driver.findElement(By.xpath("(//H2[@class='sub-title flex-grow--1 margin-right--1'][text()='Titles catalog']/..//BUTTON[@class='show-copies-btn btn--small btn btn--primary'][text()='Show copies'])[1]"));
        showCopies.click();

        //funkcjonalność zobaczenia historii wypozyczeń („SHOW HISTORY”)
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title-copies")));
        WebElement showHistory = driver.findElement(By.cssSelector("#item-41715 > div.items-list__item__actions.list__item__actions > a > button"));
        showHistory.click();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
