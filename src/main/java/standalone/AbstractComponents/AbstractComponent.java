package standalone.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import standalone.pageobjects.OrderPage;
import standalone.pageobjects.cartPage;

//import rahulshettyacademy.pageobjects.CartPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOf(findBy));	
    }
	
	
	public cartPage gotoCartPage() {
		cartHeader.click();
		cartPage CartPage = new cartPage(driver);
		return CartPage;
	}
	
	public OrderPage gotoOrderPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	

	
	public void waitForElementToDisAppear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
