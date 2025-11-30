package Analyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	int counter =0;
	int retryLimit = 2;
	
	public boolean retry(ITestResult result) {

		if (result.getThrowable().toString()
		        .contains("Exception"))
		{
		if(counter<retryLimit)
		{
			System.out.println("Retrying the testcase");
			System.out.println("Counter="+counter);
			counter++;
			return true;
		}
		
	}
		return false;
	}
	

}
