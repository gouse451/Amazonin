package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Amazon_GetMaxPricedMobile {
    static WebDriver driver;

    public static void main(String[] args) {

        String brandName = ("Samsung");

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");


        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        driver.get("https://amazon.in");


        driver.findElement(By.linkText("Mobiles")).click();

        WebElement brandCheckbox =  driver.findElement(By.xpath("//span[text()='Brands']/../following-sibling::ul//a//span[text()='"+brandName+"']/preceding-sibling::div//input"));


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", brandCheckbox);


        double maxPrice = 0;
        do {

            List<WebElement> allElems = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
            for (WebElement priceElem: allElems) {
                String productPrice = priceElem.getText();
                productPrice = 	productPrice.replace(",", "");


                double dblPrice = Double.parseDouble(productPrice)  ;

                if (dblPrice > maxPrice) {
                    maxPrice = dblPrice;
                }
            }

            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[text()='Next']")));

            driver.findElements(By.xpath("//a[text()='Next']")).get(0).click();


        } while (driver.findElements(By.xpath("//a[text()='Next']")).size() > 0);


        System.out.println(maxPrice);

    }

}
