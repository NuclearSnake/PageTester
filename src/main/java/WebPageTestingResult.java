import org.jsoup.select.Elements;

import java.util.List;

public class WebPageTestingResult {
    private WebPageLoadingResult loadingResult;
    private List<String> links;
    private List<String> images;
    private List<String> headersH1;
    private String title;
    private String description;
    private boolean isSuccessfull;

    WebPageTestingResult(WebPageLoadingResult loadingResult) {
        this.loadingResult = loadingResult;
    }

    public WebPageLoadingResult getLoadingResult() {
        return loadingResult;
    }

    // returning not copy but the original list to reduce complexity
    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    // returning not copy but the original list to reduce complexity
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getHeadersH1() {
        return headersH1;
    }

    // returning not copy but the original list to reduce complexity
    public void setHeadersH1(List<String> headersH1) {
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