package Assignment_5.Test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener  implements ITestListener {

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
