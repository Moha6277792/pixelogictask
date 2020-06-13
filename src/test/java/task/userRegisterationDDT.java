package task;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import validation.userInfoValidation;

public class userRegisterationDDT {


	ChromeDriver driver;
	@DataProvider (name="UserData")
	public Object[][] userregistData () throws IOException {

		ExcelReader ER = new ExcelReader();
		return ER.getExcelData();
	}

	@Test (dataProvider="UserData")
	public void usersignupProcess(String firstname,String lastname, String mobilenumber, String email, String password ) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver.exe" );
		driver = new ChromeDriver();
		driver.navigate().to("https://www.phptravels.net/register");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// Insert user information
		WebElement namefirst = driver.findElement(By.cssSelector("input[name='firstname']")); 
		namefirst.sendKeys(firstname);
		WebElement namelast = driver.findElement(By.cssSelector("input[name='lastname']")); 
		namelast.sendKeys(lastname);
		boolean firstnamefirstletter = Character.isUpperCase(firstname.charAt(0));
		boolean lastnamelastletter = Character.isUpperCase(lastname.charAt(0));
		assertTrue(firstnamefirstletter & lastnamelastletter , "Make sure first name and last name start with capital letter");
		WebElement numbermobile = driver.findElement(By.cssSelector("input[name='phone']"));
		numbermobile.sendKeys(mobilenumber);
		userInfoValidation mobnum = new userInfoValidation();
		assertTrue(mobnum.isNumeric(mobilenumber), "Make sure that you entering numbers as mobile number");

		WebElement addressmail = driver.findElement(By.cssSelector("input[name='email']")); 
		addressmail.sendKeys(email);
		userInfoValidation e_mail = new userInfoValidation();
		assertTrue(e_mail.validEmail(email), "Make sure you follow the mail format 'xxxx@xxxxx.xxx' ");

		WebElement userpassword = driver.findElement(By.cssSelector("input[name='password']")); 
		userpassword.sendKeys(password);
		WebElement confirmpassword = driver.findElement(By.cssSelector("input[name='confirmpassword']")); 
		confirmpassword.sendKeys(password);
		userInfoValidation pass_word = new userInfoValidation();
		assertTrue(pass_word.validPassword(password), "Make sure that password must have capital letter, small letter, with a limit of 8 characters");

		WebElement submit = driver.findElement(By.cssSelector("button.signupbtn.btn_full.btn.btn-success.btn-block.btn-lg")); 
		submit.click();
		Thread.sleep(4000);
		String tittle = driver.getTitle();
		String pageTittle = "My Account";
		if (tittle.equals(pageTittle)) {
			WebElement accountdropdown = driver.findElement(By.cssSelector("div.dropdown.dropdown-login.dropdown-tab")); 
			accountdropdown.click();
			Thread.sleep(1000);
			WebElement logout = driver.findElement(By.xpath("//*[@class='dropdown-item tr'][text()='Logout']"));
			logout.click();
			Thread.sleep(4000);
			WebElement loginmail = driver.findElement(By.cssSelector("input[name='username']")); 
			loginmail.sendKeys(email);
			WebElement loginpassword = driver.findElement(By.cssSelector("input[name='password']")); 
			loginpassword.sendKeys(password);
			WebElement signin = driver.findElement(By.cssSelector("button.btn.btn-primary.btn-lg.btn-block.loginbtn")); 
			signin.click();
			System.out.println("Your account is successfully registed");
		}
		else {
			WebElement signupresult = driver.findElement(By.cssSelector("div.resultsignup"));
			System.out.println(signupresult.getText());
			assertTrue(false, "Try to sign up with another mail");
		}
		driver.close();
	}


}
