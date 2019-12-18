/* @author Fuad Aliev
 *  10.12.2018
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // BorderPane root = new BorderPane();
            Parent root = FXMLLoader
                    .load(getClass()
                            .getResource("E_2neu.fxml"));


            Scene scene = new Scene(root);
//		      scene.getStylesheets()
//		        .add(getClass()
//		        .getResource("application.css")
//		        .toExternalForm());


            primaryStage.setTitle("Aufgabenblatt E");
            primaryStage.setMinWidth(1200);
            primaryStage.setMinHeight(600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}