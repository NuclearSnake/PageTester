import org.jsoup.select.Elements;

public class WebPageTestingResult {
    private WebPageLoadingResult loadingResult;
    private Elements links;
    private Elements images;
    private Elements headersH1;
    private String title;
    private String description;
    private boolean isSuccessfull;

    WebPageTestingResult(WebPageLoadingResult loadingResult) {
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