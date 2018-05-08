package com.selenium.test.pages.bussinesSteps;


import com.selenium.framework.base.SeleniumBase;
import com.selenium.test.pages.promotionsPage.CitiesEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class CityPromotionsSectionBS extends SeleniumBase {

    public void accessToCity(CitiesEnum citiesMenu) {

        WebElement section = getDriver().findElement(citiesMenu.getBySelector());

        new WebDriverWait(getDriver(), 5, 500).until(ExpectedConditions.elementToBeClickable(section)).click();
    }

    public CityPromotionsSectionBS(WebDriver driver) {
        super(driver);
    }

}






