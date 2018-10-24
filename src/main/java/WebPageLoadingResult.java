/**
 * <h1>A data class to represent the loading result</h1>
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-23
 */
public class WebPageLoadingResult {
    /** The URL address of a webpage */
    protected String urlString = null;
    /** The response code we got on trying to connect */
    protected int responseCode = -1;
    /** The text representation of a webpage loaded. <p>
     * Typically Html content
     */
    protected String webpage = null;
    /** Time passed since the start of loading to the first bit of a response received */
    protected long timeToFirstBit = -1;
    /** Time passed since the start of loading to the finish of loading */
    protected long timeToFinish = -1;

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public long getTimeToFirstBit() {
        return timeToFirstBit;
    }

    public void setTimeToFirstBit(long timeToFirstBit) {
        this.timeToFirstBit = timeToFirstBit;
    }

    public long getTimeToFinish() {
        return timeToFinish;
    }

    public void setTimeToFinish(long timeToFinish) {
        this.timeToFinish = timeToFinish;
    }
}
