
import com.sun.source.tree.AssertTree;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    private static String chromedriverPath = "<chromedriver_path>";
    private static WebDriver driver;

    @Rule
    public TestListener failure = new TestListener(driver);

    @BeforeClass
    public static void Setup(){
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        driver = new ChromeDriver();
    }

    @Test
    public void AddNewPostTest() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Login();
        driver.manage().window().maximize();
        GetToAddPost();
        AddNewTestingPost();
    }

    private void Login(){
        driver.get("http://demosite.center/wordpress/wp-login.php");
        WebElement username = driver.findElement(By.id("user_login"));
        WebElement password = driver.findElement(By.id("user_pass"));
        username.sendKeys("admin");
        password.sendKeys("demo123");

        WebElement login = driver.findElement(By.id("wp-submit"));
        login.click();
    }

    private void GetToAddPost(){
        Actions action = new Actions(driver);
        WebElement posts = driver.findElement(By.id("menu-posts"));
        action.moveToElement(posts).perform();

        WebElement addNewPost = posts.findElement(By.linkText("Add New"));
        addNewPost.click();
    }

    private void AddNewTestingPost(){
        WebElement title = driver.findElement(By.id("title"));
        title.sendKeys("testing12");

        WebElement publishPost = driver.findElement(By.id("publish"));
        publishPost.click();
    }
}
