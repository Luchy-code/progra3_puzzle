package Run;

import java.awt.EventQueue;
import Presenter.PuzzlePresenter;
import View.Menu;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View.Menu menu = new Menu();
			        PuzzlePresenter presenter = new PuzzlePresenter(menu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
