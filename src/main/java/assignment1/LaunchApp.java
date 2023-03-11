package assignment1;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		//Maximize the browser window.
		
		driver.manage().window().maximize();
		
		//Navigate to “http://qatechhub.com”.
		
		driver.get("http://qatechhub.com");
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		/*
		 * Write a method to print PASS if the title of the page matches with “QA
		 * Automation Tools Trainings and Tutorials | QA Tech Hub” else FAIL. (If you
		 * are familiar with TestNG or JUnit use assert statement like
		 * assert.assertequals(actual, expected) to give a verdict of the pass or fail
		 * status.
		 */
		
		String originalTitle= driver.getTitle();
		
		String expectedTitle="QA Automation Tools Trainings and Tutorials | QA Tech Hub";
		
		if(expectedTitle.equals(originalTitle))
		{
			Assert.assertTrue(true);
			System.out.println("Test case PASS");
		}
		else
		{
			System.out.println("Test case FAIL");
			Assert.fail();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Navigate to the Facebook page (https://www.facebook.com)
		
		driver.navigate().to("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Navigate back to the QA Tech Hub website.
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Print the URL of the current page.
		String pageURL= driver.getCurrentUrl();
		
		
		System.out.println("URL of the current page: "+pageURL);
		
		//Navigate forward.
		
		driver.navigate().forward();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Reload the page
		driver.navigate().refresh();
		
		//Close the Browser.
		driver.close();
		
		driver.quit();
		
		
		

	}

}
