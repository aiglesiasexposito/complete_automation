#1.	Run the test from console

By console
You just need to run a single command from inside the project directory from
terminal. 

mvn clean install -pl web_test -Ddevice.web=<name>

Where <name>: 
if “NO DEVICE” -> will run by default with Chrome.
if “iPad” -> will run as iPad in Chrome.

Example: 
mvn clean install -pl web_test -Ddevice.name=iPad

Some parameters: "device.name" -> Select the device iPad, iPad Pro. By default will be NO DEVICE, so we will launch browser google chrome.
"browser.name" -> Select the browser, by default we will launch google chrome.
"browser.height" -> by default 900.
"browser.width" -> by default 1400.

#2.	Report Test
You just need to go inside your project directory. 
/target/cucumber-html-reports


Running Your Tests

mvn clean install inside the root path will run all the test modules with default settings. After cd to submodule, you can only run that module with mvn clean install You can also mvn clean install -f web_test/pom.xml to run specific submodule

If you like to override default run settings you can just run it with

#This will run your test on iPhone 6 simulator
mvn clean install -Dspring.profiles.active="ios" -Ddevice.name="Iphone 6" -Dapp.file="myiosapp.ipa"
By default cucumber will execute every scenario except ones with @ignore

#This will run scenarios tagged with @accounts and @p1
mvn clean -D"cucumber.options=--tags @accounts --tags @p1" install
#This will run scenarios tagged with @accounts or @p1
mvn clean -D"cucumber.options=--tags @accounts,@p1" install


#This will run your account test on android device named xxxxx with default imlicit wait 10 seconds
mvn clean -D"cucumber.options=--tags @accounts" -Ddevice.name="xxxxxx" -Dimplicit.wait="10"
See Property class in framework for more parameter


Appium

Your app package should be com.appium.test to spring can be able to autowire your classes

Selenium

Your app package should be com.appium.test to spring can be able to autowire your classes
                              
Tips

Locating Elements

While using PageFactory and AppiumFieldDecorator define and locate elements. It will decorate your element according to the platform in the test.

@AndroidFindBy(id = "checkbox_button")
@iOSFindBy(id = "check_button_id_in_your_ios_app")
public MobileElement CHECKBOX_BUTTON;
    
#If your element is not native. You must use @FindBy to locate it.
#But for sure you need to change context to interact with it
#For easy change you can use 'alertDialogsPage.getHelper().switchContextToFirstWEBVIEW();'
@FindBy(id = "web_view_id")
public MobileElement CHECKBOX_BUTTON;
While locating element with findElement

MobileElement element = alertDialogsPage.getDriver().findElement(By.xpath("//xpath']"));
Implicit and Explicit Waits

A implicit wait is to tell Driver to poll for a certain amount of time when trying to find an element or elements if they are not immediately available. Once set, the implicit wait is set for the life of the Web Driver object instance until it changed again.

A explicit wait is the code you define to wait for a certain condition to occur before proceeding further in the code. WebDriverWait in combination with ExpectedCondition is the most common way this can be accomplished.

If (given implicit time >= explicit time)
    explicit time is ignored and driver will wait till implicit wait value
if (given explicit time >= implicit time)
    max time taken to find the element (or condition) will vary between implicit wait value and the sum of implicit wait and explicit wait
Setting high implicit wait is blindly telling Selenium, if you don't find my element, then wait for a certain time until. No matter what it is, no matter the consequence, no matter what happened, you wait until you either find it or wait duration has passed. So try to keep your implicit wait value as low as possible (better set to default value 1) and use explicit wait where wait needed

Controlling Implicit Wait
While using PageObject driver.manage.timeouts does not have any effect. Each element holds its' own implicit timeout and its assigned while decorating the elements

So If you need to change your implicit wait time you can do

alertDialogsPage.getHelper().initElementsWithFieldDecorator(AlertDialogsPage.class, 10);
You can set it back to default value any time with below code

alertDialogsPage.getHelper().initElementsWithFieldDecorator(AlertDialogsPage.class);
If you want to override implicit wait time for elements that located with findElement() you can do

alertDialogsPage.getHelper().setDriverWaitTime(10);
Controlling Explicit Wait
Explicit wait is same for elements for both findElement() and PageObject

new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(By.xpath("//xpath")));
Override Both Implicit and Explicit Wait
Annotation @WithTimeout will override every wait and wait for that element until exact that value

@AndroidFindBy(id = "checkbox_button")
@iOSFindBy(id = "check_button_id_in_your_ios_app")
@WithTimeout(time = 1, unit = TimeUnit.SECONDS)
public MobileElement CHECKBOX_BUTTON;