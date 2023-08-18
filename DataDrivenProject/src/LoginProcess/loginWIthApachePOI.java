package LoginProcess;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class loginWIthApachePOI {

	static List<String> userNameList = new ArrayList<String>();
	static List<String> passwordList = new ArrayList<String>();
	
	public void getexcel() throws IOException {
		
		FileInputStream excel = new FileInputStream("C:\\Users\\pwaha\\Documents\\TestData1.xlsx");
		Workbook workbook = new XSSFWorkbook(excel);
		Sheet sheet = workbook.getSheetAt(0);
		
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext()) {
			Row rowValue = rowIterator.next();
			
			Iterator<Cell> columnIterator = rowValue.iterator();
			int i=2;
			while(columnIterator.hasNext()) {
				if(i%2==0) {
					userNameList.add(columnIterator.next().getStringCellValue());
				}else {
					passwordList.add(columnIterator.next().getStringCellValue());
				}
				
				i++;
			}
		}
		
		
		
	}
	
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
		
		//driver.quit();
		
	}
	
	public void executeTest() throws InterruptedException {
		for(int i=0; i<userNameList.size();i++) {
			loginWithCredentials(userNameList.get(i), passwordList.get(i));
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		loginWIthApachePOI usingPOI = new loginWIthApachePOI();
		usingPOI.getexcel();
		System.out.println("UserName List" + userNameList);
		System.out.println("Password List" + passwordList);
		
		usingPOI.executeTest();

	}

}
