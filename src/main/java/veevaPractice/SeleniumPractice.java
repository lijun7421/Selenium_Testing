package veevaPractice;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * This is a practice code for learning the basics of Selenium
 * It starts with Veeva homepage, navigates to job search
 * then searches and opens up the Associate Automation Engineer job page
 */
public class SeleniumPractice {
    public static void main(String[] args) throws Exception {
        // setup webdriver with chrome driver
        System.setProperty("webdriver.chrome.driver", "drivers/chrome_97/chromedriver.exe");

        WebDriver initDriver = new ChromeDriver();
        initDriver.get("https://www.veeva.com/");

        initDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // get current window
        String currentHandle = initDriver.getWindowHandle();

        // find the About Veeva field and simulate a mouse move-over
        WebElement aboutButton = initDriver.findElement(By.xpath("//*[text()='About\u00a0Veeva']"));
        Actions builder = new Actions(initDriver);
        builder.moveToElement(aboutButton).perform();

        // from the dropdown menu, select Careers
        initDriver.findElement(By.xpath("//*[text()='Careers\u00a0']")).click();

        // switch to the new window
        Set<String> handles = initDriver.getWindowHandles();
        for(String actual: handles) {
            if(!actual.equalsIgnoreCase(currentHandle)) {
                initDriver.switchTo().window(actual);
            }
        }

        // click Search Jobs button
        initDriver.findElement(By.xpath("//*[text()='Search Jobs']")).click();

        // check the Canada location checkbox
        try {
            initDriver.findElement(By.xpath("//input[@value='Canada']")).click();
        } catch (ElementClickInterceptedException e) {
            // the accept-cookie field blocks the interaction with location checkboxes
            initDriver.findElement(By.xpath("//*[@id='cn-accept-cookie']")).click();
            initDriver.findElement(By.xpath("//input[@value='Canada']")).click();
        }

        // enter keywords in the job search field
        WebElement keywordField = initDriver.findElement(By.id("cws_jobsearch_keywords"));
        keywordField.sendKeys("Associate Automation Engineer" + Keys.ENTER);

        // check the QA & Release Engineering checkbox
        initDriver.findElement(By.xpath("//input[@value='QA & Release Engineering']")).click();

        // open up the first job search result
        Thread.sleep(500);
        initDriver.findElement(By.cssSelector("a[id=job-result0]")).click();
        WebElement jobTitle = initDriver.findElement(By.xpath("//h1"));
        System.out.println(jobTitle.getText());

    }
}
