/* @author Fuad Aliev
 *  10.12.2018
 */
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class Controller {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBoxMain;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menu;

    @FXML
    private MenuItem menuItem;

    @FXML
    private HBox hBoxBackground;

    @FXML
    private HBox hBox;

    @FXML
    private HBox hBoxLeft;

    @FXML
    private VBox vBoxInside;

    @FXML
    private HBox hBoxInside;

    @FXML
    private Button buttonBack;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button buttonForward;

    @FXML
    private Label synonyms;

    @FXML
    private ListView<String> listSynonyms;

    @FXML
    private Button buttonSearchSynonyms;

    @FXML
    private VBox vBoxInside2;

    @FXML
    private HBox hBoxInside_2;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonSort;

    @FXML
    private Button buttonRemove;

    @FXML
    private Label medien;

    @FXML
    private ListView<String> listMedia;

    @FXML
    private Separator separator;

    @FXML
    private HBox hBox1;

    @FXML
    private VBox vBoxRight;

    @FXML
    private HBox hBoxRight;

    @FXML
    private Label suchbegriffe;

    @FXML
    private TextField textField;

    @FXML
    private Button buttonSearch;

    @FXML
    private WebView webView;

    @FXML
    private HBox hBoxRight_2;

    @FXML
    private Label labelB;

    @FXML
    private Label labelA;

    private Synonym synonym = new Synonym();
    private String selectedItemSynonym = null;
    private Medium selectedItemBuch = null;
    private WikiBooks book = null;
    private Zettelkasten zettelkasten = new Zettelkasten();
    private String sortierRichtung = "auf";
    private String urlName;

    private void search() {
        try {
            String search = textField.getText().trim().replace(" ", "_");

            if (!urlName.equals(search)) {

                webView.getEngine().load("https://de.wikibooks.org/wiki/" + search);

                searchSynonyms();
                refreshComboBox();
            }
            WikibooksParser books = new WikibooksParser();
            book = books.parse(search);
            setBookInformation(book);
        } catch (NullPointerException e) {
            System.out.println("Fehler bei der Suche!");
        }
    }

    private void searchSynonyms() {
        try {
            ObservableList<String> liste = synonym.synonymList(textField.getText(), 50);
            listSynonyms.getItems().clear();
            if (liste.size() == 0) {
                listSynonyms.getItems().add("<keine>");
                listSynonyms.setDisable(true);
                buttonSearchSynonyms.setDisable(true);

            } else {
                listSynonyms.setDisable(false);
                buttonSearchSynonyms.setDisable(false);
                listSynonyms.getItems().addAll(liste);
            }
        } catch (Exception e) {
            errorSynonym();
        }
    }

    private void refreshComboBox() {
        int indexselectetItem = comboBox.getSelectionModel().getSelectedIndex();
        if (comboBox.getItems().contains(textField.getText())) {
            comboBox.getItems().remove(textField.getText());
        }
        if (indexselectetItem > 0) {
            comboBox.getItems().remove(0, indexselectetItem);

        }
        comboBox.getItems().add(0, textField.getText());
        comboBox.getSelectionModel().select(0);
    }

    private void refreshButtons() {
        int indexselectetItem = comboBox.getSelectionModel().getSelectedIndex();

        if (indexselectetItem < 1) {
            buttonForward.setDisable(true);
        } else {
            buttonForward.setDisable(false);
        }

        if (indexselectetItem >= comboBox.getItems().size() - 1) {
            buttonBack.setDisable(true);
        } else {
            buttonBack.setDisable(false);
        }
    }

    private void add() {
        try {
            if (book != null) {
                zettelkasten.addMedium(book);
                mediaList();
                textField.clear();
            } else {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            errorWikiBooks();
        }
    }

    private void sort() {
        zettelkasten.sort(sortierRichtung);
        mediaList();
        if (sortierRichtung.equals("ab")) {
            sortierRichtung = "auf";
        } else {
            sortierRichtung = "ab";
        }
    }

    @SuppressWarnings("unused")
    private void remove() throws duplicateException {
        if (selectedItemBuch != null) {
            zettelkasten.dropMedium("w", selectedItemBuch.getTitel());
            mediaList();
        }
    }

    private void mediaList() {
        listMedia.getItems().clear();
        for (Medium medium : zettelkasten.getMedium_Arr()) {
            listMedia.getItems().add(medium.getTitel());
        }
    }

    private void setBookInformation(Medium medium) {
        if (medium != null) {
            labelB.setText("Letzte Bearbeitung: " + book.getVerfasser());
            labelA.setText("Letzte Aenderung: " + WikiBooks.convertTime(book.getAenderungsDatum()));
        } else {
            labelB.setText("Letzter Bearbeiter:");
            labelA.setText("Letzte Änderung:");
        }
    }

    private void showDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.getDialogPane().setMinWidth(700);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(null);
        alert.setContentText(
                "Alle redaktionellen Inhalte stammen von den Internetseiten der Projekte Wikibooks und Wortschatz."
                        + System.lineSeparator() + System.lineSeparator()
                        + "Die von Wikibooks bezogenen Inhalte unterliegen der GNU Free Documentation License und damit auch dieses Programm. Der Text der GNU FDL ist unter"
                        + System.lineSeparator() + System.lineSeparator()
                        + "http://de.wikipedia.org/wiki/Wikipedia:GNU_Free_Documentation_License"
                        + System.lineSeparator() + System.lineSeparator() + "verfügbar." + System.lineSeparator()
                        + "Die von Wortschatz (http://wortschatz.uni-leipzig.de/) bezogenen Inhalte sind urheberrechtlich geschützt."
                        + System.lineSeparator()
                        + "Sie werden hier für wissenschaftliche Zwecke eingesetzt und dürfen darüber hinaus in keiner Weise genutzt werden."
                        + System.lineSeparator()
                        + "Dieses Programm ist nur zur Nutzung durch den Programmierer selbst gedacht."
                        + System.lineSeparator()
                        + "Dieses Programm dient der Demonstration und dem Erlernen von Prinzipien der Programmierung mit Java."
                        + System.lineSeparator()
                        + "Eine Verwendung des Programms für andere Zwecke verletzt die Urheberrechte der Originalautoren der redaktionellen Inhalte und ist daher untersagt.");
        alert.showAndWait();
    }

    private void errorWikiBooks() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("");
        alert.getDialogPane().setMinWidth(200);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(null);
        alert.setContentText("Konnte Wikibooks nicht erreichen");
        alert.showAndWait();
    }

    private void errorSynonym() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("");
        alert.getDialogPane().setMinWidth(200);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(null);
        alert.setContentText("Konnte Synonymserver nicht erreichen");
        alert.showAndWait();
    }

    // Event on Button F1
    private void functionButtonF1(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F1) {
            showDialog();
        }
    }

    public void initialize() {

        anchorPane.setOnKeyPressed(e -> {
            functionButtonF1(e);
        });

        refreshButtons();
        webView.getEngine().load("https://de.wikibooks.org/wiki");
        textField.setText("Java Standard");

        // Menue Item
        menuItem.setOnAction(e -> {
            showDialog();
        });

        // Button Suche
        buttonSearch.setOnAction(e -> {
            search();

        });

        // Enter druecken, wenn das TextFeld selektiert ist
        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                search();
            }
        });

        textField.setOnMouseClicked(e -> {
            textField.clear();
        });

        // Button Hinzufuegen
        buttonAdd.setOnAction(e -> {
            add();
        });

        // Button Sortieren
        buttonSort.setOnAction(e -> {
            sort();
        });

        // Button loeschen
        buttonRemove.setDisable(true);

        // Button Synonyme Suchen
        buttonSearchSynonyms.setOnAction(e -> {
            refreshComboBox();
            searchSynonyms();
            textField.setText(selectedItemSynonym);
        });


        // beim selektieren eines Items in der Liste f�r Synonyme
        listSynonyms.setOnMouseClicked(e -> {
            selectedItemSynonym = listSynonyms.getSelectionModel().getSelectedItems().get(0);
            if (e.getClickCount() == 2) {
                textField.setText(selectedItemSynonym);
                refreshComboBox();
            }
        });

        // wenn die ComboBox selektiert wird
        comboBox.setOnAction(e -> {
            selectedItemSynonym = comboBox.getSelectionModel().getSelectedItem();
            textField.setText(selectedItemSynonym);
            searchSynonyms();
            comboBox.getSelectionModel().select(selectedItemSynonym);
            refreshButtons();
            webView.getEngine().load("https://de.wikibooks.org/wiki/" + selectedItemSynonym);
        });

        webView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {

            if (Worker.State.FAILED.equals(newValue)) {
                errorWikiBooks();
                errorSynonym();
            }
            if (Worker.State.SCHEDULED.equals(newValue)) {
                if (!webView.getEngine().getLocation().contains("https://de.wikibooks.org/")) {
                    webView.getEngine().load("https://de.wikibooks.org/wiki/");
                }
            }

            if (Worker.State.SUCCEEDED.equals(newValue)) {
                if (webView.getEngine().getLocation().contains("https://de.wikibooks.org/wiki/")) {
                    urlName = webView.getEngine().getLocation();
                    urlName = urlName.replace("https://de.wikibooks.org/wiki/", "");
                    textField.setText(urlName);
                    search();
                } else {
                    urlName = webView.getEngine().getLocation();
                    urlName = urlName.replace("https://de.wikibooks.org/w/index.php?search=", "");

                    if (urlName.contains("&title")) {
                        urlName = urlName.substring(0, urlName.indexOf("&title"));
                    }
                    textField.setText(urlName);
                    search();
                }
            }
        });

        // Button Zurueck
        buttonBack.setOnAction(e -> {
            comboBox.getSelectionModel().select(comboBox.getSelectionModel().getSelectedIndex() + 1);

        });

        // Button Vor
        buttonForward.setOnAction(e -> {
            comboBox.getSelectionModel().select(comboBox.getSelectionModel().getSelectedIndex() - 1);
        });
    }
}
