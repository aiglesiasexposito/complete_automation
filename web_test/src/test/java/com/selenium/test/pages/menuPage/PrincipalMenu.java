package com.selenium.test.pages.menuPage;

import com.selenium.framework.base.SeleniumBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class PrincipalMenu extends SeleniumBase {

    @FindBy(css = "a[href*='/sport/']")
    public WebElement sport_menu;

    @FindBy(css = "a[href*='#orb-footer']")
    public WebElement more_menu;

    public PrincipalMenu(WebDriver driver) {
        super(driver);
    }

}