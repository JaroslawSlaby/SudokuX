package com.example.jarek.sudoku;

import android.app.Activity;
import android.os.Bundle;

/**
 * Klasa aktywności głównej zawierająca planszę do gry.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SudokuDB db = new SudokuDB(this);
		if(db.getBoardsCount() == 0)
			db.add4Boards();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GameEngine.getInstance().createGrid(this);
	}

	/**
	 * Funkcja testowa wypisująca gotową planszę.
	 * @param Sudoku - plansza zawierająca wartości sudoku.
     */
	private void printSudoku(int Sudoku[][]) {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				System.out.print(Sudoku[x][y] + "|");
			}
			System.out.println();
		}
	}

}
