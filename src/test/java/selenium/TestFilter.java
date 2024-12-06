package selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestFilter {
    private WebDriver driver;

    private void click(By by) {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }


    @Test
    public void filteringByColor_Edge() throws InterruptedException {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        driver = new EdgeDriver(options);
        driver.get("https://www.cos.com/en_sek/index.html");
        click(By.cssSelector("#oPage > div.header.parbase > div.o-lightbox.is-newsletter-popup.is-open.is-subscribe > div > button"));
        click(By.id("onetrust-reject-all-handler"));
        Thread.sleep(3000);
        click(By.cssSelector("a[href='/en_sek/women.html']"));
        Thread.sleep(3000);
        click(By.cssSelector("div > a:nth-child(2)[href='https://www.cos.com/en_sek/women/knitwear.html']"));
        click(By.cssSelector("div > button.open-filter-button.a-button-nostyle > svg"));
        click(By.cssSelector("#toggle-colorWithNames"));
        click(By.cssSelector("#section-colorWithNames > div > ul > li:nth-child(11) > div > div > div > span > svg"));
        click(By.cssSelector(".ProductFilter-module--filterButtonContainer__1SuTe >button:nth-child(2)"));
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.cos.com/en_sek/women/knitwear.html?colorWithNames=ff0000_red", url);
        driver.quit();
    }

    @Test
    public void filteringByColor_Chrome() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.cos.com/en_sek/index.html");
        click(By.cssSelector("#oPage > div.header.parbase > div.o-lightbox.is-newsletter-popup.is-open.is-subscribe > div > button"));
        click(By.id("onetrust-reject-all-handler"));
        Thread.sleep(3000);
        click(By.cssSelector("a[href='/en_sek/women.html']"));
        Thread.sleep(3000);
        click(By.cssSelector("div > a:nth-child(2)[href='https://www.cos.com/en_sek/women/knitwear.html']"));
        click(By.cssSelector("div > button.open-filter-button.a-button-nostyle > svg"));
        click(By.cssSelector("#toggle-colorWithNames"));
        click(By.cssSelector("#section-colorWithNames > div > ul > li:nth-child(11) > div > div > div > span > svg"));
        click(By.cssSelector(".ProductFilter-module--filterButtonContainer__1SuTe >button:nth-child(2)"));
        //Get all items and validate their colors
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.cos.com/en_sek/women/knitwear.html?colorWithNames=ff0000_red", url);
        driver.quit();
    }

    @Test
    public void filteringByPrice_Edge() throws InterruptedException {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        driver = new EdgeDriver(options);
        driver.get("https://www.cos.com/en_sek/index.html");
        click(By.cssSelector("#oPage > div.header.parbase > div.o-lightbox.is-newsletter-popup.is-open.is-subscribe > div > button"));
        click(By.id("onetrust-reject-all-handler"));
        Thread.sleep(3000);
        click(By.cssSelector("a[href='/en_sek/women.html']"));
        Thread.sleep(3000);
        click(By.cssSelector("div > a:nth-child(4)[href='https://www.cos.com/en_sek/women/dresses.html']"));
        click(By.cssSelector("div > button.open-filter-button.a-button-nostyle > svg"));
        click(By.cssSelector("#accordionInner > div:nth-child(1)"));
        click(By.cssSelector("#accordionInner > div:nth-child(1) > div > div > ul > li:nth-child(4)"));
        click(By.cssSelector(".ProductFilter-module--filterButtonContainer__1SuTe >button:nth-child(2)"));
        //Get all items and validate the price is in descending order
        List<WebElement> itemPriceList = driver.findElements(By.cssSelector("div.m-product-price"));
        List<Double> extractedPriceValues = new ArrayList<>();
        for (WebElement itemPrice : itemPriceList) {
            String priceText = itemPrice.getText();
            Pattern pattern = Pattern.compile("\\d+,\\d+");
            Matcher matcher = pattern.matcher(priceText);
            if (matcher.find()) {
                String priceNumber = matcher.group();
                priceNumber = priceNumber.replace(",", ".");
                double priceValue = Double.parseDouble(priceNumber);
                extractedPriceValues.add(priceValue);
            } else {
                System.out.println("No match");
            }
        }
        System.out.println(extractedPriceValues.size()); //There are 72 displayed items on the page
        System.out.println(extractedPriceValues);
        for (int i = 0; i < extractedPriceValues.size() - 1; i++) {
            Assert.assertTrue(extractedPriceValues.get(i) >= (extractedPriceValues.get(i + 1)));
                    }

        driver.quit();
    }


    @Test
    public void filteringByPrice_Chrome() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.cos.com/en_sek/index.html");
        click(By.cssSelector("#oPage > div.header.parbase > div.o-lightbox.is-newsletter-popup.is-open.is-subscribe > div > button"));
        click(By.id("onetrust-reject-all-handler"));
        Thread.sleep(3000);
        click(By.cssSelector("a[href='/en_sek/women.html']"));
        Thread.sleep(3000);
        click(By.cssSelector("div > a:nth-child(4)[href='https://www.cos.com/en_sek/women/dresses.html']"));
        click(By.cssSelector("div > button.open-filter-button.a-button-nostyle > svg"));
        click(By.cssSelector("#accordionInner > div:nth-child(1)"));
        click(By.cssSelector("#accordionInner > div:nth-child(1) > div > div > ul > li:nth-child(4)"));
        click(By.cssSelector(".ProductFilter-module--filterButtonContainer__1SuTe >button:nth-child(2)"));
        //Get all items and validate the price is in descending order
        List<WebElement> itemPriceList = driver.findElements(By.cssSelector("div.m-product-price"));
        List<Double> extractedPriceValues = new ArrayList<>();
        for (WebElement itemPrice : itemPriceList) {
            String priceText = itemPrice.getText();
            Pattern pattern = Pattern.compile("\\d+,\\d+");
            Matcher matcher = pattern.matcher(priceText);
            if (matcher.find()) {
                String priceNumber = matcher.group();
                priceNumber = priceNumber.replace(",", ".");
                double priceValue = Double.parseDouble(priceNumber);
                extractedPriceValues.add(priceValue);
            } else {
                System.out.println("No match");
            }
        }
        System.out.println(extractedPriceValues.size()); //There are 72 displayed items on the page
        System.out.println(extractedPriceValues);
        for (int i = 0; i < extractedPriceValues.size() - 1; i++) {
            Assert.assertTrue(extractedPriceValues.get(i) >= (extractedPriceValues.get(i + 1)));
        }

        driver.quit();
    }
}

