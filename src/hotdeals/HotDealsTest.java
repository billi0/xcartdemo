package hotdeals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilies.Utility;

public class HotDealsTest extends Utility {
    String baseUrl = "https://mobile.x-cart.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifySaleProductsArrangeAlphabetically() throws InterruptedException {

        doMouseHoverOnFirstThenSecondAndClick(By.xpath("//ul[@class='nav navbar-nav top-main-menu']//span[text()='Hot deals']"), By.xpath("(//span[contains(text(),'Sale')])[2]"));
        doVerifyElements("Sale", By.xpath("//h1[@id='page-title']"), "Sale message is not displayed");
        doMouseHoverNoClick(By.xpath("//span[contains(text(),'Recommended')]"));
        Thread.sleep(400);
        doSortDataFromElements(By.partialLinkText("Name A"));
    }

    @Test
    public void verifySaleProductsPriceArrangeLowToHigh() throws InterruptedException {
        doMouseHoverNoClick(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/li[2]"));
        doMouseHoverNoClick(By.xpath("//li[@class='leaf has-sub']//li[1]//a[1]"));
        doClickOnElement(By.xpath("//li[@class='leaf has-sub']//li[1]//a[1]"));
        String expectedMessage1 = "Sale";
        String actualMessage1 = doGetTextFromElement(By.xpath("//h1[@id='page-title']"));
        Assert.assertEquals("User has not navigated to Sale Page + ", expectedMessage1, actualMessage1);
        doMouseHoverNoClick(By.xpath("//span[contains(text(),'Recommended')]"));
        doSortDataFromElements(By.partialLinkText("Price Low - Hi"));
    }

    @Test
    public void verifySaleProductsArrangeByRates() throws InterruptedException {
        doMouseHoverNoClick(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/li[2]"));
        Thread.sleep(300);
        doMouseHoverNoClick(By.xpath("//li[@class='leaf has-sub']//li[1]//a[1]"));
        Thread.sleep(400);
        doClickOnElement(By.xpath("//li[@class='leaf has-sub']//li[1]//a[1]"));
        String expectedMessage1 = "Sale";
        String actualMessage1 = doGetTextFromElement(By.xpath("//h1[@id='page-title']"));
        Assert.assertEquals("User has not navigated to Sale Page + ", expectedMessage1, actualMessage1);
        doMouseHoverNoClick(By.xpath("//span[contains(text(),'Recommended')]"));
        doSortDataFromElements(By.partialLinkText("Rates"));


    }

    @Test
    public void verifyBestSellersProductsArrangeByZToA() throws InterruptedException {

        doMouseHoverNoClick(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/li[2]"));
        doMouseHoverNoClick(By.xpath("//li[@class='leaf has-sub']//li[2]/a[1]/span"));
        doClickOnElement(By.xpath("//li[@class='leaf has-sub']//li[2]/a[1]/span"));
        String expectedMessage2 = "Bestsellers";
        String actualMessage2 = doGetTextFromElement(By.xpath("//h1[@id='page-title']"));
        Assert.assertEquals("User is not on BestSellers Page + ", expectedMessage2, actualMessage2);
        doMouseHoverNoClick(By.xpath("//span[contains(text(),'Sort by:')]"));
        Thread.sleep(500);
        doSortDataFromElements(By.partialLinkText("Name Z -"));
    }

    @Test
    public void verifyBestSellersProductsPriceArrangeHighToLow() throws InterruptedException {
        doMouseHoverNoClick(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/li[2]"));
        Thread.sleep(300);
        doMouseHoverNoClick(By.xpath("//li[@class='leaf has-sub']//li[2]/a[1]/span"));
        doClickOnElement(By.xpath("//li[@class='leaf has-sub']//li[2]/a[1]/span"));
        String expectedMessage3 = "Bestsellers";
        String actualMessage3 = doGetTextFromElement(By.xpath("//h1[@id='page-title']"));
        Assert.assertEquals("User is not on BestSellers Page + ", expectedMessage3, actualMessage3);
        doMouseHoverNoClick(By.xpath("//span[contains(text(),'Sort by:')]"));
        Thread.sleep(300);
        doSortDataFromElements(By.partialLinkText("Price High - L"));

    }

    @Test
    public void verifyBestSellersProductsArrangeByRates() throws InterruptedException {

        doMouseHoverNoClick(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/li[2]"));
        Thread.sleep(300);
        doMouseHoverNoClick(By.xpath("//li[@class='leaf has-sub']//li[2]/a[1]/span"));
        doClickOnElement(By.xpath("//li[@class='leaf has-sub']//li[2]/a[1]/span"));
        Thread.sleep(300);
        String expectedMessage4 = "Bestsellers";
        String actualMessage4 = doGetTextFromElement(By.xpath("//h1[@id='page-title']"));
        Assert.assertEquals("User is not on BestSellers Page + ", expectedMessage4, actualMessage4);
        Thread.sleep(300);
        doMouseHoverNoClick(By.xpath("//span[contains(text(),'Sort by:')]"));
        Thread.sleep(300);
        doSortDataFromElements(By.partialLinkText("Price"));
    }

    public void tearDown(){
        closeBrowser();
    }
}
