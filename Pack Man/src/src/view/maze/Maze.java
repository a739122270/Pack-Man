package view.maze;

import javafx.scene.Group;
import model.cookies.Cookie;
import model.game.BarObstacle;

import java.util.HashSet;
import java.util.Set;

/**
 * Parent class for mazes
 * 
 * @author YiXUE
 *
 */
public abstract class Maze {

	public Set<BarObstacle> obstacles;

	/**
	 * Constructor
	 */
	Maze() {
		obstacles = new HashSet<>();
	}

	/**
	 * Checks if point is touching obstacles
	 * 
	 * @param x       - position for horizontal coordinate
	 * @param y       - position for vertical coordinate
	 * @param padding - padding
	 * @return - whether is touching
	 */
	public Boolean isTouching(double x, double y, double padding) {
		for (BarObstacle barObstacle : obstacles) {
			if (x >= barObstacle.getX() - padding && x <= barObstacle.getX() + padding + barObstacle.getWidth()
					&& y >= barObstacle.getY() - padding
					&& y <= barObstacle.getY() + padding + barObstacle.getHeight()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * lets you know if there's an obstacle in the current coordinate
	 * 
	 * @param fromX - original horizontal coordinate
	 * @param toX   - move to x horizontal coordinate
	 * @param fromY - original vertical coordinate
	 * @param toY   - move to x vertical coordinate
	 * @return - whether has obstacle
	 */
	public Boolean hasObstacle(double fromX, double toX, double fromY, double toY) {
		boolean isTouching = false;
		for (double i = fromX; i < toX; i++) {
			for (double j = fromY; j < toY; j++) {
				if (this.isTouching(i, j, 0))
					isTouching = true;
			}
		}
		return isTouching;
	}

	/**
	 * Abstract method for creating a maze
	 * 
	 * @param root - scene root
	 */
	public abstract void CreateMaze(Group root);

	/**
	 * Abstract method for drawing the maze
	 * 
	 * @param root      - scene root
	 * @param cookieSet - cookies
	 */
	public abstract void drawBoard(Group root, Set<Cookie> cookieSet);

}
