import java.util.ArrayList;
import java.util.List;

/**
 * <h1>A data class to represent the testing result</h1>
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-23
 */
public class WebPageTestingResult {
    /** the result of loading of a webpage */
    private WebPageLoadingResult loadingResult;
    /** all the <tt>{@code <a>}</tt> tags from the webpage */
    private List<String> links = new ArrayList<>();
    /** all the <tt>{@code <img>}</tt> tags from the webpage */
    private List<String> images = new ArrayList<>();
    /** all the <tt>{@code <h1>}</tt> headers from the webpage */
    private List<String> headersH1 = new ArrayList<>();
    /** the title of the webpage */
    private String title;
    /** the description of the webpage */
    private String description;
    /** tells if a loading was successful */
    private boolean isSuccessfull = false;

    WebPageTestingResult(WebPageLoadingResult loadingResult) {
        this.loadingResult = loadingResult;
    }

    public WebPageLoadingResult getLoadingResult() {
        return loadingResult;
    }

    /**
     * Returns the list of {@link #links} <p>
     * Note: Returns not copy but the original list to reduce complexity
     */
    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    /**
     * Returns the list of {@link #images} <p>
     * Note: Returns not copy but the original list to reduce complexity
     */
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    /**
     * Returns the list of {@link #headersH1} <p>
     * Note: Returns not copy but the original list to reduce complexity
     */
    public List<String> getHeadersH1() {
        return headersH1;
    }

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