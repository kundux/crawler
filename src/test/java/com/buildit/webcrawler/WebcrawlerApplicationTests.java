package com.buildit.webcrawler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.buildit.webcrawler.controller.CrawlController;
import com.buildit.webcrawler.main.WebcrawlerApplication;

@RunWith(SpringRunner.class)
@WebMvcTest(CrawlController.class)
@ContextConfiguration(classes={WebcrawlerApplication.class})
public class WebcrawlerApplicationTests {
	
	@Test
	public void contextLoads() {
	}

}
