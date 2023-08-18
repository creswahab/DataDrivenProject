package LoginProcess;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class loginWithJxl {
	
	String [] [] data = null;
	WebDriver driver;
	
	
	
	@DataProvider(name="loginData")
	public String[][] dataProvider() throws BiffException, IOException {
		data = getexcel();
		 return data;
	}
	
	public String[][] getexcel() throws BiffException, IOException {
		
		FileInputStream excel = new FileInputStream("C:\\Users\\pwaha\\Documents\\TestData.xls");
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);
		int rowCount = sheet.getRows();
		int columnCount = sheet.getColumns();
		
		String testdata [][] = new String[rowCount-1][columnCount];
		
		for(int i=1; i<rowCount; i++) {
			for(int j=0; j<columnCount; j++) {
				 testdata[i-1][j] = sheet.getCell(j, i
						 ).getContents();
			}
			
		}
		return testdata;
	}

	@BeforeTest
	public void beforTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pwaha\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	

	@Test(dataProvider = "loginData")
	public void loginWithCredentials(String uName, String pWord) throws InterruptedException {
		
		
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(uName);
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pWord);
		
		WebElement submit = driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"));
		submit.click();
		
		Thread.sleep(3000);
		
		
		
	}
}
