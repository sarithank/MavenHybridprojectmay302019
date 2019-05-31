package com.qa.linkedin.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.base.TestBase;

public class LinkedinLoginPage extends TestBase{

	public LinkedinLoginPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);//whenever you call this class this constructor will initialize these methods
		//class     . static()    driver  current page
	}

	@FindBy(id="username")
	WebElement username_editbox;
	
	@FindBy(name="session_password")
	WebElement password_editbox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	WebElement signin_btn;
	
	//we have get this page title
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	
	//login page username password parameter sending
		public LinkedinLogedInPage login(String username,String pwd) throws IOException {
			username_editbox.clear();
			username_editbox.sendKeys(username);
			password_editbox.clear();
			password_editbox.sendKeys(pwd);
			signin_btn.click();
			//this page returning logedin page. so need to write return new class()
	         return new LinkedinLogedInPage();//we need create class for this
	}
	

}
//respective methods and elements identyfied   in page classes
