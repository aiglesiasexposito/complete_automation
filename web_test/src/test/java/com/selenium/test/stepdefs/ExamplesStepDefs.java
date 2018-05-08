package com.selenium.test.stepdefs;

import com.selenium.test.pages.loginPage.LoginPage;
import com.selenium.test.pages.bussinesSteps.CityPromotionsSectionBS;
import com.selenium.test.pages.homePage.HomePage;
import com.selenium.test.pages.promotionsPage.CitiesEnum;
import com.selenium.test.pages.promotionsPage.PromotionPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.selenium.test.pages.loginPage.LoginPage.*;

public class ExamplesStepDefs {

    @Autowired
    public LoginPage loginPage;
    @Autowired
    public HomePage hp;
    @Autowired
    public CityPromotionsSectionBS cpsbs;
    @Autowired
    public PromotionPage pp;


    //HUNGER STATION EXAMPLE

    @Given("^I am a invited user in the platform$")
    public void iAmAInvitedUserInThePlatform(){
        hp.getURL("hungerstation.com/en");
        hp.assertContainText(hp.link_list_location,"Select Your City");
    }

    @When("^I go to top offers section$")
    public void iGoToTopOffersSection(){
        hp.menu_promotions.click();
    }

    @Then("^I can to read the first top offers from the city selected$")
    public void iCanToReadTheFirstTopOffersFromTheCitySelected(Map<String, String> tableData){
        cpsbs.accessToCity(CitiesEnum.getCityEnum(tableData.get("city")));
        String restaurant = pp.restaurant_index_help.get(0).getText();
        pp.restaurant_index_help.get(0).click();
        pp.assertContainText(pp.name_restaurant,restaurant);
    }
}
