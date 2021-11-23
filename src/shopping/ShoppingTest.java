package shopping;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilies.Utility;

public class ShoppingTest extends Utility {
    String baseUrl="https://mobile.x-cart.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }

    @Test
    public void verifyThatUserShouldPlaceOrderSuccessfullyForCupOfMojoBluetoothSpeaker () throws InterruptedException {

        doMouseHoverOnFirstThenSecondAndClick(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/li[2]"), By.xpath("(//span[contains(text(),'Sale')])[2]"));
        doVerifyElements("Sale", By.xpath("//h1[@id='page-title']"), "Sale page not displayed correctly");
        doMouseHoverNoClick(By.xpath("//span[contains(text(),'Sort by:')]"));
        doClickOnElement(By.partialLinkText("Name A -"));
        doMouseHoverNoClick(By.xpath("//a[@class='product-thumbnail next-previous-assigned']"));
        Thread.sleep(1000);
        doClickOnElement(By.xpath("//button[contains(@class,'regular-button add-to-cart product-add2cart productid-16')]//span[contains(text(),'Add to cart')]"));
        doVerifyElements("Product has been added to your cart", By.xpath("//li[@class='info']"), "Product has been added to your cart is not displayed");
        doClickOnElement(By.cssSelector("a[title='Close']"));
        doClickOnElement(By.xpath("//div[@title='Your cart']"));
        Thread.sleep(1000);
        doClickOnElement(By.xpath("//span[normalize-space()='View cart']"));
        doVerifyElements("Your shopping cart - 1 item", By.xpath("//h1[@id='page-title']"), "Shopping cart is not displayed correctly");


        Thread.sleep(500);
        doFindElementAndClearText(By.xpath("//input[@id='amount16']"));
        driver.findElement(By.xpath("//input[@id='amount16' and @ name='amount']")).sendKeys("2");
        Thread.sleep(1000);
        doVerifyElements("Your shopping cart - 2 items", By.xpath("//h1[@id='page-title']"), "Shopping cart is not displayed correctly");
        doVerifyElements("$29.98", By.xpath("//ul[@class='sums']//span[@class='surcharge-cell']"), "Subtotal is not displayed correctly");
        doVerifyElements("$36.00", By.xpath("//li[@class='total']//span[@class='surcharge']"), "Total is incorrect");
        doClickOnElement(By.xpath("//button[contains(@class,'regular-button regular-main-button checkout')]"));
        Thread.sleep(500);
        doVerifyElements("Log in to your account", By.xpath("//h3[contains(text(),'Log in to your account')]"), "Log in to your account message is not displayed correctly");
        doSendTextToElement(By.cssSelector("#email"), doRandomEmailGenerator());
        doClickOnElement(By.xpath("//button[contains(@class,'regular-button anonymous-continue-button submit')]"));
        doVerifyElements("Secure Checkout", By.cssSelector(".title"), "Secure Checkout Message is displayed incorrectly");
        doSendTextToElement(By.id("shippingaddress-firstname"), "Paul");
        doSendTextToElement(By.id("shippingaddress-lastname"), "Smith");
        doSendTextToElement(By.id("shippingaddress-street"), "1000 Abc Street");
        doSendTextToElement(By.id("shippingaddress-custom-state"), "England");
        doClickOnElement(By.id("create_profile"));
        doSendTextToElement(By.id("password"), "secret123");
        doClickOnElement(By.id("method128"));
        Thread.sleep(300);
        doClickOnElement(By.id("pmethod6"));
        doVerifyElements("$37.03", By.xpath("//div[@class='total clearfix']//span[@class='surcharge-cell']"), "Total is displayed incorrectly");
        doClickOnElement(By.cssSelector("button[class='btn regular-button regular-main-button place-order submit']"));
        Thread.sleep(1000);
        doVerifyElements("Thank you for your order", By.cssSelector("#page-title"), "Thank you for your order displayed incorrectly");

    }

    @Test
    public void verifyThatUserShouldClearShoppingCartSuccessfully() throws InterruptedException {

        doMouseHoverNoClick(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/li[2]"));
        doMouseHoverNoClick(By.xpath("//li[@class='leaf has-sub']//li[2]/a[1]/span"));
        doClickOnElement(By.xpath("//li[@class='leaf has-sub']//li[2]/a[1]/span"));
        Thread.sleep(300);
        doVerifyElements("Bestsellers", By.xpath("//h1[@id='page-title']"), "Bestsellers is not displayed correctly");
        doMouseHoverNoClick(By.xpath("//span[contains(text(),'Sort by:')]"));
        doClickOnElement(By.partialLinkText("Name A -"));
        doMouseHoverNoClick(By.cssSelector(" .product.productid-13"));
        Thread.sleep(300);
        doClickOnElement(By.xpath("//button[@class='btn  regular-button add-to-cart product-add2cart productid-13']/span[1]"));
        doVerifyElements("Product has been added to your cart", By.xpath("//li[contains(text(),'Product has been added to your cart')]"), "Product added - displayed incorrectly");
        Thread.sleep(300);
        doClickOnElement(By.xpath("//body/div[@id='mm-0']/div[@id='page-wrapper']/div[@id='page']/div[1]/div[1]/div[1]/a[1]"));
        doClickOnElement(By.xpath("//div[@title='Your cart']"));
        doClickOnElement(By.xpath("//span[contains(text(),'View cart')]"));
        doVerifyElements("Your shopping cart - 1 item", By.xpath("//h1[@id='page-title']"), "1 Product is not add into the cart");
        Thread.sleep(300);
        doClickOnElement(By.xpath("//a[contains(text(),'Empty your cart')]"));
        String alert = doGetTextFromAlert();
        String expectedAlert = "Are you sure you want to clear your cart?";
        Assert.assertEquals("Alert Message is incorrect", expectedAlert, alert);
        Thread.sleep(300);
        doAcceptAlert();
        Thread.sleep(1000);
        doVerifyElements("Item(s) deleted from your cart", By.xpath("//li[contains(text(),'Item(s) deleted from your cart')]"), "item is not deleted");
        doVerifyElements("Your cart is empty", By.xpath("//h1[normalize-space()='Your cart is empty']"), "your cart is not empty");
    }

    public void tearDown(){
        closeBrowser();
    }

}
