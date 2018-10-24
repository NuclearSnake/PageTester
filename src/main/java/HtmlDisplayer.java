import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * <h1>Class for generating simple <tt>HTML</tt> output</h1>
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-22
 */
public class HtmlDisplayer implements ResultDisplayer {
    private static final String newLineChar = "<br/>";
    /** This value is used to determine the size in pixels (width and height) of blocks
     * for displaying tables of {@link WebPageTestingResult#links},
     * {@link WebPageTestingResult#images} etc.*/
    private int size = 150;

    public HtmlDisplayer() {}

    /**
     * @param size for the size of a container in pixels
     * @see #size
     */
    public HtmlDisplayer(int size) {
        this.size = size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getResultText(WebPageTestingResult webPageTestingResult) {
        StringBuilder builder = new StringBuilder();
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
        builder.append("<h1>The result of testing '").append(webPageTestingResult.getLoadingResult().getUrlString()).append("':").append("</h1>");
        builder.append("<p>Time to first bit: <b>").append(webPageTestingResult.getLoadingResult().getTimeToFirstBit()).append("</b></p>");
        if(!webPageTestingResult.isSuccessfull()) {
            builder.append("<p>Response code: <b>").append(webPageTestingResult.getLoadingResult().getResponseCode()).append("</b></p>");
            return builder.toString();
        }

        builder.append("<p>Time to finish reading: <b>").append(webPageTestingResult.getLoadingResult().getTimeToFinish()).append("</b></p>").append(newLineChar);

        builder.append("<p>Title: ").append(webPageTestingResult.getTitle()).append("</p>");
        builder.append("<p>Description: ").append(webPageTestingResult.getDescription()).append("</p>").append(newLineChar);

        builder.append("<p>Links found: <b>").append(webPageTestingResult.getLinks().size()).append("</b></p>");
        builder.append("<p>Images found: <b>").append(webPageTestingResult.getImages().size()).append("</b></p>");
        builder.append("<p>Headers H1 found: <b>").append(webPageTestingResult.getHeadersH1().size()).append("</b></p>");

        builder.append(createHTMLTable("Headers H1", webPageTestingResult.getHeadersH1()));
        builder.append(createHTMLTable("Images", webPageTestingResult.getImages()));
        builder.append(createHTMLTable("Links", webPageTestingResult.getLinks()));
        builder.append("</body>");
        return builder.toString();
    }

    /**
     *
     * Creates an HTML tag structure that looks like a table of the provided elements with a name before it
     *
     * @param name of the table to prepend the table with an appropriate tag
     * @param elements to display
     * @return string representation of an HTML table containing all the elements
     */
    private static String createHTMLTable(String name, List<String> elements){
        StringBuilder builder = new StringBuilder();
        builder.append("<h3>").append(name).append(": </h3>");

//        builder.append("<textarea>");
        builder.append("<div class=\"Container\">");

        elements = Utils.getWithoutStringDuplicates(elements);
        Utils.sortByText(elements);

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
