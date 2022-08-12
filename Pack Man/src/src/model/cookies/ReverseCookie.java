package model.cookies;

import javafx.scene.paint.Color;

/**
 * Define ReverseCookie, inherits Cookie
 * 
 * @author YiXUE
 *
 */
public class ReverseCookie extends Cookie {

	/**
	 * Set position and color
	 * 
	 * @param x - position for horizontal coordinate
	 * @param y - position for vertical coordinate
	 */
	public ReverseCookie(double x, double y) {
		super(x, y);
		this.setFill(Color.GREENYELLOW);
	}
}