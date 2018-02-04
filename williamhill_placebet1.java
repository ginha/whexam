package williamhill_bet1;



import static org.junit.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;



public class williamhill_placebet1 {
	
	public static void main(String[] args) throws InterruptedException{
		
		 System.setProperty("webdriver.chrome.driver", "C:/Users/nomad/Documents/selenium files/chromedriver_win32/chromedriver.exe");
		 WebDriver driver1 = new ChromeDriver();
		 WebDriverWait waitTime = new WebDriverWait(driver1, 15);
		 String williamH = "http://sports.williamhill.com/bet/en-gb";
		 String strBet = "10.5";
		 
		 driver1.get(williamH);
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("popupMain")));
		 System.out.println("Main page loaded. Selecting territory.");
		 
		 //territory select -> main page where the horse racing link can be selected
		 driver1.findElement(By.id("yesBtn")).click();
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("ip_type_0")));
		 System.out.println("Territory selected. Finding Horse element to click on");
		 
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("horse_racing")));
		 driver1.findElement(By.id("horse_racing")).click();
		 System.out.println("Horse racing link clicked. Waiting for Horse Racing page to load.");
		 
		 //Horse racing page
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("md_oddsButton")));
		 driver1.findElement(By.className("md_oddsButton")).click();
		 System.out.println("Horse selected. Comparing horse names.");
		 
		 //compare the horse's name between the list of available horses and the bet slip
		 //for this run, we'll be getting the name of the horse at the upper leftmost section of the table
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("betSlip")));
		 String horseNameTable = driver1.findElement(By.xpath("/html/body/div[3]/div[7]/div[2]/div/div/table/tbody/tr[2]/td[2]")).getText();
		 System.out.println("Horse name in table is "+horseNameTable);
		
		 //split the horse's name 
		 String horseNameSlip = driver1.findElement(By.className("slipName")).getText();
		 String convertString = horseNameSlip;
		 String[] partition = convertString.split(" @");
		 String horseNameSplit = partition[0];
		 System.out.println("Horse name in bet slip is "+horseNameSplit);
		 
		 //assert if the names are the same
		 if(horseNameSlip.contains(horseNameTable))
			 System.out.println("Horse names are consistent.");
		 else
			 System.out.println("Horse names are inconsistent.");
		 
		 //horseIDNum is the unique horse ID number that changes every few minutes in placement or value on the page.
		 //slipStake is the accompanying classname that contains the unique horse ID number.
		 driver1.findElement(By.className("slipStake")).click();
		 WebElement horseIDNum = driver1.findElement(By.className("slipStake"));
		 String horseID = horseIDNum.getAttribute("id");
		 System.out.println("Horse ID is "+horseID);
 
		 //for this section, selenium was having issues sending keys so i had to use Actions instead
		 Actions actions1 = new Actions(driver1);
		 actions1.moveToElement(horseIDNum);
		 actions1.click();
		 actions1.sendKeys(strBet);
		 actions1.build().perform();
		 
		 //extracting the value from "Total Stake" to compare to the value entered previously if they are equal
		 String totalStake = driver1.findElement(By.id("slipMainTotalStake")).getText();
		 System.out.println("Total stake element contains "+totalStake);
		 assertTrue(totalStake.contains(strBet));
		 
		 System.out.println("If the assertion was false, the script would not have reached this"
		 		+ " line. Bet amount entered. Placing bet.");
		 driver1.findElement(By.id("slipBtnPlaceBet")).click();
		 
		 System.out.println("Bet placed. Loading login page.");
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit_button")));
		 System.out.println("Login page loaded. Terminating script.");
		 
		 
		 
		 
		 driver1.close();
	     System.exit(0);
		 
		 
		
	}

}
