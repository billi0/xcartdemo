package utilies;

import basetest.BaseTest0;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utility extends BaseTest0 {
    public void doClickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }


    public void doFindElementAndClearText(By by){
        WebElement loginlink = driver.findElement(by);
        loginlink.clear();
    }



    public String doGetTextFromElement(By by) {
        return driver.findElement(by).getText();

    }




    public void doSendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);//
    }


    public void doSwitchToAlert() {
        driver.switchTo().alert();

    }


    public void doAcceptAlert() {
        driver.switchTo().alert().accept();

    }


    public String doGetTextFromAlert() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void doSendTextToAlert(String textToSend) {
        driver.switchTo().alert().sendKeys(textToSend);
    }


    public void doSelectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }


    public void doSelectByValue(By by, String value) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }


    public void doSelectByIndex(By by, int index) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByIndex(index);

    }


    public void doSelectGetOptionsAndPrint(By by) {
        Select optionsSelect = new Select(driver.findElement(by));
        List<WebElement> optionNames = optionsSelect.getOptions();
        for (int i = 0; i < optionNames.size(); i++) {
            System.out.println(optionNames.get(i).getText());
        }
    }


    public void doDragAndDrop(By source, By target){
        Actions builder = new Actions(driver);
        WebElement draggable = driver.findElement(source);
        WebElement droppable = draggable.findElement(target);
        builder.dragAndDrop(draggable, droppable).build().perform();
    }


    public void doMouseHoverAndClick(By by){
        Actions hover = new Actions(driver);
        WebElement a = driver.findElement(by);
        hover.moveToElement(a).click().build().perform();
    }

    public void doMouseHoverNoClick(By by){
        Actions hover = new Actions(driver);
        WebElement a = driver.findElement(by);
        hover.moveToElement(a).build().perform();

    }

    public void doMouseHoverOnFirstThenSecondAndClick(By by1, By by2){
        Actions hover = new Actions(driver);
        WebElement destination1 = driver.findElement(by1);
        WebElement destination2 = driver.findElement(by2);
        hover.moveToElement(destination1).moveToElement(destination2).click().build().perform();

    }


    public void doRightClick(By by){
        Actions rightClick = new Actions(driver);
        WebElement a = driver.findElement(by);
        rightClick.contextClick().build().perform();
    }


    public void doSliderMovement(By sliderBar, By sliderBox, int xAxis, int yAxis ){
        Actions moveSlider = new Actions(driver);
        WebElement mainSlider = driver.findElement(sliderBar);
        WebElement slider = driver.findElement(sliderBox);
        moveSlider.dragAndDropBy(slider, xAxis,yAxis).build().perform();

    }

    public String doRandomEmailGenerator(){
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder email = new StringBuilder();
        Random randomEmail= new Random();
        while (email.length()<10) {
            int index = (int) (randomEmail.nextFloat() * chars.length());
            email.append(chars.charAt(index));
        }
        String saltStr = (email.toString()+"@gmail.com");
        return saltStr;
    }

    public void doVerifyElements(String expectedMessage, By by, String displayMessage ){
        String actualMessage = doGetTextFromElement(by);
        Assert.assertEquals(displayMessage, expectedMessage, actualMessage);
    }

    public void doSortDataFromElements(By by){
        List<WebElement> name= driver.findElements(by);
        String[]beforesort= new String[name.size()];
        for(int i= 0;i < name.size();i++){
            beforesort[i] = name.get(i).getText().trim();
        }
        Arrays.sort(beforesort);
        WebElement sort = driver.findElement(by);
        sort.click();
        name = driver.findElements(by);
        String[]aftersort = new String[name.size()];

        for(int i = 0; i< name.size(); i++){
            aftersort[i] = name.get(i).getText().trim();
            Assert.assertArrayEquals("productname is not sorted",beforesort,aftersort);

        }

    }
}
