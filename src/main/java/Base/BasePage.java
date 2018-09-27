package Base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    public void selectFromADropDdown(String Value, WebElement cboCategoryDropdown) {
        Select dropdown = new Select(cboCategoryDropdown);
        dropdown.selectByVisibleText(Value);
    }
}
