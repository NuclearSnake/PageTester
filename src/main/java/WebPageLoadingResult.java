public class WebPageLoadingResult {
    private String webpage = null;
    private long timeToFirstBit = -1;
    private long timeToFinish = -1;

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
