package standalone;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import standalone.Testcomponent.BaseTest;
import standalone.pageobjects.CheckotPage;
import standalone.pageobjects.ConfirmationPage;
import standalone.pageobjects.LandingPage;
import standalone.pageobjects.OrderPage;
import standalone.pageobjects.ProductCatalogue;
import standalone.pageobjects.cartPage;
public class submitOrder extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		
		//LandingPage landingPage = launchApplication();
        ProductCatalogue prodCatalogue =landingPage.loginApplication(input.get("email"), input.get("password"));
     //	ProductCatalogue prodCatalogue = new ProductCatalogue(driver); 
     	List<WebElement>products = prodCatalogue.getProductList();
     	prodCatalogue.addProductToCard(input.get("product"));
     	cartPage CartPage = prodCatalogue.gotoCartPage();
     	
     	Boolean match=CartPage.VerifyProductDisplaying(input.get("product"));
        Assert.assertTrue(match);
        CheckotPage checkoutPage = CartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        Thread.sleep(3000);
        ConfirmationPage confirmationPage= checkoutPage.submitOrder();
        String confirmmessage = confirmationPage.getConfirmationMessage();
    	Assert.assertTrue(confirmmessage.equalsIgnoreCase("Thankyou for the order."));

    	
}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void OrderHistory() throws InterruptedException {
		//"ZARA COT - 3 is available"
        ProductCatalogue prodCatalogue =landingPage.loginApplication("abcxyz@gmail.com", "Anirudha12@");
        OrderPage ordersPage = prodCatalogue.gotoOrderPage();
        Thread.sleep(3000);
		Assert.assertTrue(ordersPage.VerifyOrderDisplaying(productName));
		
	}
	

	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "abcxyz@gmail.com");
//		map.put("password", "Anirudha12@");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String,String>> data = getJsonMap(System.getProperty("user.dir")+"//src//test//java//standalone//data//Purchaseorder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}
