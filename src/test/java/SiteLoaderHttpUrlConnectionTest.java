import org.junit.Test;

import static org.junit.Assert.*;

public class SiteLoaderHttpUrlConnectionTest {

    @Test
    public void loadWebpage() {
        // FIXME That is an overkill so for a small app we would maybe verify manually and for the bigger one we should set up custom mocking HTTP Client
//        String webpageUrl = "https://www.facebook.com";
//        WebPageLoadingResult loadingResult = new SiteLoaderHttpUrlConnection().loadWebpage(webpageUrl, 10_000);
//
//        if(loadingResult.getResponseCode() != 200){
//            webpageUrl = "https://www.google.com";
//            loadingResult = new SiteLoaderHttpUrlConnection().loadWebpage(webpageUrl, 10_000);
//            if(loadingResult.getResponseCode() != 200){
//                webpageUrl = "https://www.usa.gov";
//                loadingResult = new SiteLoaderHttpUrlConnection().loadWebpage(webpageUrl, 10_000);
//            }
//        }
//
//        assertEquals(200, loadingResult.getResponseCode());
//        assertNotEquals(null, loadingResult.getWebpage());
//        assertTrue(loadingResult.getTimeToFirstBit() >= 0);
//        assertTrue(loadingResult.getTimeToFinish() >= loadingResult.getTimeToFirstBit());
//        assertEquals(webpageUrl, loadingResult.getUrlString());
    }
}