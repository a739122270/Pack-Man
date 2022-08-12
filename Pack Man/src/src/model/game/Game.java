package model.game;

import Controller.SettingController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.maze.Maze;

/**
 * Enter Game
 * 
 * @author YiXUE
 *
 */
public class Game extends Application {
	public static Color backgroundcolor;
	private GameManager gameManager;
	private Maze mazes;

	/**
	 * Show Game Interface and Start the Game
	 * 
	 * @throws Exception - class
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		Group root = new Group();
		Scene theScene = new Scene(root);

		// Set Style of Stage
		theStage.setScene(theScene);
		theStage.setTitle("Pacman");
		theStage.getIcons().add(new Image("./view/pacman.png"));
		theStage.setResizable(false);
		theStage.setAlwaysOnTop(true);

		// Set View Of Scene
		theScene.getStylesheets().add("view/stylesForGame.css");
		Canvas canvas = new Canvas(1225, 600);
		theScene.setFill(SettingController.backgroundcolor);
		root.getChildren().add(canvas);

		// Game Initialization
		gameManager = GameManager.getInstance(root);
		mazes = gameManager.maze;
		mazes.drawBoard(root, gameManager.cookieSet);
		gameManager.addCharacters();

		// Game KeyEvent Handler
		theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
		theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
		theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));
		theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.PauseGame(event));

		theStage.show();
	}

}
