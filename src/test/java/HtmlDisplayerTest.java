import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class HtmlDisplayerTest {

    @Test
    public void getResultText_EmptySuccessful() {
        WebPageTestingResult webPageTestingResult = getEmpty();
        webPageTestingResult.setSuccessfull(true);
        String string = new HtmlDisplayer().getResultText(getEmpty());

        assertNotEquals(null, string);
        assertTrue(string.length() > 0);
    }

    @Test
    public void getResultText_EmptyNotSuccessful() {
        String string = new HtmlDisplayer().getResultText(getEmpty());

        assertNotEquals(null, string);
        assertTrue(string.length() > 0);
    }

    @Test(expected = NullPointerException.class)
    public void getResultText_Null() {
        new HtmlDisplayer().getResultText(null);
    }

    @Test
    public void getResultText_NormalFromTester() {
        String string = new HtmlDisplayer().getResultText(new SiteTester(getMockNormal()).test());

        assertNotEquals(null, string);
        assertTrue(string.length() > 0);
    }

    @Test
    public void getResultText_ImagesOnlyFromTester() {
        String string = new HtmlDisplayer().getResultText(new SiteTester(getMockImagesOnly()).test());

        assertNotEquals(null, string);
        assertTrue(string.length() > 0);
    }

    // -------------------------------------------------------------------------------------------------------------
    private WebPageTestingResult getEmpty(){
        WebPageTestingResult webPageTestingResult = new WebPageTestingResult(getMockBase());
        webPageTestingResult.setTitle("");
        webPageTestingResult.setDescription("");
        webPageTestingResult.setHeadersH1(new ArrayList<>());
        webPageTestingResult.setImages(new ArrayList<>());
        webPageTestingResult.setLinks(new ArrayList<>());
        webPageTestingResult.setSuccessfull(true);
        return webPageTestingResult;
    }

    private WebPageLoadingResult getMockBase(){
        WebPageLoadingResult mock = new WebPageLoadingResult();
        mock.setResponseCode(200);
        mock.setTimeToFirstBit(1);
        mock.setTimeToFinish(2);
        mock.setUrlString("https://neoproduction.com");

        return mock;
    }

    private WebPageLoadingResult getMockImagesOnly(){
        WebPageLoadingResult mock = getMockBase();
        mock.setWebpage("<html><head></head><body>" +
                "<img src=\"a\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"b\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"c\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"d\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"e\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"f\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"g\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"h\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"i\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"k\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"l\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"m\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"n\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"o\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"p\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"q\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"r\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"s\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"t\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"u\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "</body></html>");
        return mock;
    }

    private WebPageLoadingResult getMockNormal(){
        WebPageLoadingResult mock = getMockBase();
        mock.setWebpage("<html><head></head><body>" +
                "<img src=\"a\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"b\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<img src=\"c\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "<a href=\"a\"/>" +
                "<a href=\"a\">Nobody reads this stuff really</a>" +
                "<a href=\"a\">Nobody reads this stuff</a>" +
                "<a href=\"a\">Do you? Really? :D</a>" +
                "<a href=\"a\"/>" +
                "<h1></h1>" +
                "this is <h1>HEEEEAAAAAAADEEEEEEEERRRR!!!!!</h1>" +
                "<h1>a humble header</h1>" +
                "<img src=\"\" alt=\"Some long text that won't be able to fit in the such a small container provided\"/>" +
                "</body></html>");
        return mock;
    }
}