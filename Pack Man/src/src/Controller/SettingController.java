package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.game.Start;

/**
 * Controller of setting page, with setting.fxml
 * 
 * @author YiXUE
 *
 */
public class SettingController {

	@FXML // fx:id="back"
	private Button back;
	@FXML
	private ComboBox<String> id_maze = new ComboBox<String>();
	@FXML
	private ComboBox<String> id_difficulty = new ComboBox<String>();
	@FXML
	public ColorPicker id_BackgroundColor = new ColorPicker();
	public ColorPicker id_WallColor = new ColorPicker();
	public static Color wallcolor = Color.CADETBLUE;
	public static Color backgroundcolor = Color.WHITE;
	public static String mazeType = "A";
	public static String difficultylevel = "Easy";

	/**
	 * goto start page
	 */
	public void backtostart()  {
		Stage originalstage = (Stage) back.getScene().getWindow();
		originalstage.close();
		Start mainstart = new Start();
		Stage theStage = new Stage();
		try {
			mainstart.start(theStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * color picker event: choose wall color
	 * 
	 */
	public void chooseWallColor()  {
		wallcolor = id_WallColor.getValue();
	}

	/**
	 * color picker event: choose background color
	 */
	public void chooseBackgroundColor() {
		backgroundcolor = id_BackgroundColor.getValue();
	}

	/**
	 * combobox event: choose maze type
	 */
	public void chooseMaze(){
		mazeType = id_maze.getValue();
	}

	/**
	 * combobox event: choose difficulty
	 * 
	 */
	public void chooseDifficulty() {
		difficultylevel = id_difficulty.getValue();
	}

	/**
	 * show choices in combobox
	 */
	@FXML
	public void initialize() {
		id_maze.getItems().addAll("A", "B");
		id_maze.getSelectionModel().selectFirst();
		id_difficulty.getItems().addAll("Easy", "Medium", "Hard");
		id_difficulty.getSelectionModel().selectFirst();
	}

}
