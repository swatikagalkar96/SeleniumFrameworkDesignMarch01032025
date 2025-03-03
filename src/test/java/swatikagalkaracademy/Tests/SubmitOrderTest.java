package swatikagalkaracademy.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import swatikagalkaracademy.TestComponents.BaseTest;
import swatikagalkaracademy.pageObjects.CartPage;
import swatikagalkaracademy.pageObjects.CheckoutPage;
import swatikagalkaracademy.pageObjects.ConfirmationPage;
import swatikagalkaracademy.pageObjects.LandingPage;
import swatikagalkaracademy.pageObjects.OrderPage;
import swatikagalkaracademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest
{
	String productName="QWERTY";
	@Test(dataProvider= "getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String>input)
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage=confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderPage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"anshika@gmail.com","Iamking@000","QWERTY"},{"anshika@gmail.com","Iamking@000","QWERTY"}};
	}*/
	
	/*@DataProvider
	public Object[][] getData()
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("email", "anshika@gmail.com");
		map.put("password", "Iamking@000");
		map.put("product","QWERTY");
		
		Map<String,String> map1=new HashMap<String,String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product","QWERTY");
		return new Object[][] {{map},{map1}};
	}*/
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List <HashMap<String,String>> data = getJsonDataToMap
				(System.getProperty("user.dir")+"//src//test//java//swatikagalkaracademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
