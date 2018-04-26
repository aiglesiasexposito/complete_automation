package com.selenium.test.pages.menuPage;

import org.openqa.selenium.By;

import java.util.Arrays;

public enum SubMenuTypeEnum {

    HOME(By.cssSelector("a[href*='/sport']")),
    FOOTBALL(By.cssSelector("a[href*='/sport/football']")),
    FORMULA1(By.cssSelector("a[href*='/sport/formula1']")),
    CRICKET(By.cssSelector("a[href*='/sport/cricket']")),
    RUGBY(By.cssSelector("a[href*='/sport/rugby-union']")),
    TENNIS(By.cssSelector("a[href*='/sport/tennis']")),
    GOLF(By.cssSelector("a[href*='/sport/golf']")),
    ATHLETICS(By.cssSelector("a[href*='/sport/athletics']")),
    CYCLING(By.cssSelector("a[href*='/sport/cycling']"));

    private By bySelector;

    SubMenuTypeEnum(final By bySelector){
        this.bySelector = bySelector;
    }

    public By getBySelector() {
        return this.bySelector;
    }

    public static SubMenuTypeEnum getSubMenuTypeEnum(String name){
        return Arrays.stream(SubMenuTypeEnum.values()).filter(subMenuTypeEnum -> (
                subMenuTypeEnum.name().equals(name))).findFirst().get();
    }

}
