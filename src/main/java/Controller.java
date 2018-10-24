import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Controller {
    public TextField tfUrl;
    public Label lbResult;
    public WebView wvResult;
    public TextArea taResult;

    public void test(ActionEvent actionEvent) {
        String urlString = tfUrl.getText();
        testConnection(urlString);
    }

    public void testConnection(String url){
        WebPageLoadingResult loadingResult = new SiteLoaderHttpUrlConnection().loadWebpage(url);
        SiteTester siteTester = new SiteTester();
        siteTester.setLoadingResult(loadingResult);
        WebPageTestingResult testingResult = siteTester.test();

        wvResult.getEngine().loadContent(new HtmlDisplayer().getResultText(testingResult));
        taResult.setText(new SimpleResultDisplayer().getResultText(testingResult));
    }

    @FXML
    public void onEnter(ActionEvent actionEvent) {
        testConnection(tfUrl.getText());
    }

//    public static GridPane table(int rows){
//        GridPane table = new GridPane();
//
//        for(int i=0; i<rows; i++){
//            TextField textField = new TextField();
//            textField.setAlignment(Pos.CENTER);
//            CheckBox checkBox = new CheckBox("Check Box");
//            checkBox.setTextFill(Color.WHITE);
//            checkBox.setAlignment(Pos.CENTER);
//            DatePicker datePicker = new DatePicker();
//
//            //add them to the GridPane
//            table.add(textField, 0, i); //  (child, columnIndex, rowIndex)
//            table.add(checkBox , 1, i);
//            table.add(datePicker,2, i);
//
//            // margins are up to your preference
//            GridPane.setMargin(textField, new Insets(5));
//            GridPane.setMargin(checkBox, new Insets(5));
//            GridPane.setMargin(datePicker, new Insets(5));
//        }
//        table.setAlignment(Pos.CENTER);
//
//        return table;
//
//    }
}
