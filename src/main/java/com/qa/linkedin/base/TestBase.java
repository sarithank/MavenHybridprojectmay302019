package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.linkedin.util.ListenerHelper;
import com.qa.linkedin.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	
	public static WebDriver driver=null;
	protected static WebDriverWait wait=null;
	public static Properties prop=null;
	
	//create constructor to use properties file  or read the properties file which is created globally
	public TestBase() throws IOException {
		//create an object for properties file
		
		prop=new Properties();
		
		//read the config.properties file
		try {
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/linkedin/config/config.properties");
			//load all the properties
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//initialize driver/browser/url
	//@SuppressWarnings("deprecation")
	public static void initWebdriver() throws IOException {
	//first we need to find out which browser i want to write(need to get the browsername)
	//from	config.properties we need get the "browser" name
		//properties object we need to use to get any properties file
		//because one time only we create properties object
		//fetch the broser name
		String browserName=prop.getProperty("browser");{//we are giving key value from config.propflie
		if (browserName.equalsIgnoreCase("firefox")) {
			//here no need to give System.property(path).we can use
			WebDriverManager.firefoxdriver().setup();//firefoxdriver is a static method
			driver=new FirefoxDriver();	
		}else if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("safari")) {
			//WebDriverManager.safaridriver().setup();
			driver=new SafariDriver();
		}
		//add implicite wait->this is one kind of handling the ajax calls and wait times
		//globaly adding not adding in the testcases. it saves memory also
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		//maximize the window
		driver.manage().window().maximize();
		//open the url
		driver.get(prop.getProperty("url"));
		
		//create object for WebdriverWait
		wait=new WebDriverWait(driver,TestUtil.EXPLICIT_WAIT);//used static variable eg:classname.static variable
			
		//create object for listener helper class
		ListenerHelper lh=new ListenerHelper();
		
		//create object for eventfiringWebdriver
		EventFiringWebDriver event=new 	EventFiringWebDriver(driver); 
		
		//register the events
		event.register(lh);
		
		//assign the event to driver
		//automatically every element you are finding will shown in the console
		driver=event;
		
		
		
		}
	}
	
}
