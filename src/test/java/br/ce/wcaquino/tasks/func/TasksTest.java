package br.ce.wcaquino.tasks.func;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
	//	WebDriver driver = new ChromeDriver();      sem usar o Selenium Grid
		
		//Usando o Selenium Grid
		DesiredCapabilities cap = DesiredCapabilities.chrome();  // informa o browser a ser utilizado para acessar a URL  
		//WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.23:4444/wd/hub"), cap); // a URL usada é fornecida pelo Selenium Grid, no terminal (hub)
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.23:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.0.23:8001/tasks/");    // URL Tomcat (no browser pode ser acessado por localhost, porém dockerizando a sua estrutura, é necessário usar o IP da máquina)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try { 
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			//cmd + 1 no () do getText para colocar o resultado dentro de uma variável local
			assertEquals("Success!", message);
		} finally {				
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naodeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try { 
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			//cmd + 1 no () do getText para colocar o resultado dentro de uma variável local
			assertEquals("Fill the task description", message);
		} finally {				
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naodeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try { 
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			//cmd + 1 no () do getText para colocar o resultado dentro de uma variável local
			assertEquals("Fill the due date", message);
		} finally {				
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naodeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try { 
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			//cmd + 1 no () do getText para colocar o resultado dentro de uma variável local
			assertEquals("Due date must not be in past", message);
		} finally {				
			//fechar o browser
			driver.quit();
		}
	}
	

}
