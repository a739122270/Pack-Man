package model.game;

import Controller.SettingController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Defining BarObstacles
 * @author YiXUE
 *
 */
public class BarObstacle extends Rectangle {
	public static Color wallcolor = Color.CADETBLUE;
	public static double THICKNESS = 25;

	/**
	 * Constructor, Create obstacle in different position, direction and length
	 * 
	 * @param x           -position for horizontal coordinate
	 * @param y           -position for vertical coordinate
	 * @param orientation - horizontal or vertical
	 * @param length      - the length of the bar (1 == 100px)
	 */
	public BarObstacle(double x, double y, String orientation, double length) {
		this.setX(x);
		this.setY(y);
		if (orientation.equals("horizontal")) {
			this.setHeight(BarObstacle.THICKNESS);
			this.setWidth(length * BarObstacle.THICKNESS);
		} else {
			this.setHeight(length * BarObstacle.THICKNESS);
			this.setWidth(BarObstacle.THICKNESS);
		}
		this.setFill(SettingController.wallcolor);
		this.setStrokeWidth(3);
	}
}
