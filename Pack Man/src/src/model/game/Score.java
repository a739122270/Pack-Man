package model.game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Define Scoreboard
 * 
 * @author YiXUE
 *
 */
public class Score {

	public Text score;
	public Text lifes;

	/**
	 * Set style of scoreboard
	 * 
	 * @param root - scene root
	 */
	Score(Group root) {
		this.score = new Text(BarObstacle.THICKNESS * 4, BarObstacle.THICKNESS * 28, "Score: 0");
		this.lifes = new Text(BarObstacle.THICKNESS * 14, BarObstacle.THICKNESS * 28, "Lifes: 3");
		score.setFill(Color.MAGENTA);
		score.setFont(Font.font("Arial", 30));

		lifes.setFill(Color.MAROON);
		lifes.setFont(Font.font("Arial", 30));

		root.getChildren().add(score);
		root.getChildren().add(lifes);
	}
}
