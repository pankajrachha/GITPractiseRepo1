package endToEndFlow;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {

	public static void main(String[] args) {
		
		TestNG testng = new TestNG();
		//testng.setTestClasses(new Class[] {Testcases.class});
		
		List<String> suitefiles=new ArrayList<String>();
		
//		suitefiles.add("C:\\Pankaj\\selenium-workspaces\\Workspace for Ecash India Projects\\ECashIndia_Automation\\testng.xml");
		
//		System.out.println(System.getProperty("user.dir"));
		suitefiles.add(System.getProperty("user.dir")+".\\testng.xml");
		
		testng.setTestSuites(suitefiles);
		
		testng.run();

	}

}
