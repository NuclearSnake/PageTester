public class SimpleResultDisplayer implements ResultDisplayer {
    @Override
    public String getResultText(SiteTester.WebPageTestingResult testingResult) {
        StringBuilder builder = new StringBuilder();
        builder.append("The result of testing '").append(testingResult.getLoadingResult().getUrlString()).append("':\n");
        builder.append("\tTime to first bit: ").append(testingResult.getLoadingResult().getTimeToFirstBit()).append("\n");
        if(!testingResult.isSuccessfull()) {
            builder.append("Response code: ").append(testingResult.getLoadingResult().getResponseCode());
            return builder.toString();
        }

        builder.append("\tTime to finish reading: ").append(testingResult.getLoadingResult().getTimeToFinish()).append("\n\n");

        builder.append("\tTitle: ").append(testingResult.getTitle()).append("\n");
        builder.append("\tDescription: ").append(testingResult.getDescription()).append("\n\n");
        builder.append("\tLinks found: ").append(testingResult.getLinks().size()).append("\n");
        builder.append("\tImages found: ").append(testingResult.getImages().size()).append("\n");
        builder.append("\tHeaders H1 found: ").append(testingResult.getHeadersH1().size()).append("\n");
        return builder.toString();
    }
}
