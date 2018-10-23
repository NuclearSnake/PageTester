import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class SiteTester {
    private SiteLoader siteLoader;

    public SiteTester(SiteLoader siteLoader) {
        this.siteLoader = siteLoader;
    }

    public String test(String webpage) {
        String html = siteLoader.loadWebpage(webpage);
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("a");
//        Element head = doc.select("head").first();
//        System.out.println(links);
        return (links.toString());
    }
}
