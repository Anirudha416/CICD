package standalone.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import standalone.AbstractComponents.AbstractComponent;

//import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ProductCatalogue extends AbstractComponent{
 WebDriver driver;
  public ProductCatalogue(WebDriver driver) {
	  super(driver);
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
  
  @FindBy(css=".mb-3")
  List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
  By addToCart =  By.cssSelector(".card-body button:last-of-type");
  By toast = By.cssSelector("#toast-container");
  By productsBy = By.cssSelector(".mb-3");
  
  public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
  
  public WebElement getProductByName(String productName){
  	WebElement prod = getProductList().stream().filter(product->
  	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
  	return prod;
 }
  
  public void addProductToCard(String productName) throws InterruptedException {
  	WebElement prod = getProductByName(productName);
  	prod.findElement(addToCart).click();
  	waitForElementToAppear(toast);
	waitForElementToDisAppear(spinner);
  }
  

}

