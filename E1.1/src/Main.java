import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        try {
            GridPane root = new GridPane();
            Scene scene = new Scene(root, 400, 400);
            TextField textField1 = new TextField();
            textField1.setPromptText("Schreibehier");
            TextField textField2 = new TextField();
            textField2.setPromptText("Schreibehier");
            final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
                public void handle(final KeyEvent keyEvent) {
                    if (keyEvent.getCode() != KeyCode.F1 &&keyEvent.getCode() != KeyCode.F2 && keyEvent.getCode() != KeyCode.F3) {
                        textField1.setEditable(false);
                        textField2.setEditable(false);
                        textField2.clear();
                    } else if (keyEvent.getCode() == KeyCode.F1) {
                        textField2.clear();
                        textField2.setText("F1");
                    } else if (keyEvent.getCode() == KeyCode.F2) {
                        textField2.clear();
                        textField2.setText("F2");
                    }else if (keyEvent.getCode() == KeyCode.F3){
                        textField2.clear();
                        textField2.setText("F3");
                    }
                    keyEvent.consume();
                }
            }

                    ;
            textField1.setOnKeyPressed(keyEventHandler);
            textField2.setOnKeyPressed(keyEventHandler);
            root.setConstraints(textField1,0,0);
            root.setConstraints(textField2,0,1);
            root.getChildren().

                    addAll(textField1, textField2);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(
                Exception e)

        {
            e.printStackTrace();
        }
    }

    public static void main (String[]args){
        launch(args);
    }
}