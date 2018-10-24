import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SimpleResultDisplayer implements ResultDisplayer {
    final String newLineChar = "<br/>";

    @Override
    public String getResultText(SiteTester.WebPageTestingResult testingResult) {
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
        builder.append("\tLinks found: ").append(testingResult.getLinks().size()).append(newLineChar);
        builder.append("\tImages found: ").append(testingResult.getImages().size()).append(newLineChar);
        builder.append("\tHeaders H1 found: ").append(testingResult.getHeadersH1().size()).append(newLineChar);

        builder.append(createHTMLTable(testingResult.getImages()));
//        for(Element link : testingResult.getImages()){
//            builder.append(link.toString()).append(newLineChar);
//        }
        return builder.toString();
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



    public static String createHTMLTable(Elements elements){
        StringBuilder builder = new StringBuilder();

//        builder.append("<textarea>");
        builder.append("<div class=\"Container\">");

        removeAttributes(elements);
        elements = getWithoutDuplicates(elements);

        for(int i=0; i < elements.size(); i++){
            builder.append("<div class=\"Content\">");
//            element.attr("style", "width=90px; height=auto; max-width=90px");
            builder.append(elements.get(i));
            builder.append("</div>");
        }
        builder.append("</div>");
//        builder.append("</table>");
//        builder.append("</textarea>");
        return builder.toString();
    }
}
