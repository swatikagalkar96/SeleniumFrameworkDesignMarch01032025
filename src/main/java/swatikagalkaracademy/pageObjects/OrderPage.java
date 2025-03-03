package swatikagalkaracademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponent
{
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match=productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
}
