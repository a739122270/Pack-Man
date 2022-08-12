package model.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.cookies.Cookie;
import model.cookies.LifeHeart;
import model.cookies.ReverseCookie;
import model.pacman.Pacman;
import model.pacman.ReversePacmanState;
import model.pacman.SwitchState;
import view.maze.Maze;
import view.maze.MazeFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import Controller.SettingController;
import Controller.StartController;

/**
 * A Game Manager
 * 
 * @author YiXUE
 *
 */
public class GameManager {
	private Pacman pacman;
	private Group root;
	public Set<Cookie> cookieSet;
	private Set<Ghost> ghosts;
	private AnimationTimer leftPacmanAnimation;
	private AnimationTimer rightPacmanAnimation;
	private AnimationTimer upPacmanAnimation;
	private AnimationTimer downPacmanAnimation;
	public Maze maze;
	public SwitchState state;
	private int lifes;
	private int score;
	private Score scoreBoard;
	private boolean gameEnded;
	private boolean pauseflag;
	private int cookiesEaten;
	public static String mazeType;
	public static String difficultylevel;

	/**
	 * Constructor
	 * 
	 * @param root - scene root
	 */
	private GameManager(Group root) {
		// Scene and Maze
		this.root = root;
		mazeType = SettingController.mazeType;
		MazeFactory mazefactory = new MazeFactory();
		Maze mazes = mazefactory.getMaze(mazeType);
		this.maze = mazes;

		// Characters' Figure
		Image pman = new Image("view/pacman.png");
		this.pacman = new Pacman(2.5 * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS, pman);
		this.cookieSet = new HashSet<>();
		this.ghosts = new HashSet<>();

		// Pacman State and Behavior
		this.state = new SwitchState();
		this.leftPacmanAnimation = pacman.createAnimation("left", maze, pacman, root, state);
		this.rightPacmanAnimation = pacman.createAnimation("right", maze, pacman, root, state);
		this.upPacmanAnimation = pacman.createAnimation("up", maze, pacman, root, state);
		this.downPacmanAnimation = pacman.createAnimation("down", maze, pacman, root, state);

		// Game Data
		this.lifes = 3;
		this.score = 0;
		this.cookiesEaten = 0;
	}

	/**
	 * Singleton Pattern for GameManager
	 */
	private static GameManager instance;

	public static GameManager getInstance(Group root) {
		if (instance == null) {
			instance = new GameManager(root);
			return instance;
		} else {
			return instance;
		}
	}

	/**
	 * Set one life less
	 */
	private void lifeLost() {
		this.leftPacmanAnimation.stop();
		this.rightPacmanAnimation.stop();
		this.upPacmanAnimation.stop();
		this.downPacmanAnimation.stop();
		for (Ghost ghost : ghosts) {
			ghost.getAnimation().stop();
		}
		this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
		this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
		lifes--;
		score -= 10;
		this.scoreBoard.lifes.setText("Lifes: " + this.lifes);
		this.scoreBoard.score.setText("Score: " + this.score);
		if (lifes == 0) {
			this.endGame();
		}
	}

	/**
	 * Ends the game
	 */
	private void endGame() {
		this.gameEnded = true;
		root.getChildren().remove(pacman);
		for (Ghost ghost : ghosts) {
			root.getChildren().remove(ghost);
		}
		javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to restart");
		endGame.setX(BarObstacle.THICKNESS * 3);
		endGame.setY(BarObstacle.THICKNESS * 28);
		endGame.setFont(Font.font("Arial", 40));
		endGame.setFill(Color.ROYALBLUE);
		root.getChildren().remove(this.scoreBoard.score);
		root.getChildren().remove(this.scoreBoard.lifes);
		root.getChildren().remove(this.scoreBoard.lifes);
		root.getChildren().add(endGame);

		/**
		 * Write high score
		 */
		String filePath = "src\\model\\game\\highscore.txt";
		try {
			File file = new File(filePath);
			FileOutputStream fos = null;
			if (!file.exists()) {
				file.createNewFile();
				fos = new FileOutputStream(file);
			} else {

				fos = new FileOutputStream(file, true);
			}

			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			String str = Integer.toString(score);
			osw.write("\n");
			osw.write(StartController.username + "," + str);

			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ShowScore showscore = new ShowScore();
		Stage theStage = new Stage();
		try {
			showscore.start(theStage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Restart the game
	 * 
	 * @param event keyevent
	 */
	public void restartGame(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
			root.getChildren().clear();
			this.cookieSet.clear();
			this.ghosts.clear();
			maze.drawBoard(root, cookieSet);
			addCharacters();
			this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
			this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
			this.lifes = 3;
			this.score = 0;
			this.cookiesEaten = 0;
			gameEnded = false;
		}
	}

	/**
	 * pause the game
	 * 
	 * @param event keyEvent
	 */
	public void PauseGame(KeyEvent event) {
		javafx.scene.text.Text pauseGame = new javafx.scene.text.Text("Press SPACE to pause/continue the game!");
		pauseGame.setX(BarObstacle.THICKNESS * 28);
		pauseGame.setY(BarObstacle.THICKNESS * 28);
		pauseGame.setFont(Font.font("Arial", 25));
		pauseGame.setFill(Color.ROYALBLUE);
		root.getChildren().add(pauseGame);
		if (event.getCode() == KeyCode.SPACE) {
			if (pauseflag == false) {
				this.leftPacmanAnimation.stop();
				this.rightPacmanAnimation.stop();
				this.upPacmanAnimation.stop();
				this.downPacmanAnimation.stop();
				for (Ghost ghost : ghosts) {
					ghost.getAnimation().stop();
				}
				pauseflag = true;
			} else {
				for (Ghost ghost : ghosts) {
					ghost.getAnimation().start();
				}
				pauseflag = false;
			}

		}
	}

	/**
	 * Add PacMan and Ghost on Map
	 */
	public void addCharacters() {
		root.getChildren().add(this.pacman);
		this.generateGhosts();
		root.getChildren().addAll(this.ghosts);
		this.scoreBoard = new Score(root);
	}

	/**
	 * Generates the ghosts for the pacman!
	 */
	public void generateGhosts() {
		difficultylevel = SettingController.difficultylevel;
		Image ghost1 = new Image("view/ghost1.png");
		Image ghost2 = new Image("view/ghost2.png");
		Image ghost3 = new Image("view/ghost3.png");
		Image ghost4 = new Image("view/ghost4.png");
		this.ghosts.add(new Ghost(18.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, maze, this, ghost1));
		this.ghosts.add(new Ghost(22.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, maze, this, ghost2));
		this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, maze, this, ghost3));
		this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, maze, this, ghost4));
		if (difficultylevel.equalsIgnoreCase("Easy")) {

		} else if (difficultylevel.equalsIgnoreCase("Medium")) {
			this.ghosts.add(new Ghost(18.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, maze, this, ghost3));
			this.ghosts.add(new Ghost(22.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, maze, this, ghost4));
		} else if (difficultylevel.equalsIgnoreCase("Hard")) {

			this.ghosts.add(new Ghost(25.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, maze, this, ghost2));
			this.ghosts.add(new Ghost(25.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, maze, this, ghost1));
			this.ghosts.add(new Ghost(18.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, maze, this, ghost3));
			this.ghosts.add(new Ghost(22.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, maze, this, ghost4));
		}

	}

	/**
	 * Moves the pacman
	 * 
	 * @param event - keyEvent
	 */
	public void movePacman(KeyEvent event) {
		if (pauseflag != true) {
			for (Ghost ghost : this.ghosts) {
				ghost.run();
			}
			switch (event.getCode()) {
			case RIGHT:
				this.rightPacmanAnimation.start();
				break;
			case LEFT:
				this.leftPacmanAnimation.start();
				break;
			case UP:
				this.upPacmanAnimation.start();
				break;
			case DOWN:
				this.downPacmanAnimation.start();
				break;
			}
		}

	}

	/**
	 * Stops the pacman
	 * 
	 * @param event - keyEvent
	 */
	public void stopPacman(KeyEvent event) {
		switch (event.getCode()) {
		case RIGHT:
			this.rightPacmanAnimation.stop();
			break;
		case LEFT:
			this.leftPacmanAnimation.stop();
			break;
		case UP:
			this.upPacmanAnimation.stop();
			break;
		case DOWN:
			this.downPacmanAnimation.stop();
			break;
		}
	}

	/**
	 * Checks if the Pacman touches cookies.
	 * 
	 * @param pacman the generated pacman
	 * @param axis   position
	 */
	public void checkCookieCoalition(Pacman pacman, String axis) {
		double pacmanCenterY = pacman.getCenterY();
		double pacmanCenterX = pacman.getCenterX();
		double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
		double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
		double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
		double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();
		for (Cookie cookie : cookieSet) {
			double cookieCenterX = cookie.getCenterX();
			double cookieCenterY = cookie.getCenterY();
			double cookieLeftEdge = cookieCenterX - cookie.getRadius();
			double cookieRightEdge = cookieCenterX + cookie.getRadius();
			double cookieTopEdge = cookieCenterY - cookie.getRadius();
			double cookieBottomEdge = cookieCenterY + cookie.getRadius();
			if (axis.equals("x")) {

				// pacman goes right
				if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge)
						&& (pacmanRightEdge >= cookieLeftEdge && pacmanRightEdge <= cookieRightEdge)) {
					if (cookie.isVisible()) {
						this.score += cookie.getValue();
						this.cookiesEaten++;
						// eat LifeHeart
						if (cookie instanceof LifeHeart) {
							this.lifes++;
						}
						// eat ReverseCookie
						if (cookie instanceof ReverseCookie) {
							this.state.setState(new ReversePacmanState());
							this.score += cookie.getValue();
						}
					}
					cookie.hide();
				}
				// pacman goes left
				if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge)
						&& (pacmanLeftEdge >= cookieLeftEdge && pacmanLeftEdge <= cookieRightEdge)) {
					if (cookie.isVisible()) {
						this.score += cookie.getValue();
						this.cookiesEaten++;
						// eat LifeHeart
						if (cookie instanceof LifeHeart) {
							this.lifes++;
						}
						// eat ReverseCookie
						if (cookie instanceof ReverseCookie) {
							this.state.setState(new ReversePacmanState());
							this.score += cookie.getValue();
						}
					}
					cookie.hide();

				}
			} else {
				// pacman goes up
				if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge)
						&& (pacmanBottomEdge >= cookieTopEdge && pacmanBottomEdge <= cookieBottomEdge)) {
					if (cookie.isVisible()) {
						this.score += cookie.getValue();
						this.cookiesEaten++;
						// eat LifeHeart
						if (cookie instanceof LifeHeart) {
							this.lifes++;
						}
						// eat ReverseCookie
						if (cookie instanceof ReverseCookie) {
							this.state.setState(new ReversePacmanState());
							this.score += cookie.getValue();
						}
					}
					cookie.hide();

				}
				// pacman goes down
				if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge)
						&& (pacmanTopEdge <= cookieBottomEdge && pacmanTopEdge >= cookieTopEdge)) {
					if (cookie.isVisible()) {
						this.score += cookie.getValue();
						this.cookiesEaten++;
						// eat LifeHeart
						if (cookie instanceof LifeHeart) {
							this.lifes++;
						}
						// eat ReverseCookie
						if (cookie instanceof ReverseCookie) {
							this.state.setState(new ReversePacmanState());
							this.score += cookie.getValue();
						}
					}
					cookie.hide();

				}
			}
			this.scoreBoard.score.setText("Score: " + this.score);
			this.scoreBoard.lifes.setText("Lifes: " + this.lifes);
			if (this.cookiesEaten == this.cookieSet.size() && gameEnded == false) {
				this.endGame();
			}
		}
	}
	
	/**
	 * Checks if pacman is touching a ghost
	 */
	public void checkGhostCoalition() {
		double pacmanCenterY = pacman.getCenterY();
		double pacmanCenterX = pacman.getCenterX();
		double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
		double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
		double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
		double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();
		for (Ghost ghost : ghosts) {
			double ghostLeftEdge = ghost.getX();
			double ghostRightEdge = ghost.getX() + ghost.getWidth();
			double ghostTopEdge = ghost.getY();
			double ghostBottomEdge = ghost.getY() + ghost.getHeight();
			if ((pacmanLeftEdge <= ghostRightEdge && pacmanLeftEdge >= ghostLeftEdge)
					|| (pacmanRightEdge >= ghostLeftEdge && pacmanRightEdge <= ghostRightEdge)) {
				if ((pacmanTopEdge <= ghostBottomEdge && pacmanTopEdge >= ghostTopEdge)
						|| (pacmanBottomEdge >= ghostTopEdge && pacmanBottomEdge <= ghostBottomEdge)) {
					lifeLost();
				}
			}
		}
	}

}
