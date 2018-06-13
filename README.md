# Environment configuration

Apache Maven 3.5.2  
IntelliJ IDEA 2017.3.4 (Community Edition)  
JRE: 1.8.0_152-release-1024-b11 x86_64  
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o  
Mac OS X 10.13.3  
Appium 1.6.1  


# Running your tests

`mvn clean install` inside the root path will run all the test modules with default settings. 

# Running your specific tests

## Web module

`mvn clean install -pl web_test` inside the root path, will run specific submodule.

`mvn clean install -pl web_test -Ddevice.name=iPad -Dbrowser.name=chrome` inside the root path, will run specific submodule using web mobile with chrome.

**NOTE:** You can see the pom.xml configuration from web_test module.

## Mobile module

`mvn clean install -Dspring.profiles.active="ios" -Ddevice.name="Iphone 6" -Dapp.file="myiosapp.ipa"`
By default cucumber will execute every scenario except ones with @ignore.

This will run scenarios tagged with @accounts and @p1  
`mvn clean -D"cucumber.options=--tags @accounts --tags @p1" install`  
This will run scenarios tagged with @accounts or @p1  
`mvn clean -D"cucumber.options=--tags @accounts,@p1" install`  

This will run your account test on android device named xxxxx with default imlicit wait 10 seconds
`mvn clean -D"cucumber.options=--tags @accounts" -Ddevice.name="xxxxxx" -Dimplicit.wait="10"`

**NOTE:** See Property class in framework for more parameter.

# Reporting

After the test execution you can find generated HTML report in /target/cucumber-html-reports folder
Project Structure

# Tips

## Locating Elements

While using PageFactory and AppiumFieldDecorator define and locate elements. It will decorate your element according to the platform in the test.
```java
@AndroidFindBy(id = "checkbox_button")  
@iOSFindBy(id = "check_button_id_in_your_ios_app")  
public MobileElement CHECKBOX_BUTTON;  
``` 
If your element is not native. You must use @FindBy to locate it.  
But for sure you need to change context to interact with it.  
For easy change you can use `alertDialogsPage.getHelper().switchContextToFirstWEBVIEW();`  
```java
@FindBy(id = "web_view_id")
public MobileElement CHECKBOX_BUTTON;
```
While locating element with findElement:
```java
MobileElement element = alertDialogsPage.getDriver().findElement(By.xpath("//xpath']"));  
```
**Implicit and Explicit Waits**

A **implicit wait** is to tell Driver to poll for a certain amount of time when trying to find an element or elements if they are not immediately available. Once set, the implicit wait is set for the life of the Web Driver object instance until it changed again.  

A **explicit wait** is the code you define to wait for a certain condition to occur before proceeding further in the code. WebDriverWait in combination with ExpectedCondition is the most common way this can be accomplished.  
```java
If (given implicit time >= explicit time)  
    explicit time is ignored and driver will wait till implicit wait value  
if (given explicit time >= implicit time)  
    max time taken to find the element (or condition) will vary between implicit wait value and the sum of implicit wait and explicit wait  
```    
Setting high implicit wait is blindly telling Selenium, if you don't find my element, then wait for a certain time until. No matter what it is, no matter the consequence, no matter what happened, you wait until you either find it or wait duration has passed. So try to keep your implicit wait value as low as possible (better set to default value 1) and use explicit wait where wait needed

**Controlling Implicit Wait**  
While using PageObject driver.manage.timeouts does not have any effect. Each element holds its' own implicit timeout and its assigned while decorating the elements

So If you need to change your implicit wait time you can do  
`alertDialogsPage.getHelper().initElementsWithFieldDecorator(AlertDialogsPage.class, 10);` 

You can set it back to default value any time with below code  
`alertDialogsPage.getHelper().initElementsWithFieldDecorator(AlertDialogsPage.class);` 

If you want to override implicit wait time for elements that located with findElement() you can do  
`alertDialogsPage.getHelper().setDriverWaitTime(10);`  

**Controlling Explicit Wait**  
Explicit wait is same for elements for both findElement() and PageObject
```java
new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));  
new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(By.xpath("//xpath")));  
```
**Override Both Implicit and Explicit Wait**  
Annotation @WithTimeout will override every wait and wait for that element until exact that value
```java
@AndroidFindBy(id = "checkbox_button")  
@iOSFindBy(id = "check_button_id_in_your_ios_app")  
@WithTimeout(time = 1, unit = TimeUnit.SECONDS)  
public MobileElement CHECKBOX_BUTTON;  
```

# Configuration pom.xml Web module 
```
<configuration>
    <systemPropertyVariables>
        <spring.profiles.active>Selenium</spring.profiles.active>
        <platform.name>Selenium</platform.name>
        <device.name>NO DEVICE</device.name>
        <browser.name>Chrome</browser.name>
        <base.url>https://www.bbc.com</base.url>
        <grid.use>true</grid.use>
        <grid.url>localhost:32802</grid.url>
    </systemPropertyVariables>
    <testFailureIgnore>true</testFailureIgnore>
    <workingDirectory>${basedir}</workingDirectory>
</configuration>
```
**device.name - NO DEVICE** with **browser.name - Chrome**  
If you select iPad, iPad Pro, Nexus 5... on device.name you will run the test on chrome simulating a mobile device. Only abailable for chrome browser.  

**browser.name - Chrome**  
If you select firefox, chrome, opera... you will run the test in an specific browser.  

**base.url - https://www.bbc.com**  
URL that you want to test.  

**grid.use - true** with **grid.url - localhost:32802**  
If you want to launch your test in your local machine you have to put a `false` grid.use  

## Configuration pom.xml Mobile module 
```
<configuration>
    <systemPropertyVariables>
        <spring.profiles.active>Android</spring.profiles.active>
        <platform.name>Android</platform.name>
        <device.name>emulator-5554</device.name>
        <app.file>demo.apk</app.file>
        <grid.use>false</grid.use>
    </systemPropertyVariables>
    <testFailureIgnore>true</testFailureIgnore>
    <workingDirectory>${basedir}</workingDirectory>
</configuration>
```
**spring.profiles.active - Android**  
Select iOS or Android  

**platform.name - Android**  
Select iOS or Android  

**device.name - emulator-5554**  
Include your device name using ADB for Android, you can use real devices or emulators with Android Studio for example.  
For iOS yoy have to find with XCode you emulator. 

**app.file - demo.apk**  
Name of your .app or .apk  
