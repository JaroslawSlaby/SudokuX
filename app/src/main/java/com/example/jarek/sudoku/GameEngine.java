package com.example.jarek.sudoku;

import com.example.jarek.sudoku.view.sudokugrid.GameGrid;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import java.util.Random;

/**
 * Klasa silnika gry tworząca planszę.
 */
public class GameEngine {

    private static GameEngine instance;

    private GameGrid grid = null;

    private int selectedPosX = -1, selectedPosY = -1;

    private Sudoku sd = new Sudoku();

    private Random rand = new Random();

    int[][] Sudoku = new int[9][9];

    private GameEngine(){}

    /**
     * Metoda tworząca instancję klasy GameEngine.
     * @return instance - Obiekt klasy.
     */
    public static GameEngine getInstance(){
        if( instance == null ){
            instance = new GameEngine();
        }
        return instance;
    }

    /**
     * Metoda odczytująca z bazy danych ciągu planszy, przepisująca ją do tablicy i tworząca gotową planszę Sudoku.
     * @param context Kontekst aktywności, w której wywołujemy metodę.
     */
    public void createGrid( Context context ){

        SudokuDB db = new SudokuDB(context);
        db.getReadableDatabase();
        int i = rand.nextInt(db.getBoardsCount()) + 1;
        String SudokuBoard = db.getBoard(i);


        int position = 0; int s = 0;
        do
        {
            for (int j = 0; j < 9; j++) {
                Sudoku[j][s] = SudokuBoard.charAt(position) - 48;
                position++;
            }
            s++;

        }while (position < 81);

        Sudoku = sd.makeSudoku(Sudoku, 5); // poziom trudności
        grid = new GameGrid(context);
        grid.setGrid(Sudoku);

    }

    /**
     * Metoda zwracająca planszę.
     * @return grid - plansza
     */
    public GameGrid getGrid(){
        return grid;
    }

    /**
     * Funkcja ustawiająca aktywną komórkę celem wstawienia danych użytkownika.
     * @param x - współrzędna X komórki.
     * @param y - współrzędna Y komórki.
     */
    public void setSelectedPosition( int x , int y ){
        selectedPosX = x;
        selectedPosY = y;
    }

    /**
     * Funkcja wpisująca dane użytkownika do komórek.
     * @param number - numer komórki do której wpisujemy dane.
     */
    public void setNumber( int number ){
        if( selectedPosX != -1 && selectedPosY != -1 ){
            grid.setItem(selectedPosX,selectedPosY,number);
        }
        grid.checkGame();
    }
}