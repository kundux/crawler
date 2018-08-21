package com.buildit.webcrawler.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main starting point for the Application.
 * @author DELL
 *
 */
@ComponentScan("com.buildit.webcrawler.*")
@SpringBootApplication
public class WebcrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebcrawlerApplication.class, args);
	}
}
