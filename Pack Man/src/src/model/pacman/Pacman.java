package model.pacman;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import view.maze.Maze;

/**
 * Define pacman, implement State
 * 
 * @author YiXUE
 *
 */
public class Pacman extends Circle implements State {
	public double step;

	/**
	 * Constructor
	 * 
	 * @param x     - position for horizontal coordinate
	 * @param y     - position for vertical coordinate
	 * @param image - image of pacman
	 */
	public Pacman(double x, double y, Image image) {
		this.setCenterX(x);
		this.setCenterY(y);
		this.setRadius(25);
		this.step = 5;
		this.setFill(new ImagePattern(image));
	}

	/**
	 * Creates an animation of the movement.
	 * 
	 * @param direction       - keyboard code
	 * @param maze            - chosen maze
	 * @param pacman          - generated pacman
	 * @param root            - scene root
	 * @param switchstate     - switch state of pacman
	 * @return - animation
	 */
	public AnimationTimer createAnimation(String direction, Maze maze, Pacman pacman, Group root,
			SwitchState switchstate) {
		return new AnimationTimer() {
			public void handle(long currentNanoTime) {
				switchstate.directiontype(currentNanoTime, direction, maze, pacman, root);
			}
		};
	}

	@Override
	public void directiontype(long currentNanoTime, String direction, Maze maze, Pacman pacman, Group root) {
		// TODO Auto-generated method stub
	}

}
