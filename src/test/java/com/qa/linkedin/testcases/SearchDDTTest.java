package com.qa.linkedin.testcases;

import java.io.IOException;

import org.testng.Assert;
//import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLogedInPage;
import com.qa.linkedin.pages.LinkedinLoginPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.TestUtil;



public class SearchDDTTest extends TestBase {
	LinkedinHomePage hmpg=null;
	LinkedinLoginPage lpg=null;
	LinkedinLogedInPage lggpg=null;
	SearchResultsPage srpg=null;
	
  public SearchDDTTest() throws IOException {
		super();
		
	}

@Test(dataProvider = "dp")
  public void searchTest(String s) throws InterruptedException, IOException {
	//AssertJUnit.assertTrue(lggpg.verifyprofileCard());
	Assert.assertTrue(lggpg.verifyprofileCard());
	lggpg.searchPeople(s);
	int cnt=srpg.getResultsCount();
	System.out.println("results count for "+s+" is--> "+cnt);
	driver.navigate().back();
    driver.navigate().refresh();
	
  }

  @DataProvider
  public Object[][] dp() throws IOException {
   Object[][] data=TestUtil.getTestData(TestUtil.TESTDATA_SHEET_PATH1, "sheet1") ;
   return data; 
   
  }
  
  @BeforeClass
  public void beforeClass() throws IOException {
	  //initwebdriver initialize browser and url both
	  initWebdriver();
	  hmpg=new LinkedinHomePage();
	  lpg=new LinkedinLoginPage();
	  lggpg=new LinkedinLogedInPage();
	  srpg=new SearchResultsPage();
	  //we need to loggin here also
	  hmpg.clickOnSigninLink();
	  lpg.login(prop.getProperty("username"), prop.getProperty("pwd"));
  }

  @AfterClass
  public void afterClass() {
	  lggpg.loguot();
	  driver.close();
  }

}
