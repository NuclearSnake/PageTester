import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SiteLoaderHttpUrlConnection implements SiteLoader{
    @Override
    public WebPageLoadingResult loadWebpage(String url) {
        return loadWithURLStream(url);
    }

    private WebPageLoadingResult loadWithURLStream(String urlString){
        URL url;
        InputStream inputStream = null;
        BufferedReader br;
        StringBuilder builder = new StringBuilder();
        String line = null;
        WebPageLoadingResult webPageLoadingResult = new WebPageLoadingResult();
        webPageLoadingResult.setUrlString(urlString);

        try {
            url = new URL(urlString);

            TimeMeasurer timeMeasurer = new TimeMeasurer();
            timeMeasurer.setStartNow();

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10_000);
            final int responseCode = httpURLConnection.getResponseCode();

            webPageLoadingResult.setTimeToFirstBit(timeMeasurer.getElapsedMS());
            webPageLoadingResult.setResponseCode(responseCode);

            if(responseCode != 200) {
                return webPageLoadingResult;
            }

            inputStream = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(inputStream));

            if((line = br.readLine()) != null){
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
                if (inputStream != null) inputStream.close();
            } catch (IOException ioe) {
                // nothing to do here
            }
        }

        return webPageLoadingResult;
    }
}
