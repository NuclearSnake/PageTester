import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.exit;

public class Controller {
    /** Contains the URL of a webpage to test */
    public TextField tfUrl;

    public WebView wvResult;
    public TextArea taResult;

    public Label lbResult;
    public MenuItem miSave;
    public MenuItem miLoad;
    public ProgressBar pbLoading;

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

    // ---------------- Custom methods -----------------

    /**
     * Saves the last created test report to a file
     * @param absolutePath
     */
    private void saveReport(String absolutePath){
        String path = absolutePath;
        if(!path.endsWith(".rprt"))
            path += ".rprt";
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
    private void loadReport(String path){
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

    Timeline animationTimeline;

    /**
     * Do the testing of a webpage on the URL provided
     * @param url of the webpage to test
     */
    private void testConnection(String url){
        final int timeoutSec = 10;

        pbLoading.setProgress(0);
        pbLoading.setVisible(true);

        animationTimeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(pbLoading.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(timeoutSec),
                        new KeyValue(pbLoading.progressProperty(), 1)
                )
        );

        animationTimeline.playFromStart();

        Task<Void> taskLoad = new Task<Void>() {
            @Override
            protected Void call() {
                WebPageLoadingResult loadingResult = null;
                try {
                    loadingResult = new SiteLoaderHttpUrlConnection().loadWebpage(url, timeoutSec * 1000);
                } catch (SocketTimeoutException ste){
                    ste.printStackTrace();
                    this.failed();
                }
                SiteTester siteTester = new SiteTester();
                siteTester.setLoadingResult(loadingResult);
                testingResult = siteTester.test();

                return null;
            }
        };
        taskLoad.setOnFailed(event -> {
                    animationTimeline.stop();
                    pbLoading.setProgress(0);
                    pbLoading.setVisible(false);
                    new Alert(Alert.AlertType.ERROR, "Timeout reached!").showAndWait();
//                    wvResult.getEngine().loadContent(new HtmlDisplayer().getResultText(testingResult));
//                    taResult.setText(new SimpleResultDisplayer().getResultText(testingResult));
                });
        taskLoad.setOnSucceeded(event -> {
            animationTimeline.stop();
            pbLoading.setProgress(1);
            wvResult.getEngine().loadContent(new HtmlDisplayer().getResultText(testingResult));
            taResult.setText(new SimpleResultDisplayer().getResultText(testingResult));

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    pbLoading.setVisible(false);
                }
            });
        });
        Thread loadingThread = new Thread(taskLoad);
        loadingThread.setDaemon(true);
        loadingThread.start();
    }

    private String getVersion(){
        return "1.0.3";
    }

    private String getReleaseDate(){
        return "24.10.18";
    }

    @FXML
    public void onMenuAbout(ActionEvent actionEvent) {
        ButtonType okBtn = new ButtonType("OK");
        final String info = "Author: Makarenko George\nLatest release date: "+getReleaseDate();
        Alert a = new Alert(Alert.AlertType.INFORMATION, info, okBtn);
        a.setTitle("About");
        a.setHeaderText("WebPagesParser v"+getVersion());
        a.setResizable(false);
        a.showAndWait().ifPresent(response -> {
            if (response == okBtn) {}
        });
    }

    @FXML
    public void onMenuSave(ActionEvent actionEvent) {
        if(testingResult == null){
            new Alert(Alert.AlertType.ERROR, "No tests were performed!").showAndWait();
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Report");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Reports", "*.rprt"),
                new FileChooser.ExtensionFilter("ALL", "*.*")
        );
        // Strategical reserve:
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showSaveDialog(miLoad.getParentPopup().getScene().getWindow());
        if(file != null)
            saveReport(file.getAbsolutePath());

    }

    @FXML
    public void onMenuLoad(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Report");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Reports", "*.rprt"),
                new FileChooser.ExtensionFilter("ALL", "*.*")
                );
        // Strategical reserve:
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(miLoad.getParentPopup().getScene().getWindow());
        if(file != null)
            loadReport(file.getAbsolutePath());
    }

    @FXML
    public void onMenuExit(ActionEvent actionEvent) {
        exit(0);
    }
}
