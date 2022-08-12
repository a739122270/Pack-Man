package view.maze;

/**
 * A factory to create different type of maze instance, factory pattern
 * 
 * @author YiXUE
 *
 */
public class MazeFactory {

	/**
	 * create type A or B maze instance according to user's choice
	 * 
	 * @param mazeType - user chosen mazeType
	 * @return - Maze A/Maze B
	 */
	public Maze getMaze(String mazeType) {
		if (mazeType == null) {
			return null;
		}
		if (mazeType.equalsIgnoreCase("A")) {
			return new MazeA();
		} else if (mazeType.equalsIgnoreCase("B")) {
			return new MazeB();
		}
		return null;
	}
}