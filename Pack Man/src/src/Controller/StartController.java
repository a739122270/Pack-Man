package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.game.Game;
import model.game.SettingPage;

/**
 * controller for start page, with start.fxml
 * 
 * @author YiXUE
 *
 */
public class StartController {
	@FXML // input user name
	public TextField UserName;
	public static String username;

	@FXML // goto setting page
	private Button setup;

	@FXML // goto game
	private Button game;

	/**
	 * goto game page
	 * 
	 */
	public void gotogame() {
		// input user name
		username = UserName.getText();

		Stage originalstage = (Stage) game.getScene().getWindow();
		originalstage.close();
		Game gamestart = new Game();
		Stage theStage = new Stage();
		try {
			gamestart.start(theStage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * goto setting page
	 */
	public void gotosetup(){
		Stage originalstage = (Stage) setup.getScene().getWindow();
		originalstage.close();
		SettingPage mainstart = new SettingPage();
		Stage theStage = new Stage();
		try {
			mainstart.start(theStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {

	}

}
