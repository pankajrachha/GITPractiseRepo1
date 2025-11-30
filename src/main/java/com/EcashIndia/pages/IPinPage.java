package com.EcashIndia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import parentObjects.PageObject;


public class IPinPage extends PageObject {
	
	
	public IPinPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	private By ipin = By.id("ipin");
	private By submit2 = By.id("otpbut");
	
	public void enterIPin(String val) {
		try {
			if (isDisplayed(ipin, 30) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(ipin, val, "agreement", 30);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}
	
	
	public void submit() throws Exception{
		click(submit2, "submit button", 30);
	}

}
