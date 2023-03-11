package assignment2;

/*Facebook Sign up:
Scenario:
Open a Chrome browser.
Navigate to “http://www.fb.com”
Verify that the page is redirected to “http://www.facebook.com”, by getting the current URL. (use if-else condition to verify this condition or use Assert.assertequals() in case you are familiar with TestNG or JUnit)
Verify that there is a “Create an account” section on the page.
Fill in the text boxes: First Name, Surname, Mobile Number or email address, “Re-enter mobile number”, new password.
Update the date of birth in the drop-down.
Select gender.
Click on “Create an account”.
Verify that the account is created successfully.*/

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateFBAccount {
	
	WebDriver driver;
	
	String fbURL="http://www.fb.com";
	

	@BeforeTest
	public void invokeFacebook() {
		WebDriverManager.chromedriver().setup();
		
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		//Navigate to “http://www.fb.com
		
		driver.get(fbURL);
		
		/*
		 * Verify that the page is redirected to “http://www.facebook.com”, by getting
		 * the current URL. (use if-else condition to verify this condition or use
		 * Assert.assertequals() in case you are familiar with TestNG or JUnit)
		 */	
		
		String currentURL=driver.getCurrentUrl();
		
		Assert.assertEquals(currentURL, "https://www.facebook.com/", "No redirection happened");
		
		
		
	}
	
	@Test
	public void facebookSignup() throws InterruptedException {
		//Verify that there is a “Create an account” section on the page.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		List<WebElement> newEle=driver.findElements(By.linkText("Create new account"));
		if(newEle.size()!=0)
			System.out.println("Create new account button is available");
		else
			System.out.println("Create new account is not available");
		//Fill in the text boxes: First Name, Surname, Mobile Number or email address, “Re-enter mobile number”, new password.
		newEle.get(0).click();
		
		driver.findElement(By.name("firstname")).sendKeys("test");
		
		driver.findElement(By.name("lastname")).sendKeys("username");
		
		driver.findElement(By.name("reg_email__")).sendKeys("testuser11@test.com");
		
		driver.findElement(By.name("reg_email_confirmation__")).sendKeys("testuser11@test.com");
		
		driver.findElement(By.name("reg_passwd__")).sendKeys("testn@me123");
		
		Select day=new Select(driver.findElement(By.id("day")));
		
		Select month=new Select(driver.findElement(By.id("month")));
		
		Select year=new Select(driver.findElement(By.id("year")));
		
		day.selectByValue("21");
		
		month.selectByVisibleText("Jun");
		
		year.selectByVisibleText("1989");
		
		List<WebElement> gender=driver.findElements(By.xpath("//*[@data-name='gender_wrapper']/span/label"));
		for(WebElement e:gender)
		{
			if(e.getText().equalsIgnoreCase("Male"))
				e.click();
		}
		driver.findElement(By.name("websubmit")).click();
		
		Thread.sleep(2000);
		
	}
	
	@AfterTest
	public void closeDriver() {
		driver.quit();
	}

}
