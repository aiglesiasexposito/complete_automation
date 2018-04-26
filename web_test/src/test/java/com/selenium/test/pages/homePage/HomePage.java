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

    @FindBy(id = "idcta-link")
    public static WebElement sign_in_button;

    @FindBy(id = "user-identifier-input")
    public static WebElement user_identifier_field;

    @FindBy(id = "password-input")
    public static WebElement user_password_field;

    @FindBy(id = "submit-button")
    public static WebElement submit_button;


    public HomePage(WebDriver driver) {
        super(driver);
    }


}
