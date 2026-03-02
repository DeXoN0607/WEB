package ru.netology.order;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private By nameInput = By.cssSelector("[data-test-id='name'] input");
    private By phoneInput = By.cssSelector("[data-test-id='phone'] input");
    private By agreementCheckbox = By.cssSelector("[data-test-id='agreement']");
    private By submitButton = By.cssSelector("button.button");
    private By successNotification = By.cssSelector("[data-test-id='order-success']");
    private By nameError = By.cssSelector("[data-test-id='name'].input_invalid .input__sub");
    private By phoneError = By.cssSelector("[data-test-id='phone'].input_invalid .input__sub");
    private By agreementError = By.cssSelector("[data-test-id='agreement'].input_invalid");

    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void acceptAgreement() {
        driver.findElement(agreementCheckbox).click();
    }

    public void submit() {
        driver.findElement(submitButton).click();
    }

    public String getSuccessText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification));
        return driver.findElement(successNotification).getText().trim();
    }

    public String getNameErrorText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameError));
        return driver.findElement(nameError).getText().trim();
    }

    public String getPhoneErrorText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneError));
        return driver.findElement(phoneError).getText().trim();
    }

    public boolean isAgreementInvalid() {
        return driver.findElement(agreementError).isDisplayed();
    }
}