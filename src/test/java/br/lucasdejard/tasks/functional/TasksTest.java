package br.lucasdejard.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		System.setProperty("webdriver.chrome.driver", "/home/lucasdejard/Documentos/jenkinscourse/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		return driver;
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
		
		//cliclar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);
		}
		finally{
		//fechar o browser
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		//cliclar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		// NÃO escrever descrição
		// driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);
		}
		finally{
		//fechar o browser
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		//cliclar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		// escrever descrição
		 driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		// NAO escrever data
		// driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);
		}
		finally{
		//fechar o browser
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.quit();
		}
	}

	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		//cliclar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		// escrever descrição
		 driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		// escrever data PASSADA
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);
		}
		finally{
		//fechar o browser
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.quit();
		}
	}


}
