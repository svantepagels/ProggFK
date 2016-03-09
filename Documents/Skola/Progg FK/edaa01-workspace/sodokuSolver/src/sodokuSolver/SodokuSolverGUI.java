package sodokuSolver;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class SodokuSolverGUI {
	private SodokuSolver sodokuSolver;
	private BorderPane root;
	private OneLetterTextField[][] matrix;

	public SodokuSolverGUI(SodokuSolver sb) {
		sodokuSolver = sb;

		matrix = new OneLetterTextField[9][9];

		root = new BorderPane();
		root.setCenter(makeGrid());
		root.setBottom(makeButtons());
	}

	/**
	 * Returns the root of the GUI.
	 * 
	 * @return The root of the GUI.
	 */
	public Parent getRoot() {
		return root;
	}

	private TilePane makeGrid() {
		TilePane tilePane = new TilePane();

		tilePane.setPrefColumns(9);
		tilePane.setPrefRows(9);
		tilePane.setPadding(new Insets(3, 3, 3, 3));
		tilePane.setHgap(3);
		tilePane.setVgap(3);
		tilePane.setStyle("-fx-background-color: #336699;");
		tilePane.setMaxSize(292,265);
		tilePane.setMinSize(292,265);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				OneLetterTextField tf = new OneLetterTextField();
				tf.setPrefColumnCount(1);
				if (!(i / 3 == 1 || j / 3 == 1) || ((i / 3 == 1 && j / 3 == 1))) {
					tf.setStyle("-fx-background-color: #1FBED6;");
				}
				matrix[i][j] = tf;
				tilePane.getChildren().add(tf);
			}
		}
		return tilePane;
	}

	private HBox makeButtons() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(0, 3, 3, 3));
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(30);

		Button solveButton = new Button("Solve");
		solveButton.setOnAction(e -> solve());

		Button clearButton = new Button("Clear");
		clearButton.setOnAction(e -> clear());

		hbox.getChildren().addAll(clearButton, solveButton);

		return hbox;

	}

	private void solve() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String s = matrix[i][j].getText();
				if (s.length() > 0) {
					sodokuSolver.setValue(Integer.valueOf(s), i, j);
				} else {
					sodokuSolver.setValue(0, i, j);
				}
			}
		}
		if (!sodokuSolver.solve()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Sudoku not solvable.");
			alert.showAndWait();

		} else {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					matrix[i][j].setText(sodokuSolver.getValue(i, j));
				}
			}
		}

	}

	private void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				matrix[i][j].clear();
				sodokuSolver.setValue(0, i, j);
			}
		}

	}
}