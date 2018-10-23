import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class SiteLoaderURLStream implements SiteLoader{
    @Override
    public WebPageLoadingResult loadWebpage(String url) {
        return loadWithURLStream(url);
    }

    private WebPageLoadingResult loadWithURLStream(String urlString){
        URL url;
        InputStream is = null;
        BufferedReader br;
        StringBuilder builder = new StringBuilder();
        String line = null;
        WebPageLoadingResult webPageLoadingResult = new WebPageLoadingResult();

        try {
            url = new URL(urlString);

            TimeMeasurer timeMeasurer = new TimeMeasurer();
            timeMeasurer.setStartNow();

            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            if((line = br.readLine()) != null){
                webPageLoadingResult.setTimeToFirstBit(timeMeasurer.getElapsedMS());
                builder.append(line);

                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }

                webPageLoadingResult.setTimeToFinish(timeMeasurer.getElapsedMS());
                webPageLoadingResult.setWebpage(builder.toString());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to do here
            }
        }

        return webPageLoadingResult;
    }
}
