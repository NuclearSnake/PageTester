import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {
    /** Contains the URL of a webpage to test */
    public TextField tfUrl;

    public WebView wvResult;
    public TextArea taResult;

    public Label lbResult;

    private WebPageTestingResult testingResult;

    // ---------------- Action Events -----------------
    @FXML
    public void test(ActionEvent actionEvent) {
        testConnection(tfUrl.getText());
    }

    @FXML
    public void onEnter(ActionEvent actionEvent) {
        testConnection(tfUrl.getText());
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        saveReport();
    }

    @FXML
    public void load(ActionEvent actionEvent) {
        loadReport();
    }

    // ---------------- Custom methods -----------------

    /**
     * Saves the last created test report to a file
     */
    private void saveReport(){
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

    /**
     * Loads the last saved test report from a file and tries to display it
     */
    private void loadReport(){
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

    /**
     * Do the testing of a webpage on the URL provided
     * @param url of the webpage to test
     */
    private void testConnection(String url){
        WebPageLoadingResult loadingResult = new SiteLoaderHttpUrlConnection().loadWebpage(url);
        SiteTester siteTester = new SiteTester();
        siteTester.setLoadingResult(loadingResult);
        testingResult = siteTester.test();

        wvResult.getEngine().loadContent(new HtmlDisplayer().getResultText(testingResult));
        taResult.setText(new SimpleResultDisplayer().getResultText(testingResult));
    }
}
