package com.willis.scraper

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by willis7 on 12/01/15.
 */
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
        System.out.println(contentText)
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
            System.out.println("The URL was malformed");
            return "";
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
            System.out.println("There was an error connecting to the URL")
            return ""
        }
        return outputText;
    }
}
