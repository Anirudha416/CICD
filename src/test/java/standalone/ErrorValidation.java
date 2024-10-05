package standalone;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sun.net.httpserver.Authenticator.Retry;

import standalone.Testcomponent.BaseTest;
//import standalone.Testcomponent.Retry;
import standalone.pageobjects.CheckotPage;
import standalone.pageobjects.ConfirmationPage;
import standalone.pageobjects.LandingPage;
import standalone.pageobjects.ProductCatalogue;
import standalone.pageobjects.cartPage;
public class ErrorValidation extends BaseTest{

	//@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";

		
		
		//LandingPage landingPage = launchApplication();
        landingPage.loginApplication("abc@gmail.com", "Anirudha12@");
        
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    	
}
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		//LandingPage landingPage = launchApplication();
        ProductCatalogue prodCatalogue =landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
     //	ProductCatalogue prodCatalogue = new ProductCatalogue(driver); 
     	List<WebElement>products = prodCatalogue.getProductList();
     	prodCatalogue.addProductToCard(productName);
     	cartPage CartPage = prodCatalogue.gotoCartPage();
     	
     	Boolean match=CartPage.VerifyProductDisplaying(productName);
        Assert.assertTrue(match);
//        CheckotPage checkoutPage = CartPage.goToCheckout();
//        checkoutPage.selectCountry("india");
//        Thread.sleep(3000);
//        ConfirmationPage confirmationPage= checkoutPage.submitOrder();
//        String confirmmessage = confirmationPage.getConfirmationMessage();
//    	Assert.assertTrue(confirmmessage.equalsIgnoreCase("Thankyou for the order."));

    	
}

}
