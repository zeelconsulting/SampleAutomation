package pages;

import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.Alert;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import utils.*;

public class PopUpsConcepts extends PageObject{

	@Steps
	ReportLog report;
	@Steps
	CommonMethods common;


	public void testJavaScriptAlerts() {

		getDriver().get("https://www.rediff.com/"); waitABit(3000);
		getDriver().manage().window().maximize();
		getDriver().findElement(By.className("signin")).click();waitABit(3000);
		getDriver().findElement(By.name("proceed")).click();
		report.LOG("clicked on login and alert is displayed");

		// creating alert object
		Alert alert = getDriver().switchTo().alert();
		waitABit(3000);

		String alertmessage = alert.getText();
		System.out.println(alertmessage);
		// to accept the alert
		alert.accept();
		
		// to cancel alert
		//alert.dismiss();

		//to enter data in the alert
		//	alert.sendKeys("text to enter");
		//	alert.accept();

		Assert.assertEquals("Please enter a valid user name", alertmessage);
		report.LOG("login alert is accepted");
		System.out.println("login alert is accepted");


		getDriver().findElement(By.id("login1")).sendKeys("Test12344");waitABit(3000);
		getDriver().findElement(By.name("proceed")).click();waitABit(3000);
		System.out.println("-----------------------------------------");
		String alertmessage2 = alert.getText();
		System.out.println(alertmessage2);
		alert.accept();

		Assert.assertEquals("Please enter your password", alertmessage2);
		report.LOG("password alert is accepted");
		System.out.println("password alert is accepted");

	}


	public void testDesktopPopUp(String fileName) {
		
		// to get the folder pathof your project
		String path = System.getProperty("user.dir");
		String filepath = path +"\\data\\";
		String fullFilePath  = filepath + fileName;
		System.out.println(fullFilePath);

		getDriver().get("https://html.com/input-type-file/");
		getDriver().manage().window().maximize();
		waitABit(3000);
		common.scrolldown();
		common.scrolldown();

		waitABit(3000);
		// we will not do click; becasue a window will open and control will shoft to that window popup
		// which Selenium cannot handle
		getDriver().findElement(By.id("fileupload")).sendKeys(fullFilePath);
		waitABit(5000);
		report.LOG("uploaded " + fileName);
		System.out.println("uploaded " + fileName);

		getDriver().findElement(By.xpath("//input[@type='submit']")).click();
		report.LOG("Submit is clicked");
		System.out.println("Submit is clicked");
		common.closebrowser();

	}

}
