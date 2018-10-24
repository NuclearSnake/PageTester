import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    public static void sortByText(Elements elements){
        elements.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));
    }

    public static Elements getWithoutDuplicates(Elements elements){
        Elements result = new Elements();
        boolean duplicate;
        for(Element element : elements){
            duplicate = false;
            for(Element already : result){
                if(already.toString().equals(element.toString())) {
                    duplicate = true;
                    break;
                }
            }
            if(!duplicate)
                result.add(element);
        }

        return result;
    }

    public static Elements removeAttributes(Elements elements){
        for(Element element : elements){
            for(Attribute attribute : element.attributes()){
                if(!"src".equals(attribute.getKey()))
                    element.attr(attribute.getKey(), "");
            }
        }

        return elements;
    }

    public static String elementsToNiceString(String name, Elements elements){
        StringBuilder builder = new StringBuilder();
        int total = elements.size();
        removeAttributes(elements);
        elements = getWithoutDuplicates(elements);
        int unique = elements.size();

        builder.append(String.format("%s: %d (%d unique)", name, total, unique)).append(newLineChar);

        sortByText(elements);

        for(Element element : elements){
            builder.append(element).append(newLineChar);
        }

        return builder.toString();
    }
}
