package Module_Login;

import java.awt.AWTException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import xeroAppTesting.ReusableFlows;
import xeroAppTesting.ReusableMethods;
import xeroAppTesting.SetUps;

public class Rough extends ReusableFlows {
	@BeforeSuite
	public static void beforeSuite()
	{
		initializeExtentReport();
		readORFile("./src/test/java/repository/objRep.properties");
		readConfigFile("./src/test/java/repository/config.properties");
	}
	@AfterMethod
	public static void afterEachMethod()
	{
		extent.flush();
	}
	//
	//	@Test
	//	public static void TC12_UploadDP() throws Exception
	//	{
	//		//create test case report tab
	//		ExtentTest logger = createTestScriptReport("TC12");
	//
	//		//give data file path
	//		readDataFile("./src/test/java/dataFiles/TC12_data.properties");
	//
	//		// reusable flow
	//		enterEmailPasswordLogin();
	//
	//		//click user menu
	//		WebElement userMenu = driver.findElement(getLocator("HomePage.userMenu"));
	//		clickElement(userMenu, "User Menu");
	//
	//		Thread.sleep(3000);
	//		
	//		//click profile
	//		WebElement profile = driver.findElement(getLocator("HomePage.userMenu.Profile"));
	//		clickElement(profile, "profile");
	//		
	//		//click upload image button
	//		Thread.sleep(3000);
	//		WebElement uploadImageButton = driver.findElement(getLocator("ProfilePage.uploadimageButton"));
	//		clickElement(uploadImageButton, "uploadImageButton	");
	//		
	//		uploadFile(dataProp.getProperty("imageAddress"));
	//		
	//		// click save button
	//		Thread.sleep(3000);
	//		WebElement saveButton = driver.findElement(getLocator("ProfilePage.saveButton"));
	//		saveButton.click();
	//		
	//		//close browser
	//		closeBrowser();		
	//	}
	//
	//	public static void uploadFile(String path) throws Exception {
	//	
	//
	//		//File Need to be imported
	//
	//		File file = new File(path);
	//		
	//		StringSelection media = new StringSelection(path);
	//		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(media, null);
	//		
	//
	//		//Copy to clipboard Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	//
	//		System.out.println("copied path");
	//	
	//		
	//		Robot robot = new Robot();
	//
	//		// Cmd + Tab is needed since it launches a Java app and the browser looses focus
	//
	//		System.out.println("robor 1");
	//		robot.keyPress(KeyEvent.VK_META);
	//
	//		robot.keyPress(KeyEvent.VK_TAB);
	//
	//		robot.keyRelease(KeyEvent.VK_META);
	//
	//		robot.keyRelease(KeyEvent.VK_TAB);
	//
	//		robot.delay(500);
	//
	//		System.out.println("window 1");
	//		//Open Goto window
	//
	//		robot.keyPress(KeyEvent.VK_META);
	//
	//		robot.keyPress(KeyEvent.VK_SHIFT);
	//
	//		robot.keyPress(KeyEvent.VK_G);
	//
	//		robot.keyRelease(KeyEvent.VK_META);
	//
	//		robot.keyRelease(KeyEvent.VK_SHIFT);
	//
	//		robot.keyRelease(KeyEvent.VK_G);
	//		System.out.println("win 2");
	//
	//		//Paste the clipboard value
	//
	//		robot.keyPress(KeyEvent.VK_META);
	//
	//		robot.keyPress(KeyEvent.VK_V);
	//
	//		robot.keyRelease(KeyEvent.VK_META);
	//
	//		robot.keyRelease(KeyEvent.VK_V);
	//		System.out.println("win 3");
	//
	//		//Press Enter key to close the Goto window and Upload window
	//
	//		robot.keyPress(KeyEvent.VK_ENTER);
	//
	//		robot.keyRelease(KeyEvent.VK_ENTER);
	//
	//		robot.delay(500);
	//
	//		robot.keyPress(KeyEvent.VK_ENTER);
	//
	//		robot.keyRelease(KeyEvent.VK_ENTER);
	//		
	//		System.out.println("robor 4");
	//
	//	}

	@Test

	public static void TC08_newOffersLink() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC08");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC05_data.properties");

		//open browser
		openBrowser("firefox");

		//open sigup page
		openWebPage(dataProp.getProperty("URL"));

		//click free trail
		WebElement freTrailButton = driver.findElement(getLocator("SignUp.freeTrailButton"));
		clickElement(freTrailButton, "free Trail Button");

		//store current window before clicking the link
		String winHandleBefore = driver.getWindowHandle();

		//click offer details link
		WebElement offerDetailsLink = driver.findElement(getLocator("SignUp.offerDeatilsLink"));
		clickElement(offerDetailsLink, "offer details");

		// go to new tab
		Thread.sleep(3000);
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		System.out.println("switched to new tab \n");
		Thread.sleep(3000);

		//verify Offer details page is disaplyed
		checkTextAnywhereOnPage("Offer details " , "Offer details  page");	

		// Close the new tab
		driver.close();

		// Switch  to original tab
		driver.switchTo().window(winHandleBefore);

		closeBrowser();
	}

}