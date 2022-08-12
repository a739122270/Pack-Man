package model.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Setting Page
 * 
 * @author YiXUE
 *
 */
public class SettingPage extends Application {

	/**
	 * Set Interface of Setting Page
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		Parent root1 = FXMLLoader.load(getClass().getResource("/view/setting.fxml"));
		Scene startScene = new Scene(root1, 1243, 754);
		startScene.getStylesheets().add("view/styles.css");
		theStage.setScene(startScene);
		theStage.setTitle("Pacman");
		theStage.getIcons().add(new Image("./view/pacman.png"));
		theStage.setResizable(false);
		theStage.setAlwaysOnTop(true);
		theStage.show();
	}

}