package model.cookies;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Defining Cookie
 * 
 * @author YiXUE
 *
 */
public class Cookie extends Circle {
	private int value;

	/**
	 * Constructor, set position, size and color of Cookie
	 * 
	 * @param x - position for horizontal coordinate
	 * @param y - position for vertical coordinate
	 */
	public Cookie(double x, double y) {
		this.value = 5;
		this.setCenterX(x);
		this.setCenterY(y);
		this.setRadius(12.5);
		this.setFill(Color.SADDLEBROWN);
	}

	/**
	 * Get value of Cookie
	 * 
	 * @return	- value of cookie
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Hide Cookie
	 */
	public void hide() {
		this.setVisible(false);
	}

	/**
	 * Make cookie visible
	 */
	public void show() {
		this.setVisible(true);
	}

}