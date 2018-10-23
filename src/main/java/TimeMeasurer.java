public class TimeMeasurer {
    private long start;

    public void setStartNow(){
        start = System.currentTimeMillis();
    }

    public long getElapsedMS(){
        long now = System.currentTimeMillis();
        return now-start;
    }
}
