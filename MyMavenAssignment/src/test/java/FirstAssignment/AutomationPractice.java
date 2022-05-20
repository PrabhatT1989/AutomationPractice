package FirstAssignment;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationPractice extends SetUp {

	static String totalPriceBeforeProceedCheckout;

	@Test(priority = 1, description = "Hover over Women link and click Casual Dress Link")
	public void hoverOverWomenLink_AndClickCasualDressLink() throws InterruptedException {
		// String WomenLoc = "//a[text()='Women']";
		String CasualDressLinkLoc = prop.getProperty("CasualLink_Xpath");

		WebElement WomenLinkElement = driver.findElement(By.xpath(prop.getProperty("WomenLoc")));

		Actions hoverOverWomenLink = new Actions(driver);
		hoverOverWomenLink.moveToElement(WomenLinkElement).perform();
		;

		driver.findElement(By.xpath(CasualDressLinkLoc)).click();

		String QuickViewButtonLoc = "//span[text()='Quick view']";

		// String inStockButtonLoc = "(//*[contains(text(),'Printed Dress')])[3]";

		String inStockButtonLoc = "//*[contains(text(),'Printed Dress')]";

		// String frameLoc = "//*[@class='fancybox-iframe']";

		WebElement inStockButtonWebElement = driver.findElement(By.xpath(prop.getProperty("inStockButtonLoc")));

		Actions PrintedDresses = new Actions(driver);
		PrintedDresses.moveToElement(inStockButtonWebElement).perform();

		driver.findElement(By.xpath(prop.getProperty("QuickViewButtonLoc"))).click();

		Thread.sleep(4000);

//		String frameLoc = "//*[contains(@id,'fancybox-frame')]";
//		// String plusIconLoc = "//*[contains(@class,'button-plus')]";
//
//		String plusIconLoc = "//*[contains(@class,'button-plus')]/span/i";
//
//		String AddToCartLoc = "//*[@id='add_to_cart']//span[text()='Add to cart']";

		// code to switch to frame ;
		WebElement frameWebElement = driver.findElement(By.xpath(prop.getProperty("frameLoc")));
		driver.switchTo().frame(frameWebElement);
		System.out.println("Switched to iFrame");

		// code to increase the quantity
		driver.findElement(By.xpath(prop.getProperty("plusIconLoc"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(prop.getProperty("plusIconLoc"))).click();
		System.out.println("Clicked on plus icon to increase the quantity -- Success");

		driver.findElement(By.xpath(prop.getProperty("AddToCartLoc"))).click();
		System.out.println("Clicked on Add to cart button");

		// code to come out of the current frame

		driver.switchTo().defaultContent();

		Thread.sleep(3000);

		// code to get the total price before proceed to checkout

		//String TotalPriceBeforeProceedToCheckoutLoc = "(//span[contains(@class,'cart_total')])[3]";

		// String totalPriceBeforeProceedToCheckoutLoc = "//*[contains(text(), 'Total
		// shipping')]/parent::div/following-sibling::div/strong[normalize-space(text()='Total')]"
		// ;

		totalPriceBeforeProceedCheckout = driver.findElement(By.xpath(prop.getProperty("TotalPriceBeforeProceedToCheckoutLoc"))).getText()
				.toString().trim();
		//Thread.sleep(3000);

		System.out.println("Total Price before proceed to checkout -- " + totalPriceBeforeProceedCheckout);

		//Thread.sleep(3000);

		//String proceedToCheckoutLoc = "//*[contains(text(),'Proceed to checkout')]";
		
		driver.findElement(By.xpath(prop.getProperty("proceedToCheckoutLoc1"))).click();
		
		System.out.println("Click on Proceed to Checkout SUCCESS -- ");

	}

	@Test(priority = 2)

	public void SummaryTab() throws InterruptedException {

		String totalPriceOnSummaryTab = driver.findElement(By.xpath(prop.getProperty("totalPriceOnSummaryTabLoc"))).getText().toString()
				.trim();

		System.out.println("total Price On Summary Tab -- " + totalPriceOnSummaryTab);

// code to tally the price displayed before the ProceedToCheckOut Action and after the ProceedToCheckOut action

//Assert.assertEquals(totalPriceBeforeProceedCheckout, totalPriceOnSummaryTab , " The didnt match, please check ...");

		Assert.assertEquals(totalPriceBeforeProceedCheckout, totalPriceOnSummaryTab);

//		Assert.assertEquals(totalPriceBeforeProceedCheckout, totalPriceOnSummaryTab,
//				" The price didnt match, please check ...");
		System.out.println("Price displayed before Proceed to checkout Matched '" + totalPriceBeforeProceedCheckout
				+ "' with  the total price after Proceed To  checkout " + totalPriceOnSummaryTab);
		Thread.sleep(2000);

		WebElement scrollToProceedToCheckoutWebelement = driver
				.findElement(By.xpath(prop.getProperty("proceedToCheckoutLoc2")));

		Thread.sleep(2000);
		scrollToProceedToCheckoutWebelement.click();

	}

	@Test(priority = 3, description = "SignIn Tab")
	public void signInTab() throws InterruptedException {
//		String emailLoc = "//*[@id='email']";
//		String passwordLoc = "//*[@id='passwd']";
//		String signInLoc = "//*[@id='SubmitLogin']";

		driver.findElement(By.xpath(prop.getProperty("emailLoc"))).sendKeys("Prabhat.Tavag@gmail.com");

		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("passwordLoc"))).sendKeys("Messi@123");
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("signInLoc"))).click();
		Thread.sleep(2000);
	}

	@Test(priority = 4, description = "Address Tab")
	public void addressTab() throws InterruptedException {

//		String commentBoxLoc = "//*[@id='ordermsg']//textarea";
//		String processAddressPTCLoc = "//*[text()='Proceed to checkout']";

		driver.findElement(By.xpath(prop.getProperty("commentBoxLoc"))).sendKeys("Comments Added");
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("proceedToCheckoutAddressTab"))).click();
		Thread.sleep(2000);
	}

	@Test(priority = 5, description = " Shipping Tab")
	public void shippingTab() throws InterruptedException {
//		String IAgreeLoc = "//*[@id='cgv']";
//		String proceedToCheckoutLoc = "//button//span[contains(text(),'Proceed to checkout')]";

		driver.findElement(By.xpath(prop.getProperty("IAgreeLoc"))).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(prop.getProperty("proceedToCheckoutShippingTabLoc"))).click();
		Thread.sleep(2000);

	}

	@Test(priority = 6, description = "Payment Tab")
	public void paymentTab() throws InterruptedException {
		//String totalPriceLoc = "//*[@id='total_price']";

		String totalPrice = driver.findElement(By.xpath(prop.getProperty("totalPriceLoc"))).getText();
		System.out.println("Total Price displayed on the Payment Tab -- " + totalPrice);

		Thread.sleep(2000);
		;

	//	String payByBankWireLoc = "//*[contains(text(),'Pay by bank wire')]";

		driver.findElement(By.xpath(prop.getProperty("payByBankWire"))).click();
		Thread.sleep(2000);
		;

		String iConfirmOrderLoc = "//*[contains(text(),'I confirm my order')]";
		driver.findElement(By.xpath(prop.getProperty("iConfirmOrderLoc"))).click();

		String orderSummary = driver.findElement(By.xpath(prop.getProperty("orderSummary"))).getText();

		System.out.println("  *******   Order Summary Info  *******  ");

		System.out.println(orderSummary);
         
		//String s = orderSummary.replaceAll("", orderSummary)
		
	}
	
	@Test(priority = 7, dependsOnMethods = "paymentTab",description =   "Contact Us")
	public void contactUs() throws InterruptedException {
		{
			
		driver.findElement(By.xpath(prop.getProperty("ContactUsLinkLoc"))).click();
		
		}
		}

}
