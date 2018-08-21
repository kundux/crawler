package com.buildit.webcrawler.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildit.webcrawler.model.GeneralResponse;
import com.buildit.webcrawler.service.CrawlService;


/**
 * Controller to handle requests for crawler.
 * @author DELL
 *
 */
@RestController
@RequestMapping(value="/v1")
public class CrawlController {
	private static Logger LOGGER = LogManager.getLogger(CrawlController.class);
	
	@Autowired
	private CrawlService crawlService;
	
	/**
	 * Entry point for Crawler
	 * @return GeneralResponse Object
	 * @throws IllegalArgumentException
	 * @throws RuntimeException
	 * @throws IOException
	 */
	@GetMapping(path = "/crawl", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> initCrawler() throws IllegalArgumentException, RuntimeException, IOException {
		LOGGER.info("Initializing crawler in CrawlController.");
		Map<String, Object> result = crawlService.initialize();
		GeneralResponse response = new GeneralResponse();
		response.setResult(result);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
