package swatikagalkaracademy.Tests;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import swatikagalkaracademy.TestComponents.BaseTest;
import swatikagalkaracademy.pageObjects.CartPage;
import swatikagalkaracademy.pageObjects.CheckoutPage;
import swatikagalkaracademy.pageObjects.ConfirmationPage;
import swatikagalkaracademy.pageObjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest
{
	String productName="QWERTY";
	@Test(groups={"ErrorHandling"})
	public void submitOrder()
	{
		
		landingPage.loginApplication("anshika@gmail.com", "Iamkig@000");
		AssertJUnit.assertEquals("Incorrect email or password", landingPage.getErrorMessage());
	}
	
	@Test
	public void productValidations()
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		
	}
	
}
