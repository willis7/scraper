package com.willis.scraper

import groovy.util.logging.Slf4j
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

/**
 * Created by willis7 on 12/01/15.
 */
@Slf4j
public class Scraper {
    public static void main(String[] args) {

        scrapeResults("http://www.espn.co.uk/scrum/rugby/match/scores/recent.html")
    }

    static void scrapeResults(String url) {
        Document doc = Jsoup.connect(url).get()
        Element content = doc.getElementById("scrumArticlesBoxContent")
        println content.text()
    }
}
