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

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class ItemTest {

	private RemoteWebDriver driver;

	@LocalServerPort
	private int port;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test
	@Order(1)
	void testCreateBook() {
		this.driver.get("http://localhost:" + this.port);
		String book = "BarryBook";
		String type = "Book";
		WebElement btype = this.driver.findElement(By.cssSelector(
				"#root > main > div > section:nth-child(2) > div:nth-child(2) > form > div > input:nth-child(1)"));
//		btype.sendKeys(type);
		btype.sendKeys("Book");

		WebElement bname = this.driver.findElement(By.cssSelector(
				"#root > main > div > section:nth-child(2) > div:nth-child(2) > form > div > input:nth-child(2)"));
//		bname.sendKeys(book);
		bname.sendKeys("BarryBook");

		WebElement add = this.driver.findElement(
				By.cssSelector("#root > main > div > section:nth-child(2) > div:nth-child(2) > form > div > button"));
		add.click();

		WebElement created = this.driver
				.findElement(By.cssSelector("#root > main > div > section:nth-child(2) > div:nth-child(3) > h3"));

		Assertions.assertEquals(true, created.getText().contains("BarryBook"));
	}

	@Test
	@Order(2)
	void testGetBook() {
		this.driver.get("http://localhost:" + this.port);

		WebElement created1 = this.driver
				.findElement(By.cssSelector("#root > main > div > section:nth-child(2) > div:nth-child(3) > h3"));
		Assertions.assertEquals("BarryBook (Book)", created1.getText());
	}

//	@AfterEach
//	void tearDown() {
//		this.driver.quit();
//	}
}
