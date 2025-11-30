package com.EcashIndia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import parentObjects.PageObject;

public class TransactionComplete extends PageObject{

	public TransactionComplete(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	private By status = By.xpath(".//*[@class='question ']/h4/b ");
	private By transaction_no = By.xpath(".//*[@id='Cars2']/div/h4/b");
	String val;
	String value;
	


	public String status() {
		try {
			if (isDisplayed(status, 30) == true) {
				//log.info("showing signup popup so, closing it..");
				val = getText(status, "status", 30);
				
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to read status value");
			e.printStackTrace();
		}
		return val;
	}
	
	
	public String transactionNo() {
		try {
			if (isDisplayed(transaction_no, 30) == true) {
				//log.info("showing signup popup so, closing it..");
				value = getText(transaction_no, "status", 30);
//				System.out.println("Value of tx no="+value);
			} else {
				//log.info("not showing the signup popup");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: to read status value");
			e.printStackTrace();
		}
		return value;
	}
	
}
