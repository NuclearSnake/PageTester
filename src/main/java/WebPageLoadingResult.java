public class WebPageLoadingResult {
    protected String urlString = null;
    protected int responseCode = -1;
    protected String webpage = null;
    protected long timeToFirstBit = -1;
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
