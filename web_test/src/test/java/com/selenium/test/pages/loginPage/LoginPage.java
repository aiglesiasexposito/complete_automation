package com.selenium.test.pages.loginPage;

import com.selenium.framework.base.SeleniumBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class LoginPage extends SeleniumBase {

    @FindBy(css = "button.radius")
    public static WebElement LOGIN_BUTTON;
    @FindBy(id = "continue")
    public static WebElement continue_button;
    @FindBy(id = "nav-link-accountList")
    public static WebElement sign_in_button;
    @FindBy(id = "twotabsearchtextbox")
    public static WebElement search_text_box;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
