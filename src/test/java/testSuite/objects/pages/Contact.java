package testSuite.objects.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact extends CommonPage {
    final public String momentOfBirth;
    final By messageCharCount = By.id("letterCount");
    @FindBy(id = "resultDisplay")
    WebElement sendingResultsWindow;
    @FindBy(id = "name")
    WebElement nameField;
    @FindBy(id = "emailAddress")
    WebElement addressField1;
    @FindBy(id = "emailAddressConf")
    WebElement addressField2;
    @FindBy(id = "subject")
    WebElement subjectField;
    @FindBy(id = "yourMessage")
    WebElement messageField;
    @FindBy(name = "sendMsg")
    WebElement sendButton;
    @FindBy(tagName = "form")
    WebElement form;

    public Contact(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(myDriver, this);
        momentOfBirth = getTimestamp();
    }

    /**
     * Add a tab character to the given string - this will invoke any field validation
     *
     * @param string - what to modify
     * @return - the modified inut
     */
    private String addTab(String string) {
        return "" + string + Keys.TAB;
    }

    public String getTimestamp() {
        // find the value right now, don't depend on the page factory
        // this always seems slow, maybe because of the JavaScript on the page, I don't know
        return myDriver.findElement(By.id("stamp")).getAttribute("textContent");
    }

    public String getNameField() { return nameField.getAttribute("value"); }

    public void setNameField(String nameField) {
        if (nameField != null) this.nameField.sendKeys(addTab(nameField));
    }

    public String getAddressField1() { return addressField1.getAttribute("value"); }

    public void setAddressField1(String addressField1) {
        if (addressField1 != null) this.addressField1.sendKeys(addTab(addressField1));
    }

    public String getAddressField2() { return addressField2.getAttribute("value"); }

    public void setAddressField2(String addressField2) {
        if (addressField2 != null) this.addressField2.sendKeys(addTab(addressField2));
    }

    public String getSubjectField() { return subjectField.getAttribute("value"); }

    public void setSubjectField(String subjectField) {
        if (subjectField != null) this.subjectField.sendKeys(addTab(subjectField));
    }

    public String getMessageField() { return messageField.getAttribute("value"); }

    public void setMessageField(String messageField) {
        if (messageField != null) this.messageField.sendKeys(addTab(messageField));
    }

    public int getMessageCharCount() {
        return Integer.parseInt(myDriver.findElement(messageCharCount).getAttribute("innerHTML"));
    }

    public String getSendingResultsMessage() { return sendingResultsWindow.getText();}

    public boolean sendingResultSignifiesSuccess() {
        return sendingResultsWindow.getAttribute("class").toLowerCase().contains("text-success");
    }

    public boolean sendingResultsMessageIsVisible() {
        return sendingResultsWindow.isDisplayed();
    }

    public void sendMessage() { sendButton.click();}

    public boolean theFormIsVisible() {
        try {
            return form.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
