package com.AML.Listeners;

import static io.qameta.allure.model.Status.BROKEN;
import static io.qameta.allure.model.Status.FAILED;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.AML.Common.BaseClass;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;

public class AllureTestLifecycleListener implements TestLifecycleListener {

    public AllureTestLifecycleListener() {
    }

    private static String getTestMethodName(TestResult result) {
        return result.getName();
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void beforeTestStop(TestResult result) {
//        if (FAILED == result.getStatus() || BROKEN == result.getStatus()) {
            if (BaseClass.getDriver() != null) {
                saveScreenshot(BaseClass.getDriver());
                saveTextLog(getTestMethodName(result) + " failed");
            }
//        }
    }
}