package sodokuSolver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private SodokuSolver ss;
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ss = new SodokuSolver();
		SodokuSolverGUI gui = new SodokuSolverGUI(ss);

		Scene scene = new Scene(gui.getRoot());
		primaryStage.setTitle("Sodoko");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}