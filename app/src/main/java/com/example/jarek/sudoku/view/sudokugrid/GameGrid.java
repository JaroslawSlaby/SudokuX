package com.example.jarek.sudoku.view.sudokugrid;

import com.example.jarek.sudoku.Sudoku;

import android.content.Context;
import android.widget.Toast;

/**
 * Klasa tworząca gridview części ekranu odpowiedzialnej za stworzenie sudoku.
 */
public class GameGrid {
	private SudokuCell[][] Sudoku = new SudokuCell[9][9];
	
	private Context context;
	
	public GameGrid( Context context ){
		this.context = context;
		for( int x = 0 ; x < 9 ; x++ ){
			for( int y = 0 ; y < 9 ; y++){
				Sudoku[x][y] = new SudokuCell(context);
                if((x < 3 || (x > 5 && x < 9)) && (y < 3 || (y > 5 && y < 9)) || ((x > 2 && x < 6) && (y > 2 && y < 6)) )
                    Sudoku[x][y].isFat();

			}
		}
	}

	/**
	 * Metoda uniemożliwiająca zmianę domyślnie wypisanych na ekran komórek.
	 * @param grid - gotowa plansza
     */
	public void setGrid( int[][] grid ){
		for( int x = 0 ; x < 9 ; x++ ){
			for( int y = 0 ; y < 9 ; y++){
				Sudoku[x][y].setInitValue(grid[x][y]);
				if( grid[x][y] != 0 ){
					Sudoku[x][y].setNotModifiable();
                    Sudoku[x][y].setUser();
				}
			}
		}
	}
	
	public SudokuCell[][] getGrid(){
		return Sudoku;
	}
	
	public SudokuCell getItem(int x , int y ){
		return Sudoku[x][y];
	}
	
	public SudokuCell getItem( int position ){
		int x = position % 9;
		int y = position / 9;
		
		return Sudoku[x][y];
	}
	
	public void setItem( int x , int y , int number ){
		Sudoku[x][y].setValue(number);
	}

	public void checkGame(){
		int [][] sudGrid = new int[9][9];
		com.example.jarek.sudoku.Sudoku sd = new Sudoku();
		for( int x = 0 ; x < 9 ; x++ ){
			for( int y = 0 ; y < 9 ; y++ ){
				sudGrid[x][y] = getItem(x,y).getValue();
			}
		}

		if(sd.check(sudGrid)){
			Toast.makeText(context, "Gratuluję! Rozwiązałeś SudokuX!", Toast.LENGTH_LONG).show();
		}
	}
}
