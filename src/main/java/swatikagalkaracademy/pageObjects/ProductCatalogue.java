package swatikagalkaracademy.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductCatalogue extends AbstractComponent
{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By productsBy=By.cssSelector(".mb-3");
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
		
	By addToCart=(By.xpath("//div[@class='card-body']/button[2]"));
	
	By toastMessage=By.cssSelector("#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName)
	{
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
