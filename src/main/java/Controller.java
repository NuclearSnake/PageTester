import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import sun.nio.cs.UTF_32;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Controller {
    public TextField tfUrl;
    public Label lbResult;
    public WebView wvResult;
    public TextArea taResult;

    private WebPageTestingResult testingResult;

    public void test(ActionEvent actionEvent) {
        String urlString = tfUrl.getText();
        testConnection(urlString);
    }

    public void testConnection(String url){
        WebPageLoadingResult loadingResult = new SiteLoaderHttpUrlConnection().loadWebpage(url);
        SiteTester siteTester = new SiteTester();
        siteTester.setLoadingResult(loadingResult);
        testingResult = siteTester.test();

        wvResult.getEngine().loadContent(new HtmlDisplayer().getResultText(testingResult));
        taResult.setText(new SimpleResultDisplayer().getResultText(testingResult));
    }

    @FXML
    public void onEnter(ActionEvent actionEvent) {
        testConnection(tfUrl.getText());
    }

    public void save(ActionEvent actionEvent) {
        String path = "report.txt";
        try {
            if(testingResult == null)
                Files.write( Paths.get(path), new byte[0]);
            else {
                Files.write(Paths.get(path), new Gson().toJson(testingResult).getBytes(StandardCharsets.UTF_16));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(ActionEvent actionEvent) {
        String path = "report.txt";
        try {
            BufferedReader reader = Files.newBufferedReader( Paths.get(path) , StandardCharsets.UTF_16);
            String line;
            StringBuilder builder = new StringBuilder();
            while((line = reader.readLine()) != null){
                builder.append(line);
            }

            if(builder.length() == 0)
                return;

            Gson gson = new Gson();
            testingResult = gson.fromJson(builder.toString(), WebPageTestingResult.class);

            wvResult.getEngine().loadContent(new HtmlDisplayer().getResultText(testingResult));
            taResult.setText(new SimpleResultDisplayer().getResultText(testingResult));
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
