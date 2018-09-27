package Pages;

import Base.BasePage;
import Base.Browser;
import Utilities.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdPostPage extends BasePage {


    public WebDriver driver ;
    public WebDriverWait wait;

    public AdPostPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    //----------PageFactory

    //Title
    @FindBy(how=How.ID, using="title")
    public WebElement txtTitle;

    @FindBy(how=How.ID, using="info_bubble_title")
    public static WebElement msgMissingTitle;

    //Description
    @FindBy(how=How.ID, using="detail")
    public WebElement txtDescription;

    // Category
    @FindBy(how=How.CSS, using="select#posting_category_select")
    public WebElement cboCategoryDropdown;

    //Postcode
    @FindBy(how=How.ID, using="searchGeo_text")
    public  WebElement txtPostcode;

    @FindBy(how=How.ID, using="error_bubble_searchGeo_text")
    public static WebElement msgMissingPostCode;

    @FindBy(how=How.CSS, using="div[id='info_bubble_searchGeo_text']>span")
    public static WebElement msgEnterYourPostCode;

    @FindBy(how=How.ID, using="idisclaimer")
    public  WebElement lblDisclaimer;

    @FindBy(how=How.CSS, using="select#id_individual_type")
    public WebElement cboUserType;

    @FindBy(how=How.CSS,using="select#id_you_are")
    public WebElement cboIAm;

    @FindBy(how=How.ID, using="id_age")
    public WebElement cboAge;

    @FindBy(how=How.CSS, using="select#id_form_search_service_type_label")
    public WebElement cboEthnicity;

    @FindBy(how=How.CSS, using="input#post_option_languages_name_0")
    public WebElement chkLanguageEnglish;

    @FindBy(how=How.CSS, using="select#id_interests")
    public WebElement cboLookingFor;

    @FindBy(how=How.CSS, using="input#checkbox_select_all")
    public WebElement chkInterestsAll;

    @FindBy(how=How.CSS, using="input#form_post_rates_1hour_incall.posting-rate")
    public WebElement txtRatesIncall1Hour;

    @FindBy(how=How.CSS, using="input#form_post_rates_1hour_outcall.posting-rate")
    public WebElement txtRatesOutcall1Hour;

    @FindBy(how=How.ID, using="post_option_service_for_1")
    public WebElement chkServiceWoman;

    @FindBy(how=How.ID, using="user_email")
    public WebElement txtUsername;

    @FindBy(how=How.ID, using="user_password")
    public WebElement txtPassword;

    @FindBy(how=How.ID, using="term_checkbox")
    public WebElement chkTsAndCs;

    @FindBy(how=How.ID, using="select_all_plans")
    public  WebElement chkPlanAll;

    @FindBy(how=How.ID, using="publish_button")
    public WebElement btnSubmit;

    @FindBy(how=How.CSS, using="div[id='enhancement_plans_table']")
    public WebElement planGrid;

    @FindBy(how = How.CSS, using = "li.ui-menu-item")
    public WebElement btnPostCodeDropdown;

    //-----Methods
    public void setTitle(String titleValue) {
        wait.until(ExpectedConditions.visibilityOf(lblDisclaimer));
        wait.until(ExpectedConditions.visibilityOf(txtTitle));
        txtTitle.sendKeys(titleValue);
    }

    public void setDescription(String descriptionValue) {
        wait.until(ExpectedConditions.visibilityOf(txtDescription));
        txtDescription.sendKeys(descriptionValue);
    }

    public void selectCategory(String  Value) {
        selectFromADropDdown(Value, cboCategoryDropdown);
    }


    public void setPostcode(String postcodeValue) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(lblDisclaimer));
        wait.until(ExpectedConditions.visibilityOf(txtPostcode));
        txtPostcode.sendKeys(postcodeValue);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("ul.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all"))));
        wait.until(ExpectedConditions.elementToBeClickable(btnPostCodeDropdown));
        btnPostCodeDropdown.click();
    }

    public void selectUserType(String userTypeValue) {
        selectFromADropDdown(userTypeValue, cboUserType);
    }

    public void selectIAm(String iAmValue) {
        selectFromADropDdown(iAmValue, cboIAm);
    }

    public void selectAge(String ageValue) {
        selectFromADropDdown(ageValue, cboAge);
    }

    public void selectEthnicity(String ethnicityValue) {
        selectFromADropDdown(ethnicityValue, cboEthnicity);
    }

    public void selectLanguageEnglish() {
        chkLanguageEnglish.click();
    }

    public void selectLookingFor(String lookingForValue) {
        selectFromADropDdown(lookingForValue, cboLookingFor);
    }

    public void selectInterestsAll() {
        chkInterestsAll.click();
    }

    public void selectServiceWoman() {
        chkServiceWoman.click();
    }

    public void setRatesIncall1hour(String rateIncall1hourValue) {
        txtRatesIncall1Hour.sendKeys(rateIncall1hourValue);
    }

    public void setRatesOutcall1hour(String rateOutcall1hourValue) {
        txtRatesOutcall1Hour.sendKeys(rateOutcall1hourValue);
    }

    public void setCredentials(String usernameValue, String passwordValue) {
        txtUsername.sendKeys(usernameValue);
        txtPassword.sendKeys(passwordValue);
    }

    public void selectAllPlans() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("switch_account"))));
        chkPlanAll.click();
    }

    public void selectTsAndCs() {
        chkTsAndCs.click();
    }

    public PlanPaymentPage selectSubmit() {
        btnSubmit.click();
        wait.until(ExpectedConditions.titleIs("Vivastreet Secure Payments"));
        Browser.checkPageUrl("http://secure.dev.vivastreet.ie/", "This is not the payment page");
        return new PlanPaymentPage(driver);
    }

    public void ValidPostAd(User user) throws InterruptedException {
        selectCategory(user.categoryValue);
        setPostcode(user.adPostItem.PostCode);
        setTitle(user.adPostItem.Title);
        setDescription(user.adPostItem.Description);
        selectIAm(user.adPostItem.Gender);
        selectAge(user.adPostItem.age);
        selectLookingFor(user.adPostItem.Lookingfor);
        selectInterestsAll();
        setCredentials(user.email,user.password);
        selectAllPlans();
        selectTsAndCs();
        selectSubmit();
        //Browser.checkPageUrl("http://secure.dev.vivastreet.ie/", "This is not the payments page ");
    }


    public void InvalidPostAd(User user) throws InterruptedException {
        selectCategory(user.categoryValue);
        if (user.postcode != "") {
            setPostcode(user.adPostItem.PostCode);
        }
        setTitle(user.adPostItem.Title);
        if (user.adPostItem.Title != "") {
            setTitle((user.adPostItem.Title));
        }
        setDescription(user.adPostItem.Description);
        selectIAm(user.adPostItem.Gender);
        selectAge(user.adPostItem.age);
        selectLookingFor(user.adPostItem.Lookingfor);
        selectInterestsAll();
        setCredentials(user.email,user.password);
        if (user.postcode != "") {
            selectAllPlans();
        }
        selectTsAndCs();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("switch_account"))));

        btnSubmit.click();
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
