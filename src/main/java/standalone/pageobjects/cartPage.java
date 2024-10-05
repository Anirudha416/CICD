package standalone.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import standalone.pageobjects.CheckotPage;
import standalone.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent{
	
WebDriver driver;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	public cartPage(WebDriver driver) {
		super(driver);
	 	this.driver=driver;
    	PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	public Boolean VerifyProductDisplaying(String productName) {
    	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
    	return match;
    }
    public CheckotPage goToCheckout() {
    	checkoutEle.click();
    	return new CheckotPage(driver); 
    }

	
}
