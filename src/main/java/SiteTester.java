import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <h1>Class for testing websites</h1>
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-22
 */
public class SiteTester {
    /** the result of loading the webpage which provides some essential info needed for testing*/
    private WebPageLoadingResult loadingResult;

    public SiteTester() {}

    public SiteTester(WebPageLoadingResult loadingResult) {
        this.loadingResult = loadingResult;
    }

    /**
     * @param loadingResult a result of loading the webpage
     * @see WebPageLoadingResult
     * @see SiteLoader
     */
    public void setLoadingResult(WebPageLoadingResult loadingResult) {
        this.loadingResult = loadingResult;
    }

    /**
     * Performs testing on the {@link #loadingResult} provided before
     * @return the result of testing
     */
    public WebPageTestingResult test() {
        WebPageTestingResult testingResult = new WebPageTestingResult(loadingResult);

        String html = loadingResult.getWebpage();
        testingResult.setSuccessfull(loadingResult.getResponseCode() == 200 && html != null);
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

            ElementPostProcessor elementPostProcessor = new ElementPostProcessor();
            testingResult.setLinks(elementPostProcessor.getStringListReadyToGo(links));
            testingResult.setHeadersH1(elementPostProcessor.getStringListReadyToGo(headersH1));
            testingResult.setImages(elementPostProcessor.getStringListReadyToGo(images));

            return testingResult;
        } else {
            return null;
        }
    }

    /**
     * Retrieves a meta tag from a document by the name
     *
     * @param document to get the tag from
     * @param attr the name of the tag
     * @return string representation of the tag
     */
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
