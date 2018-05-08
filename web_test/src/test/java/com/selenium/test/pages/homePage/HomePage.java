package com.selenium.test.pages.homePage;

import com.selenium.framework.base.SeleniumBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class HomePage extends SeleniumBase {

    @FindBy(css = "a[href*='/en/promotions']")
    public static WebElement menu_promotions;

    @FindBy(xpath = "(.//div[@class='linkslist'])")
    public static WebElement link_list_location;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
