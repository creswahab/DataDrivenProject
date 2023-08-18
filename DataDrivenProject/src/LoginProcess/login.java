package LoginProcess;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class login {

	String [] [] data = 
		{
				{"admin","admin456"},
				{"Admin","admin456"},
				{"admin2","admin123"},
				{"Admin", "admin123"}
			
		};
	
	@DataProvider(name="loginData")
	public String[][] dataProvider() {
		 return data;
	}
	
	
	
	@Test(dataProvider = "loginData")
	public void loginWithCredentials(String uName, String pWord) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pwaha\\selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		Thread.sleep(5000);
		
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(uName);
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pWord);
		
		WebElement submit = driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"));
		submit.click();
		
		driver.quit();
		
	}
	
}
