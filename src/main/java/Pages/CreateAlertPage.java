package Pages;

import Base.BasePage;
import Utilities.User;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CreateAlertPage extends BasePage {

    public WebDriver driver;
    WebDriverWait wait;

    public CreateAlertPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    //----------PageFactory

    //Title
    @FindBy(how = How.ID, using = "vs-cat-dropdown-1_alert")
    public WebElement cboCategoryDropdown;

    @FindBy(how = How.ID, using = "vs_geo_dropdown_alert_1")
    public WebElement cboLocationDropdown;

    @FindBy(how = How.ID, using = "vs_search_keywords_alert")
    public WebElement txtSearchKeyword;

    @FindBy(how = How.ID, using = "vs_searchform_age_range_min")
    public WebElement cboMinAge;

    @FindBy(how = How.ID, using = "vs_searchform_age_range_max")
    public WebElement cboMaxAge;

    @FindBy(how = How.ID, using = "vs_searchform_you_are")
    public WebElement cboGender;

    @FindBy(how = How.ID, using = "vs_searchform_service_type")
    public WebElement cboLikesInterest;

    @FindBy(how = How.ID, using = "lb_alert_submit")
    public WebElement btnSubmitAlert;

    @FindBy(how = How.CSS, using = "td:nth-child(4) a")
    public WebElement btnMyEmailAlerts;

    @FindBy(how = How.CSS, using = "input.vs-input-button-green.kiwii-btn.kiwii-margin-right-small.kiwii-btn-large")
    public List<WebElement> btnCreateNewAlert;

    @FindBy(how = How.ID, using = "vs_lb_create_alert")
    public WebElement lblCreateAlertConfirmation;

    @FindBy(how = How.CSS, using = "tr:nth-child(1).kiwii-bg-white td:nth-child(2) div a:nth-child(1)")
    public WebElement btnDeleteAlert;

    @FindBy(how = How.CSS, using = "tr:nth-child(1).kiwii-bg-white td:nth-child(2) div a:nth-child(2)")
    public WebElement btnEditAlert;

    @FindBy(how = How.ID, using = "lb_alert_close_success")
    public WebElement btnCloseAlertConfirmation;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Your alert has been deleted')]")
    public WebElement msgAlertDeleteConfirmation;

    @FindBy(how = How.ID, using = "lb_alert_screen_step5")
    public WebElement msgAlertEditConfirmation;


    //-----Methods


    public void selectCategory(String Value) {
        wait.until(ExpectedConditions.visibilityOf(cboCategoryDropdown));
        selectFromADropDdown(Value,cboCategoryDropdown);
    }

    public void selectLocation(String Value) {
        selectFromADropDdown(Value,cboLocationDropdown);
    }

    public void selectMinAge(String Value) {
        wait.until(ExpectedConditions.visibilityOf(cboMinAge));
        selectFromADropDdown(Value, cboMinAge);
    }

    public void selectMaxAge(String Value) {
        selectFromADropDdown(Value,cboMaxAge);
    }

    public void selectGender(String Value) {
        selectFromADropDdown(Value,cboGender);
    }

    public void selectLikesInterests(String Value) {
        selectFromADropDdown(Value,cboLikesInterest);
    }

    public void closeAlertConfirmation() {
        wait.until(ExpectedConditions.visibilityOf(btnCloseAlertConfirmation));
        btnCloseAlertConfirmation.click();
    }


    public void createAlert(User user) {

        btnMyEmailAlerts.click();
        btnCreateNewAlert.get(1).click();
        selectCategory(user.alertItem.Category);
        selectLocation(user.alertItem.Location);
        txtSearchKeyword.sendKeys(user.alertItem.LookingFor);
        selectMinAge(user.alertItem.MinAge);
        selectMaxAge(user.alertItem.MaxAge);
        selectGender(user.alertItem.Gender);
        selectLikesInterests(user.alertItem.LikesInterests);
        btnSubmitAlert.click();

    }

    public void deleteAlert(User user) {

        btnMyEmailAlerts.click();
        verifyAnAlertExists(); //a check in case the alert was not created in the previous step
        btnDeleteAlert.click();
    }

    private void verifyAnAlertExists() throws ElementNotVisibleException {
        if(!btnDeleteAlert.isDisplayed()){
            throw new ElementNotVisibleException("No alert exists");
        }
    }

    public void modifyAlert(User user) {

        btnEditAlert.click();
        wait.until(ExpectedConditions.visibilityOf(txtSearchKeyword));
        txtSearchKeyword.clear();
        txtSearchKeyword.sendKeys("Modified Keyword");
        btnSubmitAlert.click();
        wait.until(ExpectedConditions.visibilityOf(msgAlertEditConfirmation));
    }
}
