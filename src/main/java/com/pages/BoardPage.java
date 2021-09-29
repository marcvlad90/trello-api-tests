package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardPage extends GeneralPage {
    @FindBy(css = ".list-card-composer-textarea")
    private WebElement cardTitleInput;

    public WebElement getListContainer(String name) {
        return getDriver().findElement(By.xpath("//div[contains(@class,'list-wrapper')][div/div/textarea[text()='" + name + "']]"));
    }

    public void createCardInTheList(String listName, String cardName) {
        getListContainer(listName).findElement(By.cssSelector(".icon-add")).click();
        cardTitleInput.sendKeys(cardName);
        getElementWithValueAttribute("Add card").click();
    }

    public WebElement getCardContainer(String listName, String cardName) {
        return getListContainer(listName).findElement(By.xpath("//div[contains(@class,'list-card')][span[text()='" + cardName + "']]"));
    }

    public boolean isCardDisplayed(String listName, String cardName) {
        return getCardContainer(listName, cardName).isDisplayed();
    }
}
