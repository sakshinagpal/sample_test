

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.URL;

import javax.imageio.ImageIO;

@RunWith(Parameterized.class)
public class test_dialog_test {
  private RemoteWebDriver driver;
  private String baseUrl;
  String assignment_name,question_number,assign_name;
  Integer num;
  DesiredCapabilities dc;
  
  @Parameterized.Parameters
  public static Collection<Object[]> data() {
	   return Arrays.asList(new Object[][]{{1},{2}});
  }

  public test_dialog_test(Integer num) {
	  this.num=num;
  }

  @Before
  public void setUp() throws Exception {

	dc= DesiredCapabilities.firefox();
    driver = new FirefoxDriver(dc);
    baseUrl="http://dev2.comprotechnologies.com/simsapr18/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    System.out.println(InetAddress.getByName("127.0.0.0").isReachable(3000));
  }
  
 
@Test
public void record_time() throws Exception {
	
	System.out.println("******************************");
	System.out.println("Iteration "+num);
	System.out.println("******************************");
	WebDriverWait wait;
	long start,finish,loadtime;
	driver.get(baseUrl);
	
	driver.findElement(By.cssSelector("img[alt='Launch']")).click();
	start = System.currentTimeMillis();
	ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(newTab.get(1));
    wait = new WebDriverWait(driver, 60);// 1 minute
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("LoadingImage")));
    finish = System.currentTimeMillis();
    loadtime=(finish-start);
    System.out.println(dc.getBrowserName()+ " | " + "Component Load time"+": "+ loadtime);
    
    Thread.sleep(2000);
    driver.findElement(By.id("viewallbutton")).click();
    driver.findElement(By.xpath("//div[@id='ViewAllDlg']/div[2]/table/tbody/tr[393]/td[2]")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    start = System.currentTimeMillis();
    wait = new WebDriverWait(driver, 60);// 1 minute
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("LoadingImage")));
    finish = System.currentTimeMillis();
    loadtime=(finish-start);
    assignment_name=driver.findElement(By.id("questionAndPoints")).getText();
	assign_name=assignment_name.substring((assignment_name.indexOf(":")+1), (assignment_name.lastIndexOf(":")));
	question_number=driver.findElement(By.xpath("//span[@class='presentquestion']")).getText();
	System.out.println(dc.getBrowserName()+ " | " + "Load time for Assignment Q: "+ question_number +" -"+ assign_name +": " + loadtime);
	Thread.sleep(2000);
	
	for (int i=1;i<=10;i++)
    {
    	driver.findElement(By.id("fwdtaskbtn")).click();
    	start = System.currentTimeMillis();
    	wait = new WebDriverWait(driver, 60);// 1 minute
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("LoadingImage")));		  
    	finish = System.currentTimeMillis();
    	loadtime=(finish-start);
    	assignment_name=driver.findElement(By.id("questionAndPoints")).getText();
    	assign_name=assignment_name.substring((assignment_name.indexOf(":")+1), (assignment_name.lastIndexOf(":")));
    	question_number=driver.findElement(By.xpath("//span[@class='presentquestion']")).getText();
       	System.out.println(dc.getBrowserName()+ " | " + "Load time for Assignment Q: "+ question_number +" -"+ assign_name +": " + loadtime);
    	Thread.sleep(2000);   
    }
    	
}
 

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

}


