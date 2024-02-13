package com.qa.lib.selenium;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:itemSchema.sql",
		"classpath:itemData.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ItemTest {

	private RemoteWebDriver driver;

	@LocalServerPort
	private int port;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test
	@Order(2)
	void testCreateBook() {
		this.driver.get("http://localhost:" + this.port);

		String book = "Spiderman";
		String type = "Book";
		WebElement btype = this.driver.findElement(By.cssSelector(
				"#root > main > div > section:nth-child(2) > div:nth-child(2) > form > div > input:nth-child(1)"));
//		btype.sendKeys(type);
		btype.sendKeys("Book");

		WebElement bname = this.driver.findElement(By.cssSelector(
				"#root > main > div > section:nth-child(2) > div:nth-child(2) > form > div > input:nth-child(2)"));
//		bname.sendKeys(book);
		bname.sendKeys("Spiderman");

		WebElement add = this.driver.findElement(
				By.cssSelector("#root > main > div > section:nth-child(2) > div:nth-child(2) > form > div > button"));
		add.click();

		WebElement created = this.driver.findElement(
				By.cssSelector("#root > main > div > section:nth-child(2) > div:nth-child(3) > h3:nth-child(2)"));

		Assertions.assertEquals(true, created.getText().contains("Spiderman"));
	}

	@Test
	@Order(1)
	void testGetBook() {
		this.driver.get("http://localhost:" + this.port);

		WebElement created = this.driver
				.findElement(By.cssSelector("#root > main > div > section:nth-child(2) > div:nth-child(3) > h3"));
		Assertions.assertEquals("Pinochio (Book)", created.getText());
	}

//	@AfterEach
//	void tearDown() {
//		this.driver.quit();
//	}
}
