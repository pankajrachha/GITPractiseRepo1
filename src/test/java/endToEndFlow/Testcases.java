package endToEndFlow;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.EcashIndia.pages.HomePage;
import com.EcashIndia.pages.IPinPage;
import com.EcashIndia.pages.PayByDebitPage;
import com.EcashIndia.pages.TransactionComplete;


public class Testcases {	

	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static WebDriver driver;
	public static FileOutputStream fos;


	@BeforeTest
	public void setUp()
	{
//		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test()
	public void m1() throws Exception
	{		
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+ ".\\software_file.xlsx");
		workbook = new XSSFWorkbook(fs);
		sheet = workbook.getSheetAt(0);
		int rowcount = sheet.getLastRowNum();
//		boolean flag = true;
		
		for(int i=1; i<=rowcount; i++)
		{
			DataFormatter dataformatter = new DataFormatter();
			
//			String agreementvalue = dataformatter.formatCellValue(sheet.getRow(i).getCell(0));
//			String val = dataformatter.formatCellValue(sheet.getRow(i).getCell(11));
//			System.out.println("cell value="+val);
			
			if(!dataformatter.formatCellValue(sheet.getRow(i).getCell(11)).equalsIgnoreCase("yes"))
			{
				
				
				for(int count=1; count<=8; count++)
				{
					driver.get("https://www.aeoncredit.co.in/PayEMI/");
	
//					DataFormatter dataformatter = new DataFormatter();
					String agreementvalue = dataformatter.formatCellValue(sheet.getRow(i).getCell(0));
					String re_agreementvalue = dataformatter.formatCellValue(sheet.getRow(i).getCell(1));
					String firstnameval = dataformatter.formatCellValue(sheet.getRow(i).getCell(2));
					String lastnameval = dataformatter.formatCellValue(sheet.getRow(i).getCell(3));
					String mobileNoval = dataformatter.formatCellValue(sheet.getRow(i).getCell(4));
					String emailIdval = dataformatter.formatCellValue(sheet.getRow(i).getCell(5));
					String amountval = dataformatter.formatCellValue(sheet.getRow(i).getCell(6));
					String cardNoval = dataformatter.formatCellValue(sheet.getRow(i).getCell(7));
					String iPinval = dataformatter.formatCellValue(sheet.getRow(i).getCell(8));
					String cvvVal = dataformatter.formatCellValue(sheet.getRow(i).getCell(9));
					String expiryval = dataformatter.formatCellValue(sheet.getRow(i).getCell(10));
	
					String dates[] = expiryval.split("/");
					String month = dates[0];
					String year = dates[2];
	
					System.out.println("month="+month);
					System.out.println("year="+year);
	
					HomePage hp = new HomePage(driver);
	
					hp.agreementNumber(agreementvalue);
					hp.reagreementNumber(re_agreementvalue);
					hp.enterFirstName(firstnameval);
					hp.enterLastName(lastnameval);
					hp.entermobileNo(mobileNoval);
					hp.enterEmailId(emailIdval);
					hp.enterTotalAmount(amountval);
					hp.submit();
	
					PayByDebitPage pdp = new PayByDebitPage(driver);
					pdp.cardNumber(cardNoval);
					pdp.expMonth(month);
					pdp.expYear(year);
					pdp.enterCVV(cvvVal);
					pdp.enterCardHolder("Testing");
					pdp.proceed();	
	
					IPinPage ip = new IPinPage(driver);
					ip.enterIPin(iPinval);
					ip.submit();
	
					TransactionComplete tc = new TransactionComplete(driver);
					String status = tc.status();
					String transaction_no = tc.transactionNo();
	
					System.out.println("tx no="+transaction_no);
					
					Assert.assertTrue(false);
	
	//				sheet.getRow(0).createCell(12).setCellValue("Status");
	//				sheet.getRow(0).createCell(13).setCellValue("Transaction No");
	//
	//				sheet.getRow(i).createCell(12).setCellValue(status);
	//				sheet.getRow(i).createCell(13).setCellValue(transaction_no);
					
					
					sheet.getRow(0).createCell(count+11).setCellValue("Status"+"="+"Transaction No");
	//				sheet.getRow(0).createCell(count+12).setCellValue("Transaction No");
	
					sheet.getRow(i).createCell(count+11).setCellValue(status+"="+transaction_no);
	//				sheet.getRow(i).createCell(count+12).setCellValue(transaction_no);
	
	
					fos = new FileOutputStream(System.getProperty("user.dir")+ ".\\software_file.xlsx");
					workbook.write(fos);
	
				}
				sheet.getRow(i).createCell(11).setCellValue("Yes");
				fos = new FileOutputStream(System.getProperty("user.dir")+ ".\\software_file.xlsx");
				workbook.write(fos);	
				
//				throw new Exception("Exception message");
		}
			
		}
	}


	@AfterTest
	public void tearDown() throws Exception
	{
		driver.quit();
		fos.close();
		workbook.close();
		System.out.println("All browsers are closed");
	}


}
