package phonebook;

import java.util.Optional;

import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class PhoneBookGUI{
	private PhoneBook phoneBook;
	private BorderPane root;
	private TextArea textArea;
	
	public PhoneBookGUI(PhoneBook pb) {
		phoneBook = pb;

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setPrefColumnCount(50);
		textArea.setPrefRowCount(20);
		
		root = new BorderPane();
		root.setTop(makeMenu());
		root.setCenter(textArea);
		
	}
	
	public Parent getRoot(){
		return root;
	}

	
	private MenuBar makeMenu(){
		final Menu menuEdit = new Menu("Edit");
		final MenuItem menuAdd = new MenuItem("Add");
		menuAdd.setOnAction(e -> add());
		final MenuItem menuRemove = new MenuItem("Remove Person");
		menuRemove.setOnAction(e -> remove());
		menuEdit.getItems().addAll(menuAdd, menuRemove);
	
		final Menu menuFind = new Menu("Find");
		
	    MenuBar menuBar = new MenuBar();
	    menuBar.getMenus().addAll(menuEdit, menuFind);
	 // menuBar.setUseSystemMenuBar(true);  // if you want operating system rendered menus, uncomment this line
		return menuBar;
	}

	private void add() {	
		
	}
	
	private void remove() {
		
	}
	
	private Optional<String> oneInputDialog(String title, String headerText, String label) {
		TextInputDialog dialog = new TextInputDialog();
		// add content to dialog
		return dialog.showAndWait();
	}
	
	private Optional<String[]> twoInputsDialog(String title, String headerText, String[] labels) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setResizable(true);
		Label label1 = new Label(labels[0] + ':');
		Label label2 = new Label(labels[1] + ':');
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf1, 2, 1);
		grid.add(label2, 1, 2);
		grid.add(tf2, 2, 2);
		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeCancel, buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, String>() {
			@Override
			public String call(ButtonType b) {
				String inputs = null;
				if (b == buttonTypeOk) {
					inputs = tf1.getText() + ":" + tf2.getText();
					
				}
				return inputs;
			}
		});
		tf1.requestFocus();

		Optional<String> result = dialog.showAndWait();
		String[] input = null;
		if (result.isPresent()) {
			input = result.get().split(":");
		}
		return Optional.ofNullable(input);
	}
}
