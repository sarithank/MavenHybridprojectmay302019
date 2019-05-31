package com.qa.linkedin.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.base.TestBase;



public class LinkedinHomePage extends TestBase{

	public LinkedinHomePage() throws IOException {
		super();
		//now we have write page factory
		//init elements methods  we have to call
		PageFactory.initElements(driver, this); // this keyword initialize this current page
		
	}
	
	//we have to tell page factory here
	
	@FindBy(css="li-icon.nav_logo")
	WebElement Linkedin_logo ;
	
	@FindBy(css="a.nav__button-secondary")
	WebElement  signin_link;
	
	
	//need to write respective methods for this page class
	//verify linkedin logo present or not
	
	public boolean verifyLinkedinLogo() {
		return Linkedin_logo.isDisplayed();//element.isDisplayed().we already identifyed the element above
		
	}
	//verify the landing page title
	public String getLinkedinLandingTitle() {
		return driver.getTitle();
		
	}
	//click on signin link
	public LinkedinLoginPage clickOnSigninLink() throws IOException {
		signin_link.click();
		return new LinkedinLoginPage();
	}
	

}

//respective methods and elements identyfied   in page classes




