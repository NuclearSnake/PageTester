import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class SiteLoaderURLStream implements SiteLoader{
    @Override
    public String loadWebpage(String url) {
        return loadWithURLStream(url);
    }

    private String loadWithURLStream(String urlString){
        URL url;
        InputStream is = null;
        BufferedReader br;
        StringBuilder builder = new StringBuilder();
        String line = null;

        try {
            url = new URL(urlString);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }

        return builder.toString();
    }
}
