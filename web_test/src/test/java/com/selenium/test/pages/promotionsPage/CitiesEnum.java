package com.selenium.test.pages.promotionsPage;

import org.openqa.selenium.By;

import java.util.Arrays;

public enum CitiesEnum {

    AR_RASS(By.cssSelector("a[href*='/en/promotions/ar-rass']")),
    SHAQRA(By.cssSelector("a[href*='/en/promotions/shaqra']")),
    AL_NAMAS(By.cssSelector("a[href*='/en/promotions/al-namas']"));

    private By bySelector;

    CitiesEnum(final By bySelector){
        this.bySelector = bySelector;
    }

    public By getBySelector() {
        return this.bySelector;
    }

    public static CitiesEnum getCityEnum(String name){
        return Arrays.stream(CitiesEnum.values()).filter(cityEnum -> (
                cityEnum.name().equals(name))).findFirst().get();
    }
}
