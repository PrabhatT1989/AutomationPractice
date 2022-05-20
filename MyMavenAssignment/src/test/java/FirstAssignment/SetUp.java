package FirstAssignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SetUp {
	
	public static WebDriver driver;
	
	String driverpathVersion101 = System.getProperty("user.dir") + "\\Driver\\chromedriver.exe";
	Properties prop = new Properties();
	
FileInputStream fis;
	
	@BeforeClass
	public void setup() throws IOException {
		
		fis = new FileInputStream(System.getProperty("user.dir") + "\\Properties\\config.properties");
		prop.load(fis);

		System.setProperty("webdriver.chrome.driver", driverpathVersion101);
		driver = new ChromeDriver();
		driver.get(prop.getProperty("QA_URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));

	}
	

	@AfterClass
	public void close() {

		driver.manage().deleteAllCookies();

	}


}
