package assignment3;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;

/*Open a Browser (write the generic code such that by changing the parameter browser can be changed.)
Navigate to https://flipkart.com website.
Write a method to find the count (number of) links on the homepage of Flipkart.
Write another method to print link text and URLs of all the links on the page of Flipkart.*/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindLinksInFilkart {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String browserType="CHROME";
		WebDriver driver ;
		switch(browserType) {
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(options);
			break;
			
		case "EDGE":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();break;
			
		default:
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
			
		}
		
		driver.get("https://flipkart.com");
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		int count =findLinksCount(driver);
		
		System.out.println("Number of links in flipkart main page is: "+count);
		 
		printLinkTextURL(driver);
		
		Thread.sleep(5000);
		
		driver.quit();
		
		
	}

	public static int findLinksCount(WebDriver driver) {
		
		List<WebElement> links=driver.findElements(By.tagName("a"));
		
		return links.size();
	}
	
	public static void printLinkTextURL(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> links=driver.findElements(By.tagName("a"));
		for(int i=0;i<links.size();i++) {
			System.out.println("Link "+i+"- Text : "+links.get(i).getText()+" - URL : "+links.get(i).getAttribute("href"));
		}
	}
	
	
}
