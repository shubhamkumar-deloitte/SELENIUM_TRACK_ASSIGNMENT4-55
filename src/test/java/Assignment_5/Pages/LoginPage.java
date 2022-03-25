package Assignment_5.Pages;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginPage {

    WebDriver driver;
    By userName=By.xpath("//*[@id='user-name']");
    By password= By.xpath("//*[@id='password']");
    By loginButton=By.xpath("//*[@id='login-button']");
    By checkoutFirstName=By.xpath("//*[@id='first-name']");
    By checkoutLastName=By.xpath("//*[@id='last-name']");
    By checkoutPostalCode=By.xpath("//*[@id='postal-code']");
    String UserName;
    String loginPassword;
    String Firstname;
    String LastName;
    double postalCode;

    //constructor
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    //entering username and password
    public  void readFromEXcel() throws IOException {
        WebElement userNameElement= driver.findElement(userName);

        String excelFilePath="C:\\Users\\shubhamkumar32\\Desktop\\New Microsoft Excel Worksheet.xlsx";

        FileInputStream fis=new FileInputStream(excelFilePath);
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        XSSFSheet sheet= workbook.getSheetAt(0);

        XSSFRow row=null;
        XSSFCell cell=null;
        // String username=null;
        //String password=null;

        for(int i=0;i<sheet.getLastRowNum();i++){
            row= sheet.getRow(i);
            for (int j=0;j<row.getLastCellNum();j++){
                cell= row.getCell(j);
                if(j==0){
                    UserName=cell.getStringCellValue();
                }
                if(j==1){
                    loginPassword=cell.getStringCellValue();
                }
            }
        }


    }
    public void readCheckOutDetailsFromexcel() throws IOException {
        System.out.println("reading data fro excel");
        String excelFilePath= "C:\\Users\\shubhamkumar32\\Desktop\\CheckOutDetails.xlsx";
        FileInputStream fis=new FileInputStream(excelFilePath);
        XSSFWorkbook workbook=new XSSFWorkbook(fis);

        XSSFSheet sheet=workbook.getSheetAt(0);
        XSSFRow row=null;
        XSSFCell cell=null;

        for (int i=1;i<sheet.getLastRowNum();i++){
            row= sheet.getRow(i);
            for(int j=0;j<row.getLastCellNum();j++){
                cell= row.getCell(j);
                if(j==0){
                    Firstname=cell.getStringCellValue();
                }
                if(j==1){
                    LastName=cell.getStringCellValue();
                }
                if(j==2){
                    postalCode= cell.getNumericCellValue();
                }
            }
        }
        System.out.println("fistname is "+ Firstname);
        System.out.println("lastname is "+LastName);

    }

    public void  eneterFirstName(){

        WebElement FirstNameElement=driver.findElement(checkoutFirstName);
        FirstNameElement.sendKeys(Firstname);

    }
    public void  eneterLastName(){

        WebElement LastNameElement=driver.findElement(checkoutLastName);
        LastNameElement.sendKeys(Firstname);

    }
    public void  eneterPostalCode(){

        WebElement PostalCodeElement=driver.findElement(checkoutPostalCode);
        //String s=String.valueOf(d);
        String code=String.valueOf(postalCode);
        PostalCodeElement.sendKeys(code);

    }

    public void enterUserName(){
        WebElement userNameElement=driver.findElement(userName);
        userNameElement.sendKeys(UserName);
    }
    public  void enterpassword(){
        WebElement passwordElement= driver.findElement(password);
        passwordElement.sendKeys(loginPassword);
    }



    public void clickLogin(){
        WebElement loginElement=driver.findElement(loginButton);
        loginElement.click();
    }
}
