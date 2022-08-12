package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Controller of HighScore, with HighScore.fxml
 * 
 * @author YiXUE
 *
 */
public class ScoreController {
	@FXML
	private Button backtogame;
	@FXML
	private ListView<String> ranklist;
	@FXML
	private ListView<String> usernamelist;
	@FXML
	private ListView<String> scorelist;

	public void close() throws Exception {
		Stage originalstage = (Stage) backtogame.getScene().getWindow();
		originalstage.close();
	}

	/**
	 * read high score from file and show on the high score board
	 * 
	 * @throws IOException	IO for txt
	 */
	@FXML
	private void initialize() throws IOException {
		ObservableList<String> list1 = FXCollections.observableArrayList();
		ObservableList<String> list2 = FXCollections.observableArrayList();
		ObservableList<String> list3 = FXCollections.observableArrayList();
		list1.add("Rank");
		list2.add("UserName");
		list3.add("Score");
		BufferedReader in = new BufferedReader(new FileReader("src\\model\\game\\highscore.txt"));
		String str;
		if ((str = in.readLine()) == null) {// if file has no data

		}
		int tempcount = 1;
		while ((str = in.readLine()) != null) {
			String[] listcontent = str.split(",");
			String count = Integer.toString(tempcount);
			list1.add(count);
			String username = listcontent[0];
			list2.add(username);
			String score = listcontent[1];
			list3.add(score);
			tempcount++;
		}

		if (list2 != null) {
			for (int i = 1; i < list2.size(); i++) {
				for (int j = 1; j < list2.size() - i; j++) {
					int a = Integer.parseInt(list3.get(j));
					int b = Integer.parseInt(list3.get(j + 1));
					if (a < b) {
						String r3 = list3.get(j);
						String r2 = list2.get(j);
						list3.set(j, list3.get(j + 1));
						list2.set(j, list2.get(j + 1));
						list3.set(j + 1, r3);
						list2.set(j + 1, r2);
					}
				}
			}
		}

		// delete unnecessary data
		while (list2.size() > 11) {
			list1.remove(11);
			list2.remove(11);
			list3.remove(11);
		}

		ranklist.setItems(list1);
		usernamelist.setItems(list2);
		scorelist.setItems(list3);
	}
}
