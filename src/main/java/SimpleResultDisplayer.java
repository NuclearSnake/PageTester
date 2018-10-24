import java.util.List;

/**
 * <h1>Class for generating simple text output</h1>
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-22
 */
public class SimpleResultDisplayer implements ResultDisplayer {
    private static final String newLineChar = "\n";

    @Override
    public String getResultText(WebPageTestingResult webPageTestingResult) {
        StringBuilder builder = new StringBuilder();
        builder.append("The result of testing '").append(webPageTestingResult.getLoadingResult().getUrlString()).append("':").append(newLineChar);
        builder.append("\tTime to first bit: ").append(webPageTestingResult.getLoadingResult().getTimeToFirstBit()).append(newLineChar);
        if(!webPageTestingResult.isSuccessfull()) {
            builder.append("Response code: ").append(webPageTestingResult.getLoadingResult().getResponseCode());
            return builder.toString();
        }

        builder.append("\tTime to finish reading: ").append(webPageTestingResult.getLoadingResult().getTimeToFinish()).append(newLineChar).append(newLineChar);

        builder.append("\tTitle: ").append(webPageTestingResult.getTitle()).append(newLineChar);
        builder.append("\tDescription: ").append(webPageTestingResult.getDescription()).append(newLineChar).append(newLineChar);
        builder.append("\tHeaders H1 found: ").append(webPageTestingResult.getHeadersH1().size()).append(newLineChar);
        builder.append("\tImages found: ").append(webPageTestingResult.getImages().size()).append(newLineChar);
        builder.append("\tLinks found: ").append(webPageTestingResult.getLinks().size()).append(newLineChar);

        builder.append(elementsToNiceString("HEADERS H1", webPageTestingResult.getHeadersH1()));
        builder.append(elementsToNiceString("IMAGES", webPageTestingResult.getImages()));
        builder.append(elementsToNiceString("LINKS", webPageTestingResult.getLinks()));

        return builder.toString();
    }

    /**
     *
     * Creates a text structure that looks like a list of the provided elements with a name before it
     *
     * @param name to display before the list
     * @param elements to display
     * @return simple string representation of the list
     */
    private static String elementsToNiceString(String name, List<String> elements){
        StringBuilder builder = new StringBuilder();
        int total = elements.size();
        elements = Utils.getWithoutStringDuplicates(elements);
        int unique = elements.size();

        builder.append(newLineChar).append(String.format("%s: %d (%d unique)", name, total, unique)).append(newLineChar);

        Utils.sortByText(elements);

        for(String element : elements){
            builder.append(element).append(newLineChar);
        }

        builder.append(newLineChar);

        return builder.toString();
    }
}
