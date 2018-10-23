import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    public TextField tfUrl;
    public Label lbResult;

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
        lbResult.setText(siteTester.test(url));
    }
}
