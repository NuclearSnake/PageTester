import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SimpleResultDisplayer implements ResultDisplayer {
    static final String newLineChar = "\n";

    @Override
    public String getResultText(WebPageTestingResult testingResult) {
        StringBuilder builder = new StringBuilder();
        builder.append("The result of testing '").append(testingResult.getLoadingResult().getUrlString()).append("':").append(newLineChar);
        builder.append("\tTime to first bit: ").append(testingResult.getLoadingResult().getTimeToFirstBit()).append(newLineChar);
        if(!testingResult.isSuccessfull()) {
            builder.append("Response code: ").append(testingResult.getLoadingResult().getResponseCode());
            return builder.toString();
        }

        builder.append("\tTime to finish reading: ").append(testingResult.getLoadingResult().getTimeToFinish()).append(newLineChar).append(newLineChar);

        builder.append("\tTitle: ").append(testingResult.getTitle()).append(newLineChar);
        builder.append("\tDescription: ").append(testingResult.getDescription()).append(newLineChar).append(newLineChar);
        builder.append("\tHeaders H1 found: ").append(testingResult.getHeadersH1().size()).append(newLineChar);
        builder.append("\tImages found: ").append(testingResult.getImages().size()).append(newLineChar);
        builder.append("\tLinks found: ").append(testingResult.getLinks().size()).append(newLineChar);

        builder.append(elementsToNiceString("HEADERS H1", testingResult.getHeadersH1()));
        builder.append(elementsToNiceString("IMAGES", testingResult.getImages()));
        builder.append(elementsToNiceString("LINKS", testingResult.getLinks()));

        return builder.toString();
    }

    public static String elementsToNiceString(String name, List<String> elements){
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
