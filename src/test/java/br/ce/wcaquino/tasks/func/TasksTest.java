package br.ce.wcaquino.tasks.func;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DriverCommand;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void naodeveSalvarTarefaSemDescricao() {
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
	public void naodeveSalvarTarefaSemData() {
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
	public void naodeveSalvarTarefaComDataPassada() {
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
