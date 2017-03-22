package com.excilys.computerdatabase;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.FirefoxDriverManager;


/**
 * @author Guillon Julien
 *
 * 22 mars 2017
 */

public class DashboardTest {
  private WebDriver driver;
  private String baseUrl;

  @BeforeClass
  public static void setupClass() {
    FirefoxDriverManager.getInstance().setup();
  }
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/cdb";
  }

  @Test
  public void testDashboard() throws Exception {
    driver.get(baseUrl + "/computerdatabase");
    driver.findElement(By.linkText("»")).click();
    driver.findElement(By.linkText("3")).click();
    driver.findElement(By.linkText("«")).click();
    driver.findElement(By.id("searchbox")).clear();
    driver.findElement(By.id("searchbox")).sendKeys("mac");
    driver.findElement(By.id("searchsubmit")).click();
    driver.findElement(By.id("addComputer")).click();
    Thread.sleep(100);
    driver.findElement(By.id("homePage")).click();
    driver.findElement(By.linkText("100")).click();
    Thread.sleep(100);
    driver.findElement(By.linkText("50")).click();
    Thread.sleep(100);
    driver.findElement(By.linkText("10")).click();
    driver.findElement(By.linkText("Edit")).click();
    driver.findElement(By.id("deleteSelected")).click();
    driver.switchTo().alert().dismiss();
    driver.findElement(By.linkText("View")).click();

  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}