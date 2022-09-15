
package tests;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


public class TestWaits {
 // Variables
	
	WebDriver driver;
	String PATH_DRIVER = "./src/test/resources/driver/chromedriver.exe";
	String TIPO_DRIVER ="webdriver.chrome.driver";
	String URL = "http://the-internet.herokuapp.com/dynamic_loading/2";
     
	
    @Before
    public void setUp()
    {
    	 System.setProperty(TIPO_DRIVER,PATH_DRIVER);
         driver = new ChromeDriver();
         driver.get(URL);
       //ESPERA IMPLICTA
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }
   //@Test
   public void testSinWait()
   {
   //PASO 1:CLICK EN BOTON Start
  // WebElement btnStart = driver.findElement(By.xpath("//*[@id='start']/button"));
  // btnStart.click(); //METODO CLICK
   //PASO 2: VALIDAR EL TEXTO
  // WebElement txtSaludo = driver.findElement(By.xpath("//*[@id='finish']/h4"));
   //Assert.assertEquals("Hello World!",txtSaludo.getText());
   }
 //MANERA EXPLICITA
  // @Test
  public void testConWaitExplicit()
  {
   //By localizadorTxtSaludo = (By.xpath("//*[@id='finish']/h4")); 
   //PASO 1:CLICK EN BOTON Start
   // WebElement btnStart = driver.findElement(By.xpath("//*[@id='start']/button"));
    //btnStart.click(); //METODO CLICK
    //PASO 2: VALIDAR EL TEXTO
    //WebDriverWait esperaExplicita = new WebDriverWait(driver,5);
    //esperaExplicita.until(ExpectedConditions.visibilityOfElementLocated(localizadorTxtSaludo));
    
   // WebElement txtSaludo = driver.findElement(By.xpath("//*[@id='finish']/h4"));
   // Assert.assertEquals("Hello World!",txtSaludo.getText());
  }
  
  @Test//USANDO FlUENTWAIT
  public void testConWaitExplicitFluent()
  {
 	  final By localizadorTxtSaludo = By.xpath("//*[@id='finish']/h4"); 
 	  
 	  //PASO 1:CLICK EN BOTON Start
 	    WebElement btnStart = driver.findElement(By.xpath("//*[@id='start']/button"));
 	    btnStart.click(); //METODO CLICK
 	    
 	    //ESPERA FLUENTWAIT
 	     Wait<WebDriver> esperaFluida = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
 	        .pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
 	    // esperafluida.until(ExpectedConditions.visibilityOfElementLocated(localizadorTxtSaludo));// ES UNA CONDICIÓN
 	     esperaFluida.until(new Function<WebDriver,WebElement>(){
 	    	public WebElement apply(WebDriver driver) 
 	    	{
 	    		return driver.findElement(localizadorTxtSaludo);
 	    	}
 	     });
 	     
 	     //PASO 2: VALIDAR
 	    WebElement txtSaludo = driver.findElement (By.xpath("//*[@id='finish']/h4"));
 	     Assert.assertEquals("Hello World!",txtSaludo.getText());
 	    }
   
   @After
   public void tearDown() {
		 driver.quit();

}
}

