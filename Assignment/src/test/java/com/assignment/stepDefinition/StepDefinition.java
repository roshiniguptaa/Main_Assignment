package com.assignment.stepDefinition;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	WebDriver driver;
	ExtentReports reports;
	ExtentTest test;

	@Before
	public void setup() {

		reports = new ExtentReports(System.getProperty("user.dir") + "/Reportss/StepDefinition.html");
		test = reports.startTest("ExtentDemo");

		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver();
		test.log(LogStatus.INFO, "New Test Case Starts");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	@Given("User is on Flipkart page")
	public void user_is_on_flipkart_page() {
		// User is on Flipkart website.

		driver.get("https://www.flipkart.com/");
		test.log(LogStatus.PASS, "Website is working fine");
		driver.manage().window().maximize();
		test.log(LogStatus.PASS, "Screen is maximised");
		driver.findElement(By.className("_30XB9F")).click();

	}

	@Given("User is on Flipkart Travel page")
	public void user_is_on_flipkart_travel_page() {
		// User has clicked on Travel.
		driver.findElement(By.xpath("(//div[@class=\"_3ETuFY\"])[7]")).click();
		test.log(LogStatus.PASS, "Clicked on Travel");
		
	}

	@When("User enters From To Location")
	public void user_enters_from_to_location() throws InterruptedException {
		// User selected Round-trip button
		Thread.sleep(5000);

		driver.findElement(By.xpath("//label[@for=\"ROUND_TRIP\"]")).click();
		test.log(LogStatus.PASS, "Round-Trip button selected");

		// From
		driver.findElement(By.xpath("(//input[@type=\"text\"])[1]")).sendKeys("Mum");
		driver.findElement(By.xpath("(//input[@type=\"text\"])[1]")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("(//input[@type=\"text\"])[1]")).sendKeys(Keys.ENTER);
		test.log(LogStatus.PASS, "Mumbai selected ");
		

		// To
		driver.findElement(By.xpath("(//input[@type=\"text\"])[2]")).sendKeys("Hyd");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@type=\"text\"])[2]")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("(//input[@type=\"text\"])[2]")).sendKeys(Keys.ENTER);
		test.log(LogStatus.PASS, "Hyderbad selected");

	}

	@When("User selects Depart On and Return On Dates")
	public void user_selects_depart_on_and_return_on_dates() {

		// Depart On
		driver.findElement(By.xpath("//input[@tabindex=\"03\"]")).click();

		String month = "November 2023";
		String exp_date = "15";

//		String text = driver.findElement(By.xpath("//div[@class=\"_1oqlzu\"]")).getText();
//		System.out.println(text);

		while (true) {
			String text1 = driver.findElement(By.xpath("//div[@class=\"_1oqlzu\"]")).getText();
			if (text1.equals(month)) {
				break;
			} else {
				driver.findElement(By.xpath(
						"//*[@id=\"container\"]/div/div[2]/div[1]/div/div[2]/div[1]/div[2]/form/div/div[3]/div[1]/div[2]/div/div/div/div/table[2]/thead/tr[1]/th[3]/div/button"))
						.click();
			}
		}

		// while loop is for till we get expected month, once we get expected month it
		// will break the loop

		List<WebElement> allDates = driver.findElements(By.xpath(
				"//*[@id=\"container\"]/div/div[2]/div[1]/div/div[2]/div/div[2]/form/div/div[3]/div[1]/div[2]/div/div/div/div/table[1]/tbody/tr/td/div/button"));

		// for loop is for getting all the dates 1 to 30
		for (WebElement ele : allDates) {
			String date_text = ele.getText();
			String date[] = date_text.split("\n");
			// It will split wrt to next line and it will store as an array
			// It will contain two value date and price we want date so index 0

			if (date[0].equals(exp_date)) {
				ele.click();
				break;

			}
		}
		
		test.log(LogStatus.PASS, "Depart-on date selected");

		// Return On
		driver.findElement(By.xpath("//input[@tabindex=\"04\"]")).click();

		String month1 = "December 2023";
		String exp_date1 = "20";

		String text = driver.findElement(By.xpath("//div[@class=\"_1oqlzu\"]")).getText();
		System.out.println(text);

		while (true) {
			String text1 = driver.findElement(By.xpath("//div[@class=\"_1oqlzu\"]")).getText();
			if (text1.equals(month1)) {
				break;
			} else {
				driver.findElement(By.xpath(
						"//*[@id=\"container\"]/div/div[2]/div[1]/div/div[2]/div[1]/div[2]/form/div/div[3]/div[1]/div[2]/div/div/div/div/table[2]/thead/tr[1]/th[3]/div/button"))
						.click();
			}
		}

		List<WebElement> allDatess = driver.findElements(By.xpath(
				"//*[@id=\"container\"]/div/div[2]/div[1]/div/div[2]/div/div[2]/form/div/div[3]/div[1]/div[2]/div/div/div/div/table[1]/tbody/tr/td/div/button"));

		for (WebElement elee : allDatess) {
			String date_text1 = elee.getText();
			String date1[] = date_text1.split("\n");

			if (date1[0].equals(exp_date1)) {
				elee.click();
				break;

			}
		}
		
		test.log(LogStatus.PASS, "Return Date selected");

	}

	@When("Clicks on Search")
	public void clicks_on_search() {

		driver.findElement(By.xpath("//button[@tabindex=\"5\"]")).click();
	}

	@Then("Validate the total price for flights is equal to the price of sum of both flights.")
	public void validate_the_total_price_for_flights_is_equal_to_the_price_of_sum_of_both_flights() {
		String price1 = driver.findElement(By.xpath("(//div[@class=\"_1AhVAh\"])[1]")).getText();
		price1 = price1.replaceAll("[^\\d.]", "");
		System.out.println(price1);
		System.out.println(price1 instanceof String);

		int newprice1 = Integer.parseInt(price1);
		System.out.println(newprice1);
		System.out.println(Integer.class.isInstance(newprice1));

//		---------------------------------------------------------------------------------------------------

		String price2 = driver.findElement(By.xpath(
				"//*[@id=\"container\"]/div/div[2]/div/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[3]/div[1]/div/div[2]/div[3]"))
				.getText();
		price2 = price2.replaceAll("[^\\d.]", "");
		System.out.println(price2);
		System.out.println(price2 instanceof String);

		int newprice2 = Integer.parseInt(price2);
		System.out.println(newprice2);
		System.out.println(Integer.class.isInstance(newprice2));

		int additionprice = newprice1 + newprice2;

//		-----------------------------------------------------------------------------------

		String price3 = driver
				.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div/div[2]/div[1]/span[2]"))
				.getText();
		price3 = price3.replaceAll("[^\\d.]", "");
		System.out.println(price3);
		System.out.println(price3 instanceof String);

		int newprice3 = Integer.parseInt(price3);
		System.out.println(newprice3);
		System.out.println(Integer.class.isInstance(newprice3));

		Assert.assertEquals(additionprice, newprice3);
		
		test.log(LogStatus.PASS, "The total price for flights is equal to the price of sum of both flights");
		
		reports.endTest(test);

	}

	@After
	public void tearDown() throws IOException {

		String temp = getScreenshot(driver);
		String a = test.addScreenCapture(temp);
		test.log(LogStatus.PASS, a);

		reports.flush();
		driver.close();
		driver.quit();
	}

	public static String getScreenshot(WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = (System.getProperty("user.dir") + "/Screenshots/TwoWayR.png");

		File Destination = new File(path);
		FileUtils.copyFile(source, Destination);

		return path;

	}

}
