import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SiteTester {
    private SiteLoader siteLoader;
    private ResultDisplayer resultDisplayer;

    public SiteTester(SiteLoader siteLoader, ResultDisplayer resultDisplayer) {
        this.siteLoader = siteLoader;
        this.resultDisplayer = resultDisplayer;
    }

    public String test(String urlString) {
        WebPageLoadingResult result = siteLoader.loadWebpage(urlString);
        String html = result.getWebpage();
        if(html != null) {
//            Document doc = Jsoup.parse(html);
//            Elements links = doc.select("a");
//        Element head = doc.select("head").first();
//        System.out.println(links);
//            return (links.toString());
            return resultDisplayer.getResultText(urlString, result);
        } else {
            return null;
        }
    }
}
