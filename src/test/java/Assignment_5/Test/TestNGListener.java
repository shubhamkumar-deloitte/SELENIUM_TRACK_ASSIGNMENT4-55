package Assignment_5.Test;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestNGListener  implements ITestListener {
    WebDriver driver;

    @Override
    public void onTestStart(ITestResult Result) {
        System.out.println("Test Cases started "+Result.getName());

    }

    @Override
    public void onTestSuccess(ITestResult Result) {

        System.out.println("Test performed successfully "+ Result.getName());
    }

    @Override
    public void onTestFailure(ITestResult Result) {

        System.out.println("Test failed"+Result.getName());
        //taking screenshot
        try {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //The below method will save the screen shot in destination directory with name "screenshot.png"
            FileHandler.copy(scrFile, new File(System.getProperty("user.dir")+"/src/"+"sample1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult Result) {
        System.out.println("Test skipped"+Result.getName());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }


    @Override
    public void onStart(ITestContext result) {

        System.out.println("Test has started" +result.getName());

    }

    @Override
    public void onFinish(ITestContext result) {
        System.out.println("test has finished its execution "+ result.getName());
    }
}
