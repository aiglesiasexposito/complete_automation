package com.selenium.test.pages.promotionsPage;

import com.selenium.framework.base.SeleniumBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Scope("cucumber-glue")
public class PromotionPage extends SeleniumBase {


    @FindBy(css = "#restaurantindexhelp > div.promotionboxc a > h4")
    public static List<WebElement> restaurant_index_help;

    @FindBy(css = "#mainc > div.maincontent > div.restaurantcover > h1")
    public static WebElement name_restaurant;

    @FindBy(css = "div[class*='filtersection']")
    public static WebElement city_section;

    @FindBy(xpath = "(.//div[@class='linkslist'])")
    public static WebElement link_list_location;

    public PromotionPage(WebDriver driver) {
        super(driver);
    }


}