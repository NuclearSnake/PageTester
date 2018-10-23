import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SiteTester {
    private SiteLoader siteLoader;

    public SiteTester(SiteLoader siteLoader) {
        this.siteLoader = siteLoader;
    }

    public String test(String webpage) {
        WebPageLoadingResult result = siteLoader.loadWebpage(webpage);
        String html = result.getWebpage();
        if(html != null) {
//            Document doc = Jsoup.parse(html);
//            Elements links = doc.select("a");
//        Element head = doc.select("head").first();
//        System.out.println(links);
//            return (links.toString());
            return String.format("The result of testing '"+webpage+"':\n\tTime to first bit: %d\n\tTime to finish reading: %d", result.getTimeToFirstBit(), result.getTimeToFinish());
        } else {
            return null;
        }
    }
}
