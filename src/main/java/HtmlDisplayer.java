import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlDisplayer implements ResultDisplayer {
    final String newLineChar = "<br/>";

    @Override
    public String getResultText(WebPageTestingResult testingResult) {
        StringBuilder builder = new StringBuilder();
        final int size = 150;
        final String CSSStyle =
                ".Container {\n" +
                "   width: 100%;\n" +
//              "   border:1px solid purple;\n" +
                "   text-align: center;\n" +
                "}\n" +
                ".Content\n" +
                "{\n" +
                "   width: "+size+"px;\n" +
                "   height: "+size+";\n" +
//              "   border:1px solid red;\n" +
                "   display: inline-block;\n" +
                "   vertical-align: middle;\n" +
                "}" +
                ".Content img\n" +
                "{\n" +
                "   max-width: "+(size-10)+"px;\n" +
                "   width: "+(size-10)+"px;\n" +
                "   height: auto;\n" +
                "}";

        builder.append("<html><head><style>");
        builder.append(CSSStyle);
        builder.append("</style></head><body>");
        builder.append("<h1>The result of testing '").append(testingResult.getLoadingResult().getUrlString()).append("':").append("</h1>");
        builder.append("<p>Time to first bit: <b>").append(testingResult.getLoadingResult().getTimeToFirstBit()).append("</b></p>");
        if(!testingResult.isSuccessfull()) {
            builder.append("<p>Response code: <b>").append(testingResult.getLoadingResult().getResponseCode()).append("</b></p>");
            return builder.toString();
        }

        builder.append("<p>Time to finish reading: <b>").append(testingResult.getLoadingResult().getTimeToFinish()).append("</b></p>").append(newLineChar);

        builder.append("<p>Title: ").append(testingResult.getTitle()).append("</p>");
        builder.append("<p>Description: ").append(testingResult.getDescription()).append("</p>").append(newLineChar);

        builder.append("<p>Links found: <b>").append(testingResult.getLinks().size()).append("</b></p>");
        builder.append("<p>Images found: <b>").append(testingResult.getImages().size()).append("</b></p>");
        builder.append("<p>Headers H1 found: <b>").append(testingResult.getHeadersH1().size()).append("</b></p>");

        builder.append(createHTMLTable("Headers H1", testingResult.getHeadersH1()));
        builder.append(createHTMLTable("Images", testingResult.getImages()));
        builder.append(createHTMLTable("Links", testingResult.getLinks()));
        builder.append("</body>");
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



    public static String createHTMLTable(String name, Elements elements){
        StringBuilder builder = new StringBuilder();
        builder.append("<h3>").append(name).append(": </h3>");

//        builder.append("<textarea>");
        builder.append("<div class=\"Container\">");

        removeAttributes(elements);
        elements = getWithoutDuplicates(elements);
        sortByText(elements);

        for(int i=0; i < elements.size(); i++){
            builder.append("<div class=\"Content\">");
            builder.append(elements.get(i));
            builder.append("</div>");
        }
        builder.append("</div>");
//        builder.append("</textarea>");
        return builder.toString();
    }
}
