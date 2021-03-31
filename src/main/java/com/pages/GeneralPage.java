package com.pages;

import net.serenitybdd.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GeneralPage extends PageObject {
    public WebElement getElementWithValueAttribute(String value) {
        return getDriver().findElement(By.xpath("//*[@value='" + value + "']"));
    }
}
