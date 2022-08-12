package model.pacman;

import javafx.scene.Group;
import view.maze.Maze;

/**
 * Switch state of pacman
 * 
 * @author YiXUE
 *
 */
public class SwitchState {
	private State state;

	/**
	 * Constructor, set default state
	 */
	public SwitchState() {
		NormalPacmanState normal = new NormalPacmanState();
		state = normal;
	}

	/**
	 * Set new state of pacman
	 * 
	 * @param state	set state of pacman
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * get state of pacman
	 * 
	 * @return	return state of pacman
	 */
	public State getState() {
		return state;
	}

	/**
	 * execute direction animation according to current state
	 * 
	 * @param currentNanoTime - current nano time
	 * @param direction       - keyboard code
	 * @param maze            - chosen maze
	 * @param pacman          - generated pacman
	 * @param root            - scene root
	 */
	public void directiontype(long currentNanoTime, String direction, Maze maze, Pacman pacman, Group root) {
		getState().directiontype(currentNanoTime, direction, maze, pacman, root);

	}
}
