package web.pages.advertisement;
import core.config.properties.Section;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class WebAdvertisementPage {
    private final WebDriver driver;

    public WebAdvertisementPage(WebDriver driver) {
        this.driver = driver;
    }

    private By getSectionLocator(Section section) {
        switch (section) {
            case SELL:    return By.xpath("(//label[contains(text(),'გაყიდვა')])[1]");
            case BUY:     return By.xpath("(//label[contains(text(),'შეძენა')])[1]");
            case RENT:    return By.xpath("(//label[contains(text(),'გაქირავება')])[1]");
            case SERVICE: return By.xpath("(//label[contains(text(),'მომსახურება')])[1]");
            default: throw new IllegalArgumentException("უცნობი სექცია Web-ისთვის: " + section);
        }
    }

    public void clickSection(Section section) {
        driver.findElement(getSectionLocator(section)).click();
    }
}