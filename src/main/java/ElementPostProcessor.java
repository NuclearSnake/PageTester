import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Prepares from Jsoup library for the unified use in program.
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-24
*/
public class ElementPostProcessor {
    /**
     * Removes unnecessary attributes (all but <tt>alt</tt>, <tt>src</tt> and <tt>href</tt>)
     * and returns a ready-to-go list of Strings that can be used in a <b>JSOUP</b>-independent way
     *
     * @param elements from Jsoup library
     * @return list of strings representing html tags
     */
    public List<String> getStringListReadyToGo(Elements elements){
        removeAttributesExceptImportant(elements);
        return toStringList(elements);
    }

    /**
     * Converts {@link Elements} to regular {@link List} of {@link String}s
     */
    private List<String> toStringList(Elements elements) {
        List<String> stringList = new ArrayList<>();
        for(Element element : elements)
            stringList.add(element.toString());

        return stringList;
    }

    /**
     * Removes all the attributes from the {@link Element}s but <tt>alt</tt>, <tt>src</tt>, <tt>href</tt> etc.
     */
    private void removeAttributesExceptImportant(Elements elements){
        List<String> attrsToRemove;
        for(Element element : elements){
            attrsToRemove = new ArrayList<>();
            for(Attribute attribute : element.attributes()){
                if(!"src".equals(attribute.getKey()) && !"alt".equals(attribute.getKey()) && !"href".equals(attribute.getKey()))
                    attrsToRemove.add(attribute.getKey());
            }

            for(String key : attrsToRemove){
                element.removeAttr(key);
            }
        }
    }

}
