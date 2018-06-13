package com.selenium.test.pages.newsPage;

import com.selenium.framework.base.SeleniumBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("cucumber-glue")
public class NewsPage extends SeleniumBase {

    @FindBy(xpath = "(.//span[@class='lakeside__title-text'])")
    public static List<WebElement> news_links;

    @FindBy(css = ".gel-trafalgar-bold")
    public static WebElement title_news;

    public NewsPage(WebDriver driver) {
        super(driver);
    }
}
