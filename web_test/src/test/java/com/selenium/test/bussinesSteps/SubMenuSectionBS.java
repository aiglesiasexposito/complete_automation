package com.selenium.test.bussinesSteps;


import com.selenium.framework.base.SeleniumBase;
import com.selenium.test.pages.menuPage.SubMenuTypeEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class SubMenuSectionBS extends SeleniumBase {

    public void accessToSection(SubMenuTypeEnum subMenuTypeEnum) {

        WebElement section = getDriver().findElement(subMenuTypeEnum.getBySelector());

        new WebDriverWait(getDriver(), 5, 500).until(ExpectedConditions.elementToBeClickable(section)).click();
    }

    public SubMenuSectionBS(WebDriver driver) {
        super(driver);
    }

}






