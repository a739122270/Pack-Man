package view.maze;

import javafx.scene.Group;
import model.cookies.Cookie;
import model.cookies.LifeHeart;
import model.cookies.ReverseCookie;
import model.game.BarObstacle;

import java.util.Arrays;
import java.util.Set;

/**
 * Maze:Type A, extends Maze
 * 
 * @author YiXUE
 *
 */
public class MazeA extends Maze {
	/**
	 * Draws the maze
	 * 
	 * @param root - scene root
	 */
	public void CreateMaze(Group root) {
		// ~~~~~~~~~~~~~~~~~~~~~~~~~ frame ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// top
		this.obstacles.add(new BarObstacle(0, 0, "horizontal", 48));
		// bottom
		this.obstacles.add(new BarObstacle(0, 600, "horizontal", 48));
		// left
		this.obstacles.add(new BarObstacle(0, 0, "vertical", 11));
		this.obstacles.add(new BarObstacle(0, 350, "vertical", 11));
		// right
		this.obstacles.add(new BarObstacle(1225 - BarObstacle.THICKNESS, 0, "vertical", 11));
		this.obstacles.add(new BarObstacle(1225 - BarObstacle.THICKNESS, 350, "vertical", 11));

		// ~~~~~~~~~~~~~~~~~~~~~~~~~ Islands ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// obsTopLeft
		this.obstacles.add(new BarObstacle(12 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, "vertical", 4));
		// obsTopRight
		this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, "vertical", 4));
		// obsBottomLeft
		this.obstacles.add(new BarObstacle(12 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "vertical", 4));
		// obsBottomRight
		this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "vertical", 4));
		// obsTopMiddle
		this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 17));
		// obsBottomMiddle
		this.obstacles
				.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 17));
		// obsLMTop
		this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
		// obsLMTop4
		this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, "horizontal", 5));
		// obsLMBottom
		this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, "horizontal", 5));
		// obsRMTop
		this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
		// obsRMTop2
		this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, "horizontal", 5));
		// obsRMBottom
		this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, "horizontal", 5));
		// LobsLeftTop1
		this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 5));
		// LobsLeftTop2
		this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, "vertical", 6));
		// LobsLeftBottom1
		this.obstacles
				.add(new BarObstacle(4 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 5));
		// LobsLeftBottom2
		this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, "vertical", 6));
		// LobsRightTop1
		this.obstacles.add(new BarObstacle(40 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 5));
		// LobsRightTop2
		this.obstacles.add(new BarObstacle(44 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, "vertical", 6));
		// LobsRightBottom1
		this.obstacles
				.add(new BarObstacle(40 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 5));
		// LobsRightBottom2
		this.obstacles
				.add(new BarObstacle(44 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, "vertical", 6));
		// cageBottom
		this.obstacles
				.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 8 * BarObstacle.THICKNESS, "horizontal", 17));
		// cageRightV
		this.obstacles
				.add(new BarObstacle(32 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, "vertical", 8));
		// cageLeftV
		this.obstacles
				.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, "vertical", 8));
		// cateRightH
		this.obstacles.add(new BarObstacle(17 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
		// cateLeftH
		this.obstacles.add(new BarObstacle(27 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));

		root.getChildren().addAll(obstacles);
	}

	/**
	 * Draws the board of the game with the cookies and the Pacman
	 * 
	 * @param root      - scene root
	 * @param cookieSet - cookies
	 */
	public void drawBoard(Group root, Set<Cookie> cookieSet) {
		CreateMaze(root);
		// 1st line
		Integer skip[] = { 4, 5, 17, 18 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}
		// 1nd line ReverseCookie
		skip = new Integer[] { 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19, 20, 21, 22 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				ReverseCookie cookie = new ReverseCookie(((2 * i) + 2.5) * BarObstacle.THICKNESS,
						2.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

		// 2nd line
		skip = new Integer[] { 1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 4.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

		// 3rd line
		skip = new Integer[] { 1, 21 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 6.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

		// 4th line
		skip = new Integer[] { 1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 8.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

		// 5th line
		skip = new Integer[] { 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 10.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

		// 6th line
		skip = new Integer[] { 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}
		skip = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				LifeHeart heart = new LifeHeart(((2 * i) + 2.5) * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS);
				cookieSet.add(heart);
				root.getChildren().add(heart);
			}
		}

		// 7th line
		skip = new Integer[] { 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 14.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

		// 8th line
		skip = new Integer[] { 1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 16.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

		// 9th line
		skip = new Integer[] { 1, 21 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 18.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

		// 10th line
		skip = new Integer[] { 1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 20.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

		// 11th line
		skip = new Integer[] { 4, 5, 17, 18 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 22.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}
		// 11nd line ReverseCookie
		skip = new Integer[] { 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19, 20, 21, 22 };
		for (int i = 0; i < 23; i++) {
			if (!Arrays.asList(skip).contains(i)) {
				ReverseCookie cookie = new ReverseCookie(((2 * i) + 2.5) * BarObstacle.THICKNESS,
						22.5 * BarObstacle.THICKNESS);
				cookieSet.add(cookie);
				root.getChildren().add(cookie);
			}
		}

	}

}
