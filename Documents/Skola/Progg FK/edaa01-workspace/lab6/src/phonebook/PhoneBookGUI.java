package phonebook;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Insets;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
		root.setBottom(makeQuit());
	}
	
	public Parent getRoot(){
		return root;
	}

	private HBox makeQuit(){
		HBox hbox = new HBox(); 
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    
	    Button buttonQuit = new Button("Quit");
	    buttonQuit.setOnAction(e -> quit());
	    buttonQuit.setPrefSize(100, 20);

		hbox.getChildren().addAll(buttonQuit);
		return hbox;
	}
	
	private MenuBar makeMenu(){
		
		final Menu menuEdit = new Menu("Edit");
		final MenuItem menuAdd = new MenuItem("Add");
		menuAdd.setOnAction(e -> add());
		final MenuItem menuRemove = new MenuItem("Remove Person");
		menuRemove.setOnAction(e -> remove());
		final MenuItem menuRemoveNumber = new MenuItem("Remove Number");
		menuRemoveNumber.setOnAction(e-> removeNumber());
		menuEdit.getItems().addAll(menuAdd,menuRemove, menuRemoveNumber);
	
		final Menu menuFind = new Menu("Find");
		final MenuItem menuFindNumber = new MenuItem("Find Number(s)");
		menuFindNumber.setOnAction(e->findNumber());
		final MenuItem menuFindName = new MenuItem("Find Name(s)");
		menuFindName.setOnAction(e-> findName());
		menuFind.getItems().addAll(menuFindNumber, menuFindName);	
		
		final Menu menuView = new Menu("View"); 
		final MenuItem menuShowAll = new MenuItem("Show All");
		menuShowAll.setOnAction(e->showAll());
		menuView.getItems().addAll((menuShowAll));
				
	    MenuBar menuBar = new MenuBar();
	    menuBar.getMenus().addAll(menuEdit, menuFind,menuView);
	 // menuBar.setUseSystemMenuBar(true);  // if you want operating system rendered menus, uncomment this line
	    menuBar.setStyle("-fx-background-color: #FFD700 ;");
		return menuBar;
	}

	private void add() {	
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Add Number");
		dialog.setHeaderText("");
		

	}
	private void remove() {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Remove Number");
		dialog.setHeaderText("Enter Nr to remove");
		dialog.setContentText("Input");
		Optional<String>result=dialog.showAndWait(); 

		String i = result.get();
		if(phoneBook.remove(i)){
			textArea.appendText("The contact " + i + " has been removed");
		} else{
			textArea.appendText("No such name in phonebook");
		}
		
	}
	private void removeNumber(){
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Remove Number");
		dialog.setHeaderText("Enter Nr to remove");
		dialog.setContentText("Input");
		Optional<String>result=dialog.showAndWait(); 
		String i = result.get();
//		if(phoneBook.removeNumber(name, number)){
//			textArea.appendText("The number " + number + " has been removed.");
		}
		
//	}
	private void findNumber(){
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Find Number");
		dialog.setHeaderText("Enter Name");
		dialog.setContentText("Input");
		Optional<String>result=dialog.showAndWait();
		String i = result.get();
		if(result.isPresent()){
			StringBuilder sb = new StringBuilder();
			for(String number : phoneBook.findNumber(i)){
				sb.append(number + "\t");
			}
			textArea.appendText(i +" has following number(s) " + sb.toString());	
		}	else {
			textArea.appendText("Please enter a valid name.");
		}
		
	}
	private void findName(){
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Find Name");
		dialog.setHeaderText("Enter Name");
		dialog.setContentText("Input");
		Optional<String>result=dialog.showAndWait(); 
		String i = result.get();
		if(result.isPresent()){
			StringBuilder sb = new StringBuilder();
			for(String name : phoneBook.findNames(i)){
				sb.append(name + "\t");
			}
			textArea.appendText("The number " + i + " belongs to " + sb.toString());
		} else {
			textArea.appendText("Please enter a valid number.");
		}
		
	}
	private void showAll(){
		textArea.appendText(phoneBook.toString());
	}
	private void quit(){
		Platform.exit();
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