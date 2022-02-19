import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.veeva.com");

        System.out.println(driver.getTitle());

        WebElement careerButton = driver.findElement(By.className("Careers "));
        careerButton.click();

//        WebElement htsButton = driver.findElement(By.id("cta-customer-UCB"));
//        htsButton.click();
    }
}
