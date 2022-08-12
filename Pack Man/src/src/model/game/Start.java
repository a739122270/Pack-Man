package model.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * start page
 * 
 * @author YiXUE
 *
 */
public class Start extends Application {

	/**
	 * Set Interface of start page
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		Parent root1 = FXMLLoader.load(getClass().getResource("/view/start.fxml"));
		Scene startScene = new Scene(root1, 1243, 754);
		startScene.getStylesheets().add("view/styles.css");
		theStage.setScene(startScene);
		theStage.setTitle("Pacman");
		theStage.getIcons().add(new Image("./view/pacman.png"));
		theStage.setResizable(false);
		theStage.setAlwaysOnTop(true);
		theStage.show();
	}

	/**
	 * main
	 * 
	 * @param args	- run
	 */
	public static void main(String[] args) {
		launch(args);
	}
}