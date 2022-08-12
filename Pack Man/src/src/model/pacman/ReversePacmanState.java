package model.pacman;

import javafx.scene.Group;
import model.game.GameManager;
import view.maze.Maze;

/**
 * Reverse state of pacman, reverse user's control of pacman, implements State
 * 
 * @author YiXUE
 *
 */
public class ReversePacmanState implements State {

	/**
	 * reverse user's control of pacman
	 * 
	 * @param currentNanoTime - current nano time
	 * @param direction       - pacman's direction
	 * @param maze            - the chosen maze
	 * @param pacman          - the generated pacman
	 * @param root            - the scene root
	 */
	@Override
	public void directiontype(long currentNanoTime, String direction, Maze maze, Pacman pacman, Group root) {
		GameManager gameManager = GameManager.getInstance(root);
		switch (direction) {
		case "right":
			if (!maze.isTouching(pacman.getCenterX() - pacman.getRadius(), pacman.getCenterY(), 15)) {
				pacman.setCenterX(pacman.getCenterX() - pacman.step);
				gameManager.checkCookieCoalition(pacman, "x");
				gameManager.checkGhostCoalition();
			}
			if (pacman.getCenterX() <= 2.5) {
				pacman.setCenterX(1225);
			}

			break;
		case "left":
			if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
				pacman.setCenterX(pacman.getCenterX() + pacman.step);
				gameManager.checkCookieCoalition(pacman, "x");
				gameManager.checkGhostCoalition();
			}
			if (pacman.getCenterX() >= 1222.5) {
				pacman.setCenterX(0);
			}
			break;
		case "down":
			if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
				pacman.setCenterY(pacman.getCenterY() - pacman.step);
				gameManager.checkCookieCoalition(pacman, "y");
				gameManager.checkGhostCoalition();
			}
			break;
		case "up":
			if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
				pacman.setCenterY(pacman.getCenterY() + pacman.step);
				gameManager.checkCookieCoalition(pacman, "y");
				gameManager.checkGhostCoalition();
			}
			break;
		}
	}
}
