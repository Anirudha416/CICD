package standalone;
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

import standalone.pageobjects.LandingPage;
public class xpath{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
         WebDriver driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         driver.get("https://rahulshettyacademy.com/client/");
         LandingPage landingPage = new LandingPage(driver);
         
         driver.manage().window().maximize();
         driver.findElement(By.id("userEmail")).sendKeys("abcxyz@gmail.com");
         driver.findElement(By.id("userPassword")).sendKeys("Anirudha12@");
         driver.findElement(By.id("login")).click();
         
         
     	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
    	
    	WebElement prod = products.stream().filter(product->
    	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
    	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

    	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
    	Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
    	Assert.assertTrue(match);
    	driver.findElement(By.cssSelector(".totalRow button")).click();
    	Actions a = new Actions(driver);
    	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("window.scrollBy(0,700)", ",");
    	driver.findElement(By.cssSelector(".action__submit")).click();
    	
    	String confirmmessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
    	Assert.assertTrue(confirmmessage.equalsIgnoreCase("Thankyou for the order."));

    	
}

}
