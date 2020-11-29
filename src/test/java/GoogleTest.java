import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTest {
    private WebDriver driver;
    private static final String searchWord = "automation";

    @BeforeMethod
    public void setup() {
        String baseUrl = "https://www.google.com.ua";

        System.out.println("BeforeMethod is started!");

        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();

        WebElement searchField = driver.findElement(By.cssSelector("input[class=\"gLFyf gsfi\"]"));
        searchField.clear();
        searchField.sendKeys(searchWord);

        WebElement enterButton = driver.findElement(By.cssSelector("input[class=\"gNO89b\"]"));
        enterButton.click();

    }

    @Test(priority = 1)
    public void searchAndOpenFirstLink() {
        String firstLinkXpath = "//*[@id=\"rso\"]/div/div/div";
        System.out.println( "searchAndOpenFirstLink test is started...");

        WebElement firstLink  = driver.findElements(By.xpath(firstLinkXpath)).get(0).findElement(By.tagName("a"));
        System.out.println("firstLink = " + firstLink.getText());
        firstLink.click();

        String actualTitle = driver.getTitle();
        System.out.println("actualTitle = " + actualTitle);

        Assert.assertTrue(actualTitle.toLowerCase().contains(searchWord));

        System.out.println( "searchAndOpenFirstLink test is finished...");

    }

    @AfterMethod
    public void teardown() {
        driver.quit();

    }


}
