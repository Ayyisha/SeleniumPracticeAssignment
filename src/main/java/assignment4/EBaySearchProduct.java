package assignment4;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EBaySearchProduct {
	
	static WebDriver driver;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		invokeBrowser("CHROME");
		launchBrowser("https://www.ebay.com/");
		Thread.sleep(2000);
		
		//nter a product in the search box on the homepage (say Apple Watches).
		
		SearchProduct("Apple Watches");
		
		getNthProduct(4);
		
		printAllProducts();
		
		getAllPRoductsViaScrollDown();
		
		getAllPRoductsViaJSScrolldown();
		
		driver.quit();
		
	}
	// Method to invoke browser based on the user input
	public static void invokeBrowser(String browserName) {
		switch(browserName) {
		case "CHROME":
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(options);
			break;
			
		case "EDGE":
			EdgeOptions option=new EdgeOptions();
			option.addArguments("--start-maximized");
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver(option);
			break;
			
		default:
			ChromeOptions optios=new ChromeOptions();
			optios.addArguments("--start-maximized");
			optios.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(optios);
			break;
		}
	}
	
	//launch the url
	public static void launchBrowser(String URL) {
		driver.get(URL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		System.out.println(driver.getTitle());
		
	}
	
	public static void SearchProduct(String ProductName) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.name("_nkw")).sendKeys(ProductName);
		
		WebElement selectCatg=driver.findElement(By.xpath("//select[@aria-label='Select a category for search']"));
		Select category=new Select(selectCatg);
		category.selectByVisibleText("Cell Phones & Accessories");
		
		driver.findElement(By.xpath("//input[@type='submit' and @value='Search']")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		String result=driver.findElement(By.xpath("//div[@class='srp-controls__control srp-controls__count']/h1/span[1]")).getText();
		
		System.out.println("Result: "+result);
		
		
	}
	
	public static void getNthProduct(int itemnumber) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String nthele=driver.findElement(By.xpath("//div[@class='srp-river-results clearfix']/ul/li["+itemnumber+"]")).getText();
		System.out.println("Nth Product: "+nthele);
		
	}
	
	public static void printAllProducts() {
		List<WebElement> allProducts=driver.findElements(By.xpath("//div[@class='srp-river-results clearfix']/ul/li"));
		for(WebElement e: allProducts) {
			System.out.println(e.getText());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			System.out.println("---------------------------------------------------------------");
			
		}
	}
	
	public static void getAllPRoductsViaScrollDown() throws Exception {
		Actions action=new Actions(driver);
		List<WebElement> allProducts=driver.findElements(By.xpath("//div[@class='srp-river-results clearfix']/ul/li"));
		for(WebElement e: allProducts) {
			action.moveToElement(e).release().perform();
			System.out.println(e.getText());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			System.out.println("---------------------------------------------------------------");
			
		}
		//scroll to top of the page
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		
		//js.executeScript("arguments[0].scrollIntoView();", element);
		
		//down
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		Thread.sleep(5000);
	}
	
	public static void getAllPRoductsViaJSScrolldown() {
		List<WebElement> allProducts=driver.findElements(By.xpath("//div[@class='srp-river-results clearfix']/ul/li"));
		int x,y;
		JavascriptExecutor js=(JavascriptExecutor)driver;
		for(WebElement e: allProducts) {
			x=e.getLocation().x+e.getSize().width/2;
			y=e.getLocation().y+e.getSize().height/2;
			String cmd=String.format("window.scrollBy(%d,%d)", x,y);
			js.executeScript(cmd,"");
			System.out.println(e.getText());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			System.out.println("---------------------------------------------------------------");
			
		}
	}

}
