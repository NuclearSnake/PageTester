import org.junit.Test;

import static org.junit.Assert.*;

public class SiteTesterTest {

    @Test
    public void testTesting_OnlyImages() {
        WebPageLoadingResult webPageLoadingResult = getMockImagesOnly();
        SiteTester siteTester = new SiteTester();
        siteTester.setLoadingResult(webPageLoadingResult);
        WebPageTestingResult testingResult = siteTester.test();

        assertNotEquals(null, testingResult.getLinks());
        assertNotEquals(null, testingResult.getHeadersH1());
        assertNotEquals(null, testingResult.getImages());

        assertEquals(0, testingResult.getLinks().size());
        assertEquals(0, testingResult.getHeadersH1().size());
        assertEquals(50, testingResult.getImages().size());
    }

    @Test
    public void testTesting_EmptyPage() {
        WebPageLoadingResult webPageLoadingResult = getMockEmptyPage();
        SiteTester siteTester = new SiteTester();
        siteTester.setLoadingResult(webPageLoadingResult);
        WebPageTestingResult testingResult = siteTester.test();

        assertNotEquals(null, testingResult.getLinks());
        assertNotEquals(null, testingResult.getHeadersH1());
        assertNotEquals(null, testingResult.getImages());

        assertEquals(0, testingResult.getLinks().size());
        assertEquals(0, testingResult.getHeadersH1().size());
        assertEquals(0, testingResult.getImages().size());
    }

    @Test
    public void testTesting_NoPage() {
        WebPageLoadingResult webPageLoadingResult = getMockBase();
        SiteTester siteTester = new SiteTester();
        siteTester.setLoadingResult(webPageLoadingResult);
        WebPageTestingResult testingResult = siteTester.test();

        assertNotEquals(null, testingResult.getLinks());
        assertNotEquals(null, testingResult.getHeadersH1());
        assertNotEquals(null, testingResult.getImages());

        assertEquals(0, testingResult.getLinks().size());
        assertEquals(0, testingResult.getHeadersH1().size());
        assertEquals(0, testingResult.getImages().size());
    }

    @Test
    public void testTesting_NormalPage() {
        WebPageLoadingResult webPageLoadingResult = getMockNormal();
        SiteTester siteTester = new SiteTester();
        siteTester.setLoadingResult(webPageLoadingResult);
        WebPageTestingResult testingResult = siteTester.test();

        assertNotEquals(null, testingResult.getLinks());
        assertNotEquals(null, testingResult.getHeadersH1());
        assertNotEquals(null, testingResult.getImages());

        assertEquals(5, testingResult.getLinks().size());
        assertEquals(3, testingResult.getHeadersH1().size());
        assertEquals(4, testingResult.getImages().size());
    }


    // -------------------------------------------------------------------------------------------------------------
    private WebPageLoadingResult getMockBase(){
        WebPageLoadingResult mock = new WebPageLoadingResult();
        mock.setResponseCode(200);
        mock.setTimeToFirstBit(1);
        mock.setTimeToFinish(2);
        mock.setUrlString("https://neoproduction.com");

        return mock;
    }

    private WebPageLoadingResult getMockEmptyPage(){
        WebPageLoadingResult mock = getMockBase();
        mock.setWebpage("<html><head></head><body></body></html>");
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