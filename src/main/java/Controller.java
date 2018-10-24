import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    public void test(ActionEvent actionEvent) {
//        Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + selection + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//        alert.showAndWait();
//
//        if (alert.getResult() == ButtonType.YES) {
//            //do stuff
//        }
        String urlString = tfUrl.getText();
        testConnection(urlString);
    }

    public void testConnection(String url){
        SiteTester siteTester = new SiteTester(new SiteLoaderHttpUrlConnection(), new SimpleResultDisplayer());
        String result = siteTester.test(url);
//        lbResult.setText();

        final int size = 150;
        final String CSS =
                ".Container {\n" +
                        "   width: 100%;\n" +
//                        "   border:1px solid purple;\n" +
                        "   text-align: center;\n" +
                        "}\n" +
                        ".Content\n" +
                        "{\n" +
                        "   width: "+size+"px;\n" +
                        "   height: "+size+";\n" +
//                        "   border:1px solid red;\n" +
                        "   display: inline-block;\n" +
                        "   vertical-align: middle;\n" +
                        "}" +
                        ".Content img\n" +
                        "{\n" +
                        "   max-width: "+(size-10)+"px;\n" +
                        "   width: "+(size-10)+"px;\n" +
                        "   height: auto;\n" +
                        "}";

        wvResult.getEngine().loadContent(result);
        wvResult.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                Document doc = wvResult.getEngine().getDocument() ;
                Element styleNode = doc.createElement("style");
                Text styleContent = doc.createTextNode(CSS);
                styleNode.appendChild(styleContent);
                doc.getDocumentElement().getElementsByTagName("head").item(0).appendChild(styleNode);

                wvResult.getEngine().executeScript("document.documentElement.innerHTML");
            }
        });
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
