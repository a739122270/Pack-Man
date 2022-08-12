package model.cookies;

import javafx.scene.paint.Color;

/**
 * Define LifeHeart, inherits Cookie
 * 
 * @author YiXUE
 *
 */
public class LifeHeart extends Cookie {

	/**
	 * Set position and color
	 * 
	 * @param x - position for horizontal coordinate
	 * @param y - position for vertical coordinate
	 */
	public LifeHeart(double x, double y) {
		super(x, y);
		this.setFill(Color.CRIMSON);
	}
}