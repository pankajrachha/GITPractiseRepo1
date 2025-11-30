package com.EcashIndia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import parentObjects.PageObject;



public class HomePage extends PageObject  {

	private By agreementNo = By.id("txtAN");
	private By re_agreementNo = By.id("txtRAN");

	private By firstName = By.id("txtFName");
	private By lastName = By.id("txtLName");
	private By reg_mobileno = By.id("txtRegMobileNo");
	private By emailId = By.id("txtEmailId");
	private By total_Amount = By.xpath(".//*[@name='PayableAmount']");
	private By submit = By.xpath(".//*[@id='btnSubmit']");

	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}

	public void agreementNumber(String val) {
		try {
			
			
			if (isDisplayed(agreementNo, 10) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(agreementNo, val, "agreement", 10);
			} else {
				//log.info("not showing the signup popup");
			}
			
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}

	public void reagreementNumber(String val) {
		try {
			if (isDisplayed(re_agreementNo, 10) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(re_agreementNo, val, "agreement", 10);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}

	public void enterFirstName(String val) {
		try {
			if (isDisplayed(firstName, 10) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(firstName, val, "agreement", 10);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}

	public void enterLastName(String val) {
		try {
			if (isDisplayed(lastName, 10) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(lastName, val, "agreement", 10);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}

	public void entermobileNo(String val) {
		try {
			if (isDisplayed(reg_mobileno, 10) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(reg_mobileno, val, "agreement", 10);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}

	public void enterEmailId(String val) {
		try {
			if (isDisplayed(emailId, 10) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(emailId, val, "agreement", 10);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}

	public void enterTotalAmount(String val) {
		try {
			if (isDisplayed(total_Amount, 10) == true) {
				//log.info("showing signup popup so, closing it..");
				sendKeys(total_Amount, val, "agreement", 10);
			} else {
				//Assert.fail("Element"+total_Amount+" is not displayed");
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to close signup pop up in homepage");
			e.printStackTrace();
		}
	}

	public void submit() throws Exception{
		click(submit, "login button", 10);
	}


}
