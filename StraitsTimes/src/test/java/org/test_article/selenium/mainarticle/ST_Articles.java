package org.test_article.selenium.mainarticle;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class ST_Articles {

    private String testUrl;
    private WebDriver driver;

    @Before
    public void prepare() {
        //setup chromedriver
    	 // Create a new instance of the Chrome driver
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");

        testUrl = "https://www.straitstimes.com";
        driver = new ChromeDriver();

        //maximize window
        driver.manage().window().maximize();

        driver.get(testUrl);
        //driver.close();
    }

    @Test
    public void testMainArticle() throws IOException {
    		
    	//Validate home page
        String expectedTitle = "The Straits Times";
        String actualTitle = "";
        actualTitle = driver.getTitle();
        //System.out.println(actualTitle);
        
        try {
        	Thread.sleep(2000);
        	if (actualTitle.contains(expectedTitle)){
                System.out.println("Straits Times Home Page loaded successfully");
            } else {
                System.out.println("Unable to load Straits Times Home Page");
                Assert.assertTrue(false);
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
           
        
      
        // Click on Login Link
        System.out.println("Click on Login");
        //WebElement login = driver.findElement(By.xpath("//*[contains(@name, 'click login')]"));
        WebElement login = driver.findElement(By.xpath("//*[@id=\"navbar\"]/div/div[2]/nav/div[2]/div/ul/li[1]/a"));  
        login.click();
        try {
        	Thread.sleep(2000);
        	if (driver.findElement(By.id("j_username")).isDisplayed()) {
            	
        		System.out.println("Login Page opened successfully");
        		
        		// Enter Username
        		WebElement Username = driver.findElement(By.id("j_username"));
        		Username.sendKeys("digitaltest10");
        		
        		// Enter Password
        		WebElement Password = driver.findElement(By.id("j_password"));
        		Password.sendKeys("Sphdigital1");
        		
        		WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button"));
        		loginButton.click();
        	} else {
        		System.out.println("Unable to open login page successfully");
        	}
        }
        catch (Exception e) {
        	e.printStackTrace();
    }
        
        //Verify user successfully logged in
        try {
        	Thread.sleep(2000);
        	if (driver.findElement(By.name("login-user-name")).isDisplayed()){
                System.out.println("User able to login successfully");
            } else {
                System.out.println("User login authentication failed");
                Assert.assertTrue(false);
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        //Verify if main article have media file
        WebElement mainArticle = driver.findElement(By.xpath("//*[@id=\"block-system-main\"]/div/div/div/div/div[4]/div/div/div/div[5]/div/div/div/div/div[3]")); 
        String articleClass = mainArticle.getAttribute("class");
        System.out.println(articleClass);
        try {
        	Thread.sleep(2000);
        	if (articleClass.contains("has_media")){
                System.out.println("Media File available in main article");
            } else {
                System.out.println("Unable to find media file in main article");
                Assert.assertTrue(false);
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }    
    
      
        //Verify main article header text content 
       WebElement clickArticle = driver.findElement(By.xpath("//*[@id=\"block-system-main\"]/div/div/div/div/div[4]/div/div/div/div[5]/div/div/div/div/a"));
       String articleTitle =  clickArticle.getAttribute("title");
       System.out.println(articleTitle);
       clickArticle.click();
       
       WebElement articleHeader = driver.findElement(By.xpath("//*[@id=\"block-system-main\"]/div/div[1]/div/header/h1"));
       String articleHeader_text =  articleHeader.getText();
       System.out.println(articleHeader_text);
       
       
       try {
       	Thread.sleep(2000);
       	if (articleTitle.contentEquals(articleHeader_text)){
               System.out.println("Expected header is matching with main article");
           } else {
               System.out.println("Expected header is not matching with article.");
               Assert.assertTrue(false);
               
           }
       }
       catch (Exception e) {
       	e.printStackTrace();
       }
    
    }

    @After
    public void teardown() throws IOException {
        driver.quit();
    }

}
