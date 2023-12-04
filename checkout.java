package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class checkout {
    public static void main(String[] args) {
        WebDriver driver = chromedriver();
        Point point = new Point(0,0);
        driver.manage().window().setPosition(point);
        Dimension dimension = new Dimension(800, 500);
        driver.manage().window().setSize(dimension);
        AutoLogin(driver);
        if (driver.findElements(By.linkText("Login")).isEmpty()) {
            System.out.println("*****Login Verification-*****");
            System.out.println("Login Successful");
            buttonLocator(driver);
            brokenLinks(driver);
            navigation(driver);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            BillingDetails(driver);
            PaymentMethod(driver);
            driver.manage().window().maximize();
        } else {
            System.err.println("Unable to login- Please try again.");
        }
        driver.quit();
    }

    private static WebDriver chromedriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/sarthakrana/Desktop/IDE/basic/git/src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        return driver;
    }

    private static void AutoLogin(WebDriver driver) {
        driver.findElement(By.id("input-email")).sendKeys("bblabal411@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
    }

    private static void buttonLocator(WebDriver driver) {
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        System.out.println("\n*****Broken Button Verification-*****");
        for (WebElement button : buttons) {
            String getText = button.getText();
            if (!getText.isEmpty()) {
                System.out.println(button.getText() + " Button is Present");
            }
        }
    }
    private static void navigation (WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Qafox.com")).click();
        driver.findElement(By.linkText("MacBook")).click();
        WebElement element = driver.findElement(By.id("input-quantity"));
        element.clear();
        element.sendKeys("3");
        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("\n\n******Product Images Verification*****");
        for(WebElement img: images){
            System.out.println(img.getAttribute("src"));
        }
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.id("cart-total")).click();
        driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]/strong")).click();
    }
    private static void BillingDetails(WebDriver driver) {
        System.out.println("\n\n*****Billing Details Verification-*****");

            By paymentAddressSelector = By.cssSelector("input[name='payment_address']");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if (!driver.findElement(paymentAddressSelector).isDisplayed()) {
                System.out.println("Filling the form-");
                driver.findElement(By.cssSelector("input#input-payment-firstname")).sendKeys("testing");
                driver.findElement(By.cssSelector("input#input-payment-lastname")).sendKeys("automation");
                driver.findElement(By.cssSelector("input#input-payment-company")).sendKeys("xyz");
                driver.findElement(By.cssSelector("input#input-payment-address-1")).sendKeys("address 123 @testing");
                driver.findElement(By.cssSelector("input#input-payment-city")).sendKeys("Ohaio");
                driver.findElement(By.cssSelector("input#input-payment-postcode")).sendKeys("123958");

                Select countrySelect = new Select(driver.findElement(By.id("input-payment-country")));
                countrySelect.selectByVisibleText("India");

                Select stateSelect = new Select(driver.findElement(By.id("input-payment-zone")));
                stateSelect.selectByVisibleText("Uttar Pradesh");

                WebElement opt = countrySelect.getFirstSelectedOption();
                WebElement opt1 = stateSelect.getFirstSelectedOption();

                System.out.println("Selected country: " + opt.getText());
                System.out.println("Selected State: " + opt1.getText());

                driver.findElement(By.id("button-payment-address")).click();
            } else {
                System.out.println("Using an existing address");
                if (!driver.findElement(paymentAddressSelector).isSelected()) {
                    driver.findElement(paymentAddressSelector).click();
                    driver.findElement(By.cssSelector("input#button-payment-address")).click();
                } else {
                    driver.findElement(By.cssSelector("input#button-payment-address")).click();
                }
            }
        }

    private static void PaymentMethod (WebDriver driver){
        System.out.println("\n*****Payment Method Verification*****");
        if (driver.findElement(By.name("agree")).isEnabled()) {
            driver.findElement(By.name("agree")).click();
            System.out.println(driver.findElement(By.name("agree")).getText());
            driver.findElement(By.id("button-payment-method")).click();
            System.out.println("Payment verification done");
        }else{
            System.out.println("error");
        }
    }
    private static void brokenLinks (WebDriver driver){
        HttpURLConnection huc = null;
        System.out.println("\n*****Broken Link Verification-*****");
        List<WebElement> allHyperlinksList = driver.findElements(By.tagName("a"));
        for (WebElement hyperLink : allHyperlinksList) {
            String url = hyperLink.getAttribute("href");

            if (url != null) {
                try {
                    URL actualUrl = new URL(url);
                    huc = (HttpURLConnection) actualUrl.openConnection();
                    huc.setRequestMethod("HEAD");
                    huc.connect();
                    int respCode = huc.getResponseCode();
                    String getText = hyperLink.getText();
                    //if(!getText.isEmpty()){
                        if (respCode != 200) {
                            if(!getText.isEmpty()){
                        System.err.println(getText + " | " + url + " is a BROKEN link\n");
                            System.out.println("\n");
                    } else {
                        System.out.println(getText + " | " + url + " is a VALID link");
                            System.out.println("\n");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("\nHref attribute is null for the link with text: " + hyperLink.getText());
            }
        }
    }
}