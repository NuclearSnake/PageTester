import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ElementPostProcessor {
    public List<String> getStringListReadyToGo(Elements elements){
        removeAttributesExceptImportant(elements);
        return toStringList(elements);
    }

    public List<String> toStringList(Elements elements) {
        List<String> stringList = new ArrayList<>();
        for(Element element : elements)
            stringList.add(element.toString());

        return stringList;
    }

    public void removeAttributesExceptImportant(Elements elements){
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
