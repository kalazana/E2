import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.w3c.dom.Text;

import java.awt.*;

public class AusgabeController {
    @FXML
    private GridPane suchleiste, liste;
    @FXML
    private Button hinzufuegen, reset;
    @FXML
    private void initialize(){
        hinzufuegen.setActionCommand((event)->{});
    }
}
