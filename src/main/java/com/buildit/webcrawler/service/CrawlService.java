package com.buildit.webcrawler.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * Service used to manage crawler lifecycle.
 * @author DELL
 *
 */
@Service
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class CrawlService {
	private static Logger LOGGER = LogManager.getLogger(CrawlService.class);

	@Value("${url.init}")
	private String initUrl;
	@Value("${max.depth}")
	private int maxDepth;
	@Value("${url.facebook}")
	private String facebook;
	@Value("${url.google}")
	private String google;
	@Value("${url.snapchat}")
	private String snapchat;
	@Value("${url.instagram}")
	private String instagram;
	@Value("${url.twitter}")
	private String twitter;

	/*
	 * HashSet too contain crawled URLs
	 */
	private HashSet<String> links;
	/*
	 * HashSet to contain ignored URLs
	 */
	private HashSet<String> ignoredUrls;

	public CrawlService() {
		links = new HashSet<>();
		ignoredUrls = new HashSet<>();
	}

	/**
	 * This method is called to check if URL being processed is malformed or not,
	 * if not then proceed further
	 * @return Map containing processed and ignored URLs
	 * @throws IllegalArgumentException
	 * @throws RuntimeException
	 * @throws IOException
	 */
	public Map<String, Object> initialize() throws IllegalArgumentException, RuntimeException, IOException {
		Map<String, Object> resultMap;
		if (!initUrl.endsWith(".com") || !initUrl.startsWith("http://")) {
			LOGGER.error("Malformed url found for processing");
			throw new IllegalArgumentException("Malformed URL to crawl!!");
		} else {
			LOGGER.error("Crawler initialized");
			resultMap = new HashMap<>();
			processLinks(initUrl, 0);
			resultMap.put("processed", links);
			resultMap.put("ignored", ignoredUrls);
		}
		
		return resultMap;

	}

	/**
	 * Main initialization of crawler happens here, recursively
	 * @param initUrl, URL to start with
	 * @param depth, Level of search for URL else it can run forever
	 * @throws IOException
	 */
	private void processLinks(String initUrl, int depth) throws IOException {
		if ((!links.contains(initUrl) && (depth < maxDepth))) {
			LOGGER.info(">> Depth: " + depth + " [" + initUrl + "]");
			try {
				if (validateUrl(initUrl)) {
					links.add(initUrl);
					Document document = Jsoup.connect(initUrl).get();
					Elements linksOnPage = document.select("a[href]");

					depth++;
					for (Element page : linksOnPage) {
						processLinks(page.attr("abs:href"), depth);
					}
				} else {
					// We don't need, just to response purpose
					ignoredUrls.add(initUrl);
				}
			} catch (IOException e) {
				LOGGER.error("For '" + initUrl + "': " + e.getMessage());
			}
		}
	}

	/**
	 * Checks if URL being processed contains external sites reference
	 * @param url To be processed
	 * @return boolean whether URL is external or not
	 */
	private boolean validateUrl(String url) {
		if (url.contains(facebook) || url.contains(google) || url.contains(twitter) || url.contains(instagram)
				|| url.contains(snapchat)) {
			return false;
		}
		return true;
	}

}
