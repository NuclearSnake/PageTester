import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ElementPostProcessorTest {

    @Test
    public void getStringListReadyToGo_ImageAttributes() {
        Document doc = Jsoup.parse(getMockWebpageImages());
        Elements images = doc.select("img");

        List<String> list = new ElementPostProcessor().getStringListReadyToGo(images);
        for(String element : list){
            assertTrue(element.contains("<img "));
            assertTrue(element.contains("src"));
            assertTrue(element.contains("alt"));
            assertFalse(element.contains("copyTo"));
            assertFalse(element.contains("class"));
            assertFalse(element.contains("resizeMode"));
            assertFalse(element.contains("wrongWay"));
            assertFalse(element.contains("shouldnt"));
        }
    }

    @Test
    public void getStringListReadyToGo_LinkAttributes() {
        Document doc = Jsoup.parse(getMockWebpageImages());
        Elements images = doc.select("a");

        List<String> list = new ElementPostProcessor().getStringListReadyToGo(images);
        for(String element : list){
            assertTrue(element.contains("<a "));
            assertTrue(element.contains("href"));
            assertFalse(element.contains("class"));
            assertFalse(element.contains("whoops"));
        }
    }

    // ----------------------------------------------------------------------------
    private String getMockWebpageImages(){
        return "<html><head></head><body>" +
                "<img src=\"a\" alt=\"b\" copyTo=\"\" class=\"NoWay\"/>" +
                "<img src=\"b\" alt=\"c\" resizeMode=\"\" blabla=\"\" wrongWay=\"\"/>" +
                "<img src=\"c\" alt=\"d\" shouldnt=\"pass\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "</body></html>";
    }
    private String getMockWebpageLinks(){
        return "<html><head></head><body>" +
                "<a href=\"a\" class=\"d\"></a>" +
                "<a href=\"b\" whoops=\"e\">AAAA</a>" +
                "<a href=\"c\" />" +
                "</body></html>";
    }
}