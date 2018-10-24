import java.net.SocketTimeoutException;

/**
 * <h1>Common interface for Webpage loading</h1>
 *
 * Provides uniform API for loading webpages
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-22
 */
public interface SiteLoader {
    /**
     * Loads a webpage requested in a parameter
     *
     * @param url from which the page should be loaded
     * @param timeoutMS timeout in milliseconds
     * @return the result of the loading
     *
     * @see WebPageLoadingResult
     */
    WebPageLoadingResult loadWebpage(String url, int timeoutMS) throws SocketTimeoutException;
}
