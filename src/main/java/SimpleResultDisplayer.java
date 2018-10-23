public class SimpleResultDisplayer implements ResultDisplayer {
    @Override
    public String getResultText(String urlString, WebPageLoadingResult webPageLoadingResult) {
        return String.format("The result of testing '"+urlString+"':\n\tTime to first bit: %d\n\tTime to finish reading: %d", webPageLoadingResult.getTimeToFirstBit(), webPageLoadingResult.getTimeToFinish());
    }
}
