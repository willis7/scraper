package com.willis.scraper

import groovy.util.logging.Slf4j
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by willis7 on 12/01/15.
 */
@Slf4j
public class Scraper {
    public static void main(String[] args) {
        scrapeTopic("/wiki/python");
    }

    /**
     * Finds all the elements that match the select string, selects the first
     * element of this set, and turns the resulting object into plain text.
     *
     * @param url
     */
    private static void scrapeTopic(String url) {
        String html = getUrl("http://www.wikipedia.org/" + url)
        Document doc = Jsoup.parse(html)
        String contentText = doc.select("#mw-content-text > p").first().text()
        log.info contentText
    }

    /**
     * Takes in an arbitrary ULR and returns the raw source code as a string.
     * @param url
     * @return the output from the BufferedReader object
     */
    private static String getUrl(String url) {
        URL urlObj = null;
        try {
            urlObj = new URL(url);
        }catch(MalformedURLException e) {
            log.error "The URL was malformed"
        }

        URLConnection urlCon = null
        BufferedReader brdr = null
        String outputText = ""

        try {
            urlCon = urlObj.openConnection()
            brdr = new BufferedReader(new InputStreamReader(urlCon.getInputStream()))
            String line = "";
            while ((line = brdr.readLine()) != null) {
                outputText += line;
            }
        }catch(IOException e) {
            log.error "There was an error connecting to the URL"
        }
        return outputText;
    }
}
