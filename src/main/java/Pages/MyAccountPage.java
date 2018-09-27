package Pages;

import Base.BasePage;
import Base.Browser;
import Utilities.Credentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {


    public WebDriver driver ;
    WebDriverWait wait;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver,this);
    }

    @FindBy(how=How.XPATH, using="//a[contains(.,'My Details')]")
    public WebElement btnMyDetails;

    @FindBy(how=How.NAME, using="old_password")
    public WebElement txtOldPassword;

    @FindBy(how=How.NAME, using="new_password")
    public WebElement txtNewPassword;

    @FindBy(how=How.NAME, using="new_password_confirm")
    public WebElement txtRetypePassword;

    @FindBy(how=How.NAME, using="new_password_button")
    public WebElement btnSubmitPassword;

    @FindBy(how=How.CLASS_NAME, using="success-message")
    public WebElement txtPasswordChangeSuccessMessage;

    @FindBy(how=How.ID, using="vs_user_menu_logout_link")
    public WebElement lnkLogout;

    @FindBy(how=How.NAME, using="email")
    public WebElement txtEmail;

    @FindBy(how=How.NAME, using="location")
    public WebElement txtLocation;

    @FindBy(how=How.NAME, using="phone")
    public WebElement txtPhone;

    @FindBy(how=How.CSS, using="#detail_description > textarea")
    public WebElement txtDescription;

    @FindBy(how=How.NAME, using="interests")
    public WebElement txtInterests;

    @FindBy(how=How.NAME, using="url")
    public WebElement txtUrl;

    @FindBy(how=How.XPATH, using="//input[@name='gender'][@value='male']")
    public WebElement chkMaleRadio;

    @FindBy(how=How.XPATH, using="//input[@name='gender'][@value='female']")
    public WebElement chkFemaleRadio;

    @FindBy(how=How.NAME, using="gender_show")
    public WebElement chkShowGender;

    @FindBy(how=How.ID, using="born_day")
    public WebElement txtDOBDay;

    @FindBy(how=How.ID, using="born_month")
    public WebElement txtDOBMonth;

    @FindBy(how=How.NAME, using="date_of_birth_show")
    public WebElement chkShowDOB;

    @FindBy(how=How.NAME, using="repost_optin")
    public WebElement chkEmailPrefRepost;

    @FindBy(how=How.NAME, using="report_optin")
    public WebElement chkEmailPrefReport;

    @FindBy(how=How.NAME, using="product_optin")
    public WebElement chkEmailPrefProduct;

    @FindBy(how=How.NAME, using="partners_optin")
    public WebElement chkEmailPrefPartners;

    @FindBy(how=How.XPATH, using="//input[@type='submit'][@value='Submit']")
    public WebElement btnSubmitMain;

    @FindBy(how=How.NAME, using="company_name")
    public WebElement txtCompanyName;

    @FindBy(how=How.NAME, using="address1")
    public WebElement txtCompanyAddress;

    @FindBy(how=How.NAME, using="location")
    public WebElement txtPostCode;

    @FindBy(how=How.CLASS_NAME, using="ui-menu-item")
    public WebElement btnPostCodeDropdown;

    @FindBy(how=How.NAME, using="telephone")
    public WebElement txtPhoneNumber1;

    @FindBy(how=How.NAME, using="telephone_2")
    public WebElement txtPhoneNumber2;

    @FindBy(how=How.NAME, using="contact_name")
    public WebElement txtContactName;

    @FindBy(how=How.NAME, using="store_name")
    public WebElement txtYourActivity;

    @FindBy(how=How.CSS, using="#detail_description > div.middle > textarea")
    public WebElement txtShopName;

    @FindBy(how = How.XPATH,using = "//div[contains(.,'The old password does not match our record')]")
    public WebElement txtOldPasswordError;

    @FindBy(how = How.CSS,using = "div#detail_email input.kiwii-input.kiwii-span-9.error")
    public WebElement invalidEmailError;

    @FindBy(how = How.CSS,using = ".kiwii-input.kiwii-span-9.ui-autocomplete-input.error")
    public WebElement txtNoLocationError;

    @FindBy(how = How.CSS,using = ".kiwii-input.kiwii-span-9.error")
    public WebElement txtInvalidURL;

    public void changePassword(String OldEmail, String NewEmail) {

        txtOldPassword.sendKeys(OldEmail);
        txtNewPassword.sendKeys(NewEmail);
        txtRetypePassword.sendKeys(NewEmail);
        btnSubmitPassword.click();
        if (!confirmChangePassword()) {
            throw new IllegalStateException("Old password is incorrect");
        }

        wait.until(ExpectedConditions.visibilityOf(txtPasswordChangeSuccessMessage));
    }

    public boolean confirmChangePassword() {
        try {
            if (txtOldPasswordError.isDisplayed()) {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }


    public void changeAccountDetails(String gender) {

        btnMyDetails.click();
        txtLocation.clear();
        changePostCode(Credentials.ValidPostCode1);
        txtPhone.clear();
        txtPhone.sendKeys(Credentials.ValidPhoneNumber1);
        txtDescription.clear();
        txtDescription.sendKeys(Credentials.ValidInformation1);
        txtInterests.clear();
        txtInterests.sendKeys(Credentials.ValidActivites1);
        txtUrl.clear();
        txtUrl.sendKeys(Credentials.ValidWebsite1);
        changeGender(gender, "Public");
        changeDOB("Public");
        changeEmailCheckboxes("Individual", "Yes","Yes", "Yes", "No");
        btnSubmitMain.click();
        Browser.checkPageUrl("account.php", "This is not the my details page");
    }

    public void changeProfessionalAccountDetails() {

        //if (Account.equals(1)) {
        txtCompanyName.clear();
        txtCompanyName.sendKeys(Credentials.ValidCompanyName1);
        txtCompanyAddress.clear();
        txtCompanyAddress.sendKeys(Credentials.ValidCompanyAddress1);
        changePostCode(Credentials.ValidPostCode1);
        txtPhoneNumber1.clear();
        txtPhoneNumber1.sendKeys(Credentials.ValidPhoneNumber1);
        txtPhoneNumber2.clear();
        txtPhoneNumber2.sendKeys(Credentials.ValidPhoneNumberTwo1);
        txtContactName.clear();
        txtContactName.sendKeys(Credentials.Username1);
        txtUrl.clear();
        txtUrl.sendKeys(Credentials.ValidWebsite1);
        txtYourActivity.clear();
        txtYourActivity.sendKeys(Credentials.ValidActivites1);
        txtShopName.clear();
        txtShopName.sendKeys(Credentials.ValidShopName1);
        changeEmailCheckboxes("Professional", "Yes","Yes", "Yes", "Yes");
        /*}
        else if (Account.equals(2)) {
            txtCompanyName.clear();
            txtCompanyName.sendKeys(Credentials.ValidCompanyName2);
            txtCompanyAddress.clear();
            txtCompanyAddress.sendKeys(Credentials.ValidCompanyAddress2);
            changePostCode(Credentials.ValidPostCode2);
            txtPhoneNumber1.clear();
            txtPhoneNumber1.sendKeys(Credentials.ValidPhoneNumber2);
            txtPhoneNumber2.clear();
            txtPhoneNumber2.sendKeys(Credentials.ValidPhoneNumberTwo2);
            txtContactName.clear();
            txtContactName.sendKeys(Credentials.Username2);
            txtUrl.clear();
            txtUrl.sendKeys(Credentials.ValidWebsite2);
            txtYourActivity.clear();
            txtYourActivity.sendKeys(Credentials.ValidActivites2);
            txtShopName.clear();
            txtShopName.sendKeys(Credentials.ValidShopName2);
            changeEmailCheckboxes("Professional", "No","No", "No", "No");
        }*/
        btnSubmitMain.click();
        Browser.checkPageUrl("account.php", "This is not the my details page");
    }

    public void changeDOB(String Public) {

        txtDOBDay.sendKeys(Credentials.DOBDay1);
        txtDOBMonth.sendKeys(Credentials.DOBMonth1);

        if (Public.contains("Public")) {
            if (!chkShowDOB.isSelected()) {
                chkShowDOB.click();
            }
        } else {
            if (chkShowDOB.isSelected()) {
                chkShowDOB.click();
            }
        }
    }

    public void changeGender(String Gender, String Public) {

        if (Gender.contains("Male")) {
            chkMaleRadio.click();
        } else {
            chkFemaleRadio.click();
        }
        if (Public.contains("Public")) {
            if (!chkShowGender.isSelected()) {
                chkShowGender.click();
            }
        }
        else {
            if (chkShowGender.isSelected()) {
                chkShowGender.click();
            }
        }
    }

    public void changeEmailCheckboxes(String Type, String Repost, String Newsletter, String ThirdParties, String Report) {

        if (Repost.contains("Yes")) {
            if (!chkEmailPrefRepost.isSelected()) {
                chkEmailPrefRepost.click();
            }
        }
        else {
            if (chkEmailPrefRepost.isSelected()) {
                chkEmailPrefRepost.click();
            }
        }
        if (Newsletter.contains("Yes")) {
            if (!chkEmailPrefProduct.isSelected()) {
                chkEmailPrefProduct.click();
            }
        }
        else {
            if (chkEmailPrefProduct.isSelected()) {
                chkEmailPrefProduct.click();
            }
        }
        if (ThirdParties.contains("Yes")) {
            if (!chkEmailPrefPartners.isSelected()) {
                chkEmailPrefPartners.click();
            }
        }
        else {
            if (chkEmailPrefPartners.isSelected()) {
                chkEmailPrefPartners.click();
            }
        }
        if (Type.contains("Professional") && Report.contains("Yes")) {
            if (!chkEmailPrefReport.isSelected()) {
                chkEmailPrefReport.click();
            }
        }
        else if (Type.contains("Professional") && Report.contains("No")) {
            if (chkEmailPrefReport.isSelected()) {
                chkEmailPrefReport.click();
            }
        }
    }

    public void changePostCode(String Postcode) {

        txtPostCode.clear();
        txtPostCode.sendKeys(Postcode);
        wait.until(ExpectedConditions.elementToBeClickable(btnPostCodeDropdown));
        btnPostCodeDropdown.click();
    }

    public String getRepost() {

        if (chkEmailPrefRepost.isSelected()) {
            return "Yes";
        }
        else {
            return "No";
        }
    }

    public String getProduct() {

        if (chkEmailPrefRepost.isSelected()) {
            return "Yes";
        }
        else {
            return "No";
        }
    }

    public String getPartners() {

        if (chkEmailPrefRepost.isSelected()) {
            return "Yes";
        }
        else {
            return "No";
        }
    }

    public String getReport() {

        if (chkEmailPrefReport.isSelected()) {
            return "Yes";
        }
        else {
            return "No";
        }
    }

    public void clearIndividualDetailsFields() {

        txtEmail.clear();
        btnSubmitMain.click();
        Browser.checkPageUrl("account.php", "This is not the my details page");
    }

    public void clearProfessionalDetailsFields() {
        txtCompanyName.clear();
        txtCompanyAddress.clear();
        txtPostCode.clear();
        txtEmail.clear();
        btnSubmitMain.click();
        Browser.checkPageUrl("account.php", "This is not the my details page");
    }

    public MyAccountPage clickMyDetails() {
        btnMyDetails.click();
        Browser.checkPageUrl("/account.php", "This is not user details page");
        return new MyAccountPage(driver);
    }

    public void checkEmailField(String expectedEmail) {

        String actualEmail = txtEmail.getAttribute("value");

        if (!expectedEmail.equals(actualEmail)) {
            throw new IllegalStateException("Wrong email in Email field");
        }
    }
}
