import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * <h1>A class to load a webpage</h1>
 *
 * Uses {@link HttpURLConnection} in order to load a webpage <p>
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-22
 */
public class SiteLoaderHttpUrlConnection implements SiteLoader{
    @Override
    public WebPageLoadingResult loadWebpage(String url, int timeoutMS) throws SocketTimeoutException {
        return loadWithURLStream(url, timeoutMS);
    }

    private WebPageLoadingResult loadWithURLStream(String urlString, int timeoutMS) throws SocketTimeoutException {
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
            httpURLConnection.setConnectTimeout(timeoutMS);
            final int responseCode = httpURLConnection.getResponseCode();

            webPageLoadingResult.setTimeToFirstBit(timeMeasurer.getElapsedMS());
            webPageLoadingResult.setResponseCode(responseCode);

            if (responseCode != 200) {
                return webPageLoadingResult;
            }

            inputStream = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(inputStream));

            if ((line = br.readLine()) != null) {
                builder.append(line);

                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }

                webPageLoadingResult.setTimeToFinish(timeMeasurer.getElapsedMS());
                webPageLoadingResult.setWebpage(builder.toString());
            }
        } catch (SocketTimeoutException ste){
            ste.printStackTrace();
            throw ste;
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
