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

        try{
            def doc = Jsoup.connect(url).get()

            // Strip the table from the page
            def table = doc.select("table").first()
            // Strip the rows from the table
            def tbRows = table.select("tr")

            // For each column in a row, print its contents if not empty
            tbRows.each { row ->
                 def tbCol = row.select("td")
                 tbCol.each { column ->
                     if(!column.text().empty) {
                         println column.text()
                     }
                 }
            }


        }catch(IOException e) {
            log.error("IOException", e)
        }
    }
}
