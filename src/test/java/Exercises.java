import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercises {

    WebDriver driver;

    String b1 = "btnButton1";
    String b2 = "btnButton2";
    String check = "end";
    String result = "wrap";
    @BeforeEach
    public void SetUp() {


            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Konrad\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
@Test
    public void exercise_1() throws InterruptedException {
        int line = 2;

        driver.get("https://antycaptcha.amberteam.pl/exercises/exercise1");
        while (line <=4)
        {
            if (driver.findElement(By.xpath("/html/body/div[@class='container']/table[@class='u-full-width']/tbody/tr["+line+"]/td[2]/code")).getText().equals("B2"))
            {
                driver.findElement(By.name(b2)).click();
            }
            else if (driver.findElement(By.xpath("/html/body/div[@class='container']/table[@class='u-full-width']/tbody/tr["+line+"]/td[2]/code")).getText().equals("B1"))
                driver.findElement(By.name(b1)).click();
            line++;

        }
        driver.findElement(By.name(check)).click();
        TimeUnit.SECONDS.sleep(1);
        assertTrue(driver.findElement(By.className(result)).getText().equals("OK. Good answer"));
    }
    @Test
    public void exercise_2() throws InterruptedException {
        driver.get("https://antycaptcha.amberteam.pl/exercises/exercise2");

        String text = driver.findElement(By.xpath("/html/body/div[@class='container']/table[@class='u-full-width']/tbody/tr[2]/td[2]/code[1]")).getText();
        driver.findElement(By.id("t14")).clear();
        driver.findElement(By.id("t14")).sendKeys(text);
        driver.findElement(By.name(b1)).click();

        driver.findElement(By.name(check)).click();
        TimeUnit.SECONDS.sleep(1);
        assertTrue(driver.findElement(By.className(result)).getText().equals("OK. Good answer"));
    }
    @Test
    public void exercise_3() throws InterruptedException {
        driver.get("https://antycaptcha.amberteam.pl/exercises/exercise3");

        String select = driver.findElement(By.xpath("/html/body/div[@class='container']/table[@class='u-full-width']/tbody/tr[2]/td[2]/code")).getText();
        Select field = new Select(driver.findElement(By.name("s13")));
        field.selectByVisibleText(select);

        driver.findElement(By.name("solution")).click();
        TimeUnit.SECONDS.sleep(1);
        assertTrue(driver.findElement(By.className(result)).getText().equals("OK. Good answer"));

    }
    @Test
    public void exercise_4() throws InterruptedException {
        int item = 0;
        int fori = 1;
        driver.get("https://antycaptcha.amberteam.pl/exercises/exercise4");

        List<String> elements = new LinkedList<String>();

        elements.add(driver.findElement(By.xpath("/html/body/div[@class='container']/table[@class='u-full-width']/tbody/tr[2]/td[2]/code")).getText());
        elements.add(driver.findElement(By.xpath("/html/body/div[@class='container']/table[@class='u-full-width']/tbody/tr[3]/td[2]/code")).getText());
        elements.add(driver.findElement(By.xpath("/html/body/div[@class='container']/table[@class='u-full-width']/tbody/tr[4]/td[2]/code")).getText());
        elements.add(driver.findElement(By.xpath("/html/body/div[@class='container']/table[@class='u-full-width']/tbody/tr[5]/td[2]/code")).getText());

        for (String element : elements) {


            String group0[] = driver.findElement(By.xpath("/html/body/div/div["+ fori +"]")).getText().toString().split("\\r?\\n");

            for (int i = 0; i < group0.length; i++) {
                if (group0[i].equals(element)) {
                    item = i;
                }
            }

            driver.findElement(By.xpath("/html/body/div/div[" + fori + "]/input[" + item + "]")).click();

            fori++;
        }
        driver.findElement(By.name("solution")).click();
        TimeUnit.SECONDS.sleep(1);
        assertTrue(driver.findElement(By.className(result)).getText().equals("OK. Good answer"));

    }
    @AfterEach
    public void closeSession() {
        driver.close();
    }


}
