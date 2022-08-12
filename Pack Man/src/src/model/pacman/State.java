package model.pacman;

import javafx.scene.Group;
import view.maze.Maze;

/**
 * State Pattern, Interface
 * 
 * @author YiXUE
 *
 */
public interface State {

	/**
	 * abstract method
	 * 
	 * @param currentNanoTime - current nano time
	 * @param direction       - pacman's direction
	 * @param maze            - the chosen maze
	 * @param pacman          - the generated pacman
	 * @param root            - the scene root
	 */
	public abstract void directiontype(long currentNanoTime, String direction, Maze maze, Pacman pacman, Group root);
}
