package Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UI_scenarios {
	
	WebDriver driver;
	
	@BeforeMethod
	public void brosweropen()
	{
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("http://www.htmlcanvasstudio.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 
	}
	@Test
	public void TestCreative() throws InterruptedException 
	{
		
		
		WebElement drawLineButton = driver.findElement(By.xpath("//input[@title='Draw a line']"));
		drawLineButton.click();

		WebElement canvas = driver.findElement(By.id("imageTemp"));

		int canvasWidth = canvas.getSize().getWidth();
		System.out.println("Width of canvas : " + canvasWidth);
		int canvasHeight = canvas.getSize().getHeight();
		System.out.println("height of canvas : " +canvasHeight);

		Actions actions = new Actions(driver);
		Assert.assertTrue(canvas.isDisplayed());
	   
		// draw a horizontal line
		Action horizontalline=actions.moveToElement(canvas, -100, -130)
		.clickAndHold().moveByOffset(200, 0).release().click().build();
		horizontalline.perform();
		
		
	    driver.findElement(By.xpath("//input[@title='Draw a line']")).click();
	    // draw a vertical line
		actions.moveToElement(canvas, 0, -30);
        actions.clickAndHold().moveByOffset(0, -200).release().click().build().perform();

        // draw a rectangle
		driver.findElement(By.xpath("//input[@title='Draw a rectangle']")).click();
        actions.moveToElement(canvas, -100, 0);
		actions.clickAndHold().moveByOffset(200, 200).release().click().build().perform();

		// Erase the vertical line 
		driver.findElement(By.xpath("//input[@title='Use eraser']")).click();
		
		 // move to the start point of the line and click
		actions.moveToElement(canvas, -100, -130).clickAndHold().build().perform();
		 // drag to the end point of the line and release the mouse button
		actions.moveByOffset((-3) - (-100), (-130) - (-130)).release().click().build().perform();	
		 // move to the start point of the line and click
	    actions.moveToElement(canvas, 3, -130).clickAndHold().build().perform();
		 // drag to the end point of the line and release the mouse button
	    actions.moveByOffset(100 - (3), (-130) - (-130)).release().click().build().perform();
	    
	    Assert.assertFalse(canvas.equals(horizontalline),"Horizontal line deleted successfully");
	   
	 
		
	}
	
	@AfterMethod
	public void browserclose()
	{
		
		driver.quit();
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
