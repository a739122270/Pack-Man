package model.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * high score page
 * 
 * @author YiXUE
 *
 */
public class ShowScore extends Application {

	/**
	 * Set Interface of high score Page
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		Parent root1 = FXMLLoader.load(getClass().getResource("/view/HighScore.fxml"));
		Scene startScene = new Scene(root1, 1243, 754);
		startScene.setFill(Color.BLACK);
		theStage.setScene(startScene);
		theStage.setTitle("Pacman");
		theStage.getIcons().add(new Image("./view/pacman.png"));
		theStage.setResizable(false);
		theStage.setAlwaysOnTop(true);
		theStage.show();
	}

}
