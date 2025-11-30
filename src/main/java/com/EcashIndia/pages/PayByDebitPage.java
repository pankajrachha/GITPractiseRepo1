package com.EcashIndia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import parentObjects.PageObject;

public class PayByDebitPage extends PageObject {
	
	public PayByDebitPage(WebDriver driver)
	{
		this.driver = driver;
	}

	private By cnumber = By.id("cnumber");
	private By expmon = By.id("expmon");
	private By expyear = By.id("expyr");
	private By cvv = By.id("cvv2");
	private By choldname = By.id("cname2");
	private By makepay = By.id("proceedForm");


	public void cardNumber(String val) {
		try {
			if (isDisplayed(cnumber, 30) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(cnumber, val, "agreement", 30);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}
	
	
	public void expMonth(String val) {
		try {
			if (isDisplayed(expmon, 30) == true) {
				//log.info("showing signup popup so, closing it..");
				selectDropDownByValue(expmon, val, "exp month", 30);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}
	
	
	public void expYear(String val) {
		try {
			if (isDisplayed(expyear, 30) == true) {
				//log.info("showing signup popup so, closing it..");
				selectDropDownByValue(expyear, val, "exp year", 30);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}
	
	
	public void enterCVV(String val) {
		try {
			if (isDisplayed(cvv, 30) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(cvv, val, "agreement", 30);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}
	
	
	public void enterCardHolder(String val) {
		try {
			if (isDisplayed(choldname, 30) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(choldname, val, "agreement", 30);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}
	
	
	public void proceed() throws Exception{
		click(makepay, "proceed button", 30);
	}
	
}
