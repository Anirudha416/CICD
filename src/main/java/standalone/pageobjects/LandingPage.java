package standalone.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import rahulshettyacademy.pageobjects.ProductCatalogue;
import standalone.AbstractComponents.AbstractComponent;

//import rahulshettyacademy.pageobjects.ProductCatalogue;

public class LandingPage extends AbstractComponent{
 WebDriver driver;
  public LandingPage(WebDriver driver) {
	  super(driver);
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
  @FindBy(id="userEmail")
  WebElement userEmail;
  
  @FindBy(id="userPassword")
	WebElement passwordEle;
  
  @FindBy(id="login")
	WebElement submit;
  
  @FindBy(css="[class*='flyInOut']")
 	WebElement errorMessage;
 
  public ProductCatalogue loginApplication(String email,String password) {
  	userEmail.sendKeys(email);
  	passwordEle.sendKeys(password);
  	submit.click();
  	ProductCatalogue prodCatalogue = new ProductCatalogue(driver); 
    return prodCatalogue;
  }
  
  public String getErrorMessage() {
	  waitForWebElementToAppear(errorMessage);
	  return errorMessage.getText();
  }
  
  public void goTo() {
	  driver.get("https://rahulshettyacademy.com/client/");
  }
}

