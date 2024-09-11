package Practice1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	public WebDriver driver;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://app.germanyiscalling.com/common/login/?next=https%3A%2F%2Fapp.germanyiscalling.com%2Fcv%2Fhome%2F");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));


	}


	@Test(priority = 4)
	public void successfullLogin() {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("demo123@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Demo@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String actualTitle = "Upload your CV | Germany Is Calling";
		String expectedTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("You are Successfull login");
		driver.close();
	}
	@Test(priority = 1)
	public void  unsuccessfulLogin() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("seeleshthakur10@");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("fhyjhyj");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		WebElement errorMessage = driver.findElement(By.xpath("//li"));
		String expectedErrorMessege = errorMessage.getText();
		String actualErrorMessege = "Please enter a correct username and password. Note that both fields may be case-sensitive.";
		Assert.assertEquals(actualErrorMessege, expectedErrorMessege);
		System.out.println("Sorry! You are not able to login with Invalid Email or Password");

	}
	@Test(priority = 2)
	public void   specialcharactersLogin() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("seeleshthakur10@");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("fhyjhyj");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		WebElement errorMessage = driver.findElement(By.xpath("//li"));
		String expectedErrorMessege = errorMessage.getText();
		String actualErrorMessege = "Please enter a correct username and password. Note that both fields may be case-sensitive.";
		Assert.assertEquals(actualErrorMessege, expectedErrorMessege);
		System.out.println("Sorry! You are not able to login with  Special Characters");
		
	}
	@Test(priority = 3)
	public void  emptyFieldsLogin() throws InterruptedException {
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		WebElement errorMessage = driver.findElement(By.xpath("//li[1]"));
		String expectedErrorMessege = errorMessage.getText();
		String actualErrorMessege = "Email: This field is required.";
		Assert.assertEquals(actualErrorMessege, expectedErrorMessege);
		System.out.println("Sorry! You are not able to login with  Empty Fields");
		
	}


	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
		System.out.println("done");

	}
}
