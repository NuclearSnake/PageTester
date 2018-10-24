import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SiteTester {
    private WebPageLoadingResult loadingResult;

    public void setLoadingResult(WebPageLoadingResult loadingResult) {
        this.loadingResult = loadingResult;
    }

    public WebPageTestingResult test() {
        WebPageTestingResult testingResult = new WebPageTestingResult(loadingResult);

        String html = loadingResult.getWebpage();
        testingResult.setSuccessfull(loadingResult.getResponseCode() == 200);
        if(!testingResult.isSuccessfull())
            return testingResult;

        if(html != null) {
            Document doc = Jsoup.parse(html);
            testingResult.setTitle(doc.title());
            String description = getMetaTag(doc, "description");
            if (description == null) {
                description = getMetaTag(doc, "og:description");
            }
            testingResult.setDescription(description);
            Elements links = doc.select("a");
            Elements headersH1 = doc.select("h1");
            Elements images = doc.select("img");
            testingResult.setLinks(links);
            testingResult.setHeadersH1(headersH1);
            testingResult.setImages(images);
            return testingResult;
        } else {
            return null;
        }
    }

    private String getMetaTag(Document document, String attr) {
        Elements elements = document.select("meta[name=" + attr + "]");
        for (Element element : elements) {
            String s = element.attr("content");
            if (s != null) return s;
        }
        elements = document.select("meta[property=" + attr + "]");
        for (Element element : elements) {
            String s = element.attr("content");
            if (s != null) return s;
        }
        return null;
    }
}
