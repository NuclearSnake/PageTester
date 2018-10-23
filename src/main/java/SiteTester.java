import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SiteTester {
    private SiteLoader siteLoader;
    private ResultDisplayer resultDisplayer;

    public SiteTester(SiteLoader siteLoader, ResultDisplayer resultDisplayer) {
        this.siteLoader = siteLoader;
        this.resultDisplayer = resultDisplayer;
    }

    public String test(String urlString) {
        WebPageLoadingResult loadingResult = siteLoader.loadWebpage(urlString);
        WebPageTestingResult testingResult = new WebPageTestingResult(loadingResult);

        String html = loadingResult.getWebpage();
        testingResult.setSuccessfull(loadingResult.getResponseCode() == 200);
        if(!testingResult.isSuccessfull())
            return resultDisplayer.getResultText(testingResult);

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
            return resultDisplayer.getResultText(testingResult);
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

    public class WebPageTestingResult {
        private WebPageLoadingResult loadingResult;
        private Elements links;
        private Elements images;
        private Elements headersH1;
        private String title;
        private String description;
        private boolean isSuccessfull;

        public WebPageTestingResult(WebPageLoadingResult loadingResult) {
            this.loadingResult = loadingResult;
        }

        public WebPageLoadingResult getLoadingResult() {
            return loadingResult;
        }

        public Elements getLinks() {
            return links;
        }

        public void setLinks(Elements links) {
            this.links = links;
        }

        public Elements getImages() {
            return images;
        }

        public void setImages(Elements images) {
            this.images = images;
        }

        public Elements getHeadersH1() {
            return headersH1;
        }

        public void setHeadersH1(Elements headersH1) {
            this.headersH1 = headersH1;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isSuccessfull() {
            return isSuccessfull;
        }

        public void setSuccessfull(boolean successfull) {
            this.isSuccessfull = successfull;
        }
    }
}
