package memo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import memo.Memo;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Memo memo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image memoImage = new Image(this.getClass().getResourceAsStream("/images/Memo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Memo instance */
    public void setMemo(Memo memo) {
        this.memo = memo;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Memo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = memo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMemoDialog(response, memoImage)
        );
        userInput.clear();
    }
}
