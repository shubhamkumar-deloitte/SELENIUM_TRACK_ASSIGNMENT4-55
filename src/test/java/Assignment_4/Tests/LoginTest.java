package Tests;

import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginTest {

    WebDriver driver;
    String url="https://www.saucedemo.com/";
    LoginPage loginPage;
    By cartIcon=By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    By ExpensivecartItem= By.xpath("//*[@id='add-to-cart-sauce-labs-fleece-jacket']");
    By Expensiveaddedtocart=By.xpath("//*[@id='remove-sauce-labs-fleece-jacket']");
    By checkOutBtn=By.xpath("//*[@id='checkout']");
    By checkoutFirstName=By.xpath("//*[@id='first-name']");
    By CheckoutLastName=By.xpath("//*[@id='last-name']");
    By checkoutPostalCode=By.xpath("//*[@id='postal-code']");
    By continueAfterCheckout=By.xpath("//*[@id='continue']");
    By finishAfterCheckout=By.xpath("//*[@id='finish']");

    @BeforeClass
    public void setUp() throws IOException {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\shubhamkumar32\\Downloads\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

    }

    @Test(priority = 1)
    public void validateLogin() throws InterruptedException, IOException {
        String currentUrl=driver.getCurrentUrl();
        loginPage =new LoginPage(driver);
        loginPage.readFromEXcel();
        loginPage.enterUserName();
        loginPage.enterpassword();

        Thread.sleep(1000);
        loginPage.clickLogin();
        Thread.sleep(2000);
        String HomePageUrl= driver.getCurrentUrl();
        if(currentUrl.equals(HomePageUrl)){
            Assert.assertTrue(false);
        }
        Thread.sleep(2000);

    }
    @Test(priority = 2)
    public void addToCart() throws InterruptedException {

        WebElement addToCartButton= driver.findElement(ExpensivecartItem);

        addToCartButton.click();


        WebElement removeElement=driver.findElement(Expensiveaddedtocart);
       String Text=driver.findElement(Expensiveaddedtocart).getText();
      System.out.println(Text);
      if(Text.equals("REMOVE")){
          System.out.println("add to cart button enabled");
      }
      Thread.sleep(1000);
      //clicking on remove
        removeElement.click();


//clicking ion add to cart again

        WebElement addTocartAgain=driver.findElement(ExpensivecartItem);
        addTocartAgain.click();
    }
    @Test(priority = 3)
    public void clickOnCart() throws InterruptedException {
       // By cartIcon=By.xpath("//*[@id=\"shopping_cart_container\"]/a");
        WebElement cartIconElement=driver.findElement(cartIcon);
        cartIconElement.click();
        Thread.sleep(1000);

    }
    @Test(priority = 4)
    public void ContinueShopping() throws InterruptedException {
        By continueShopping=By.xpath("//*[@id='continue-shopping']");
        WebElement continueShoppingElement= driver.findElement(continueShopping);
        continueShoppingElement.click();
        Thread.sleep(1000);
    }

    @Test(priority = 5)
    public void leastExpensiveToCart() throws InterruptedException {

        By leastExpensiveItem=By.xpath("//*[@id='add-to-cart-sauce-labs-onesie']");
        WebElement leastExpensiveElement=driver.findElement(leastExpensiveItem);
        leastExpensiveElement.click();
        Thread.sleep(2000);
    }
    @Test(priority = 6)
    public void verifyNoOfCartItem(){

        String number=driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        System.out.println(number);
        if (number.equals("2")){
            System.out.println("Cart item increased successfully");
        }else
        {
            driver.navigate().refresh();
        }

    }
    @Test(priority = 7)
    public void verifyTheItemsInTehcart(){
        //click on cart icon
        WebElement CartELement=driver.findElement(cartIcon);
        CartELement.click();

        List<WebElement>listTotal=driver.findElements(By.xpath("//div[@class='cart_item']"));
        //System.out.println("total items in the cart is" +listTotal.size());
        if (listTotal.size()==2){
            Assert.assertTrue(true);
        }else
        {
            Assert.assertTrue(false);
        }

    }
    @Test(priority = 8)
    public void clickCheckout() throws InterruptedException {
        WebElement checkoutElement= driver.findElement(checkOutBtn);
        checkoutElement.click();
        Thread.sleep(1000);
    }

    @Test(priority = 9)
    public void checkOutDetails() throws IOException, InterruptedException {
        loginPage=new LoginPage(driver);
        loginPage.readCheckOutDetailsFromexcel();
       loginPage.eneterFirstName();
       loginPage.eneterLastName();
       loginPage.eneterPostalCode();
        Thread.sleep(2000);
       WebElement continueAfterCheckoutElement= driver.findElement(continueAfterCheckout);
       continueAfterCheckoutElement.click();

    }
    @Test(priority = 10)
    public void verifyTotalAmount(){


    }

    @Test(priority = 11)
    public void clickOnFinish() throws InterruptedException {
        Thread.sleep(2000);

        WebElement finishAfterCheckoutElement=driver.findElement(finishAfterCheckout);
        finishAfterCheckoutElement.click();
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
