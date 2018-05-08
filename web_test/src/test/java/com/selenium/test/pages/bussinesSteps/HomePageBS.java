package com.selenium.test.pages.bussinesSteps;

import com.selenium.framework.base.SeleniumBase;
import com.selenium.test.pages.homePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("cucumber-glue")
public class HomePageBS extends SeleniumBase {

    @Autowired
    public HomePage hp;

    public void doLogin() {


        }

    public HomePageBS(WebDriver driver) {
        super(driver);
    }

}