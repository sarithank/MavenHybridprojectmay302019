package com.qa.linkedin.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.linkedin.base.TestBase;

public class LinkedinLogedInPage extends TestBase {

	public LinkedinLogedInPage() throws IOException {
		super();
		
		PageFactory.initElements(driver,this );
		
	}

	@FindBy(xpath="//div[contains(@class,'profile-rail-card')]")
	WebElement profile_rail_card;
	
	@FindBy(xpath="//*[contains(@class,'nav-item__profile-member-photo nav-item__icon')]")
	WebElement profileimage;
	
	@FindBy(xpath="//*[contains(@data-control-name,'nav.settings_signout')]")
	WebElement signout_link;
	
	@FindBy(xpath="//*[@role='combobox' and @placeholder='Search']")
	WebElement search_editbox;
	
	@FindBy(xpath="//*[contains(@data-control-name,'nav.search_button')]")
	WebElement search_torch_icon;
	
	
	public boolean verifyprofileCard() {
		wait.until(ExpectedConditions.visibilityOf(profile_rail_card));
		 return profile_rail_card.isDisplayed();
		 
	}
	
	public void loguot() {
		profileimage.click();
		wait.until(ExpectedConditions.visibilityOf(signout_link));
		signout_link.click();
			
	}
	
	public SearchResultsPage searchPeople(String peopleKeyword) throws InterruptedException, IOException {
		search_editbox.clear();
		search_editbox.sendKeys(peopleKeyword);
		search_torch_icon.click();
		Thread.sleep(3000);
		return new SearchResultsPage();
		
		
	}
	
}
