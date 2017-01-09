package com.example.jarek.sudoku;

/**
 * Created by jarek on 20.04.16.
 */

import android.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 *Klasa zawierająca całą logikę gry: tworzenie gotowej, częściowo pustej planszy oraz jej sprawdzenie po wypełnieniu przez użytkownika.
 */
public class Sudoku {

    public static final int size_9x9 = 9;
    public static final int EXPERT_MODE = 55;
    public static final int MEDIUM_MODE = 40;
    public static final int EASY_MODE = 25;
    public static final int MEGAEASY_MODE = 4;

    public static final String values = "123456789";

    private int[][] puzzle = new int[9][9];

    private Random random = new Random();

    /**
     * Zwraca kopię oryginalnej tablicy przekazanej poprzez parametr;
     * @param original Oryginalna tablica przeznaczona do kopiowania.
     * @return Zwraca kopię oryginału.
     */
    private int[][] copy(int [][] original){

        int[][] copy = new int[original.length][];

        for (int i = 0; i < original.length; i++){

            copy[i] = Arrays.copyOf(original[i], original[i].length);

        }
        return copy;
    }

    /**
     * Zwraca dwie pseudolosowe liczby jako tablicę o długości 2.
     * @param minimum Minimum przeznaczone do losowania.
     * @param tolerance Zakres losowania.
     * @return Zwraca tablicę o długości 2 zawierającą dwie wylosowane liczby.
     */
    private int[] return2randoms(int minimum, int tolerance){

        int a[] = new int[2];
        a[0] = minimum + random.nextInt(tolerance);
        a[1] = minimum + random.nextInt(tolerance);

        return a;
    }

    /**
     * Ukrywa niektóre pola gotowej tablicy, by stworzyć zagadkę Sudoku.
     * @param table Gotowa, wypełniona tablica z cyframi stanowiąca rozwiązane Sudoku.
     * @param mode Liczba określająca, ile cyfr ma zostać ukrytych (zgodnie z wybranym poziomem trudności).
     * @return Zwraca tablicę, której pola przeznaczone do wypełnienia przez gracza mają wartość 0.
     */
    public int[][] makeSudoku(int [][] table, int mode){

        this.puzzle = copy(table);
        int numberOfEmptyFields = getNumberOfEmpties(table, mode);

        for (int i = 0; i < numberOfEmptyFields; i ++){

            int[] row_column = return2randoms(0, table.length);
            this.puzzle[row_column[0]][row_column[1]] = 0;

        }

        return copy(this.puzzle);
    }

    /**
     * Definuje i zwraca ilość pól przeznaczonych do ukrycia.
     * @param table Wypełniona plansza Sudoku przeznaczona do tworzenia gry.
     * @param mode Obrany wcześniej poziom trudności (Przy starcie gry domyślnie ustawiono tryb MEDIUM).
     * Tolerancja określa tzw. mniej-więcej ilość ukrytych pól.
     * @return Liczba pól przeznaczonych do ukrycia.
     */
    private int getNumberOfEmpties(int[][] table, int mode){

        int numberOfEmpties = 0;
        int numberOfBlocks = table.length * table[0].length;

        if (MEGAEASY_MODE <= mode && mode <= EXPERT_MODE){

            numberOfEmpties = (int) Math.floor((mode * numberOfBlocks) / 100);

        }
        else {

            numberOfEmpties = (int) Math.floor((MEDIUM_MODE * numberOfBlocks) / 100);

        }

        int tolerance = (int) Math.floor(((numberOfBlocks - numberOfEmpties) * 5) / 100);
        numberOfEmpties += random.nextInt(tolerance + 1);

        return numberOfEmpties;
    }

    /**
     * Funkcja sprawdzająca poprawność wypełnionej przez gracza planszy, zwracająca wartość logiczną true lub false, w zależności od poprawności jego rozwiazania lub jego braku.
     * @param table Gotowa, wypełniona przez gracza
     * @return Zwraca wartość logiczną true (poprawnie rozwiazana plansza) lub false (plansza rozwiązana błędnie).
     */
    public boolean check(int [][] table){

        boolean correct = true;
        int numberOfRows = 3;
        final String value = values;

        //check rows
        for (int i = 0; i < table.length; i++){

            String set = value;

            for (int j = 0; j < table.length; j++){

                set = set.replace("" + table[i][j], "");

            }
            if(!set.isEmpty()){

                correct = false;
                return correct;

            }

        }

        //check columns
        for (int j = 0; j < table.length; j++){

            String set = value;

            for (int i = 0; i < table.length; i++){

                set = set.replace("" + table[i][j], "");

            }
            if(!set.isEmpty()){

                correct = false;
                return correct;

            }

        }
        //vertical and horizontal blocks
        for (int bp = 0; bp < table.length; bp += numberOfRows){
            for (int pb = 0; pb < table.length; pb += 3){
                String set = value;
                for (int i = bp; i < (bp + numberOfRows); i++){
                    for (int j = pb; j < pb + 3; j++){
                        set = set.replace("" + table[i][j], "");
                    }
                }
                if (!set.isEmpty()){
                    correct = false;
                    return correct;
                }
            }
        }

        //left-right diagonal
        for(int i = 0; i < table.length-1; i++){

            for(int j = 0; j < table.length-1; j++){

                if(i==j){
                    if(table[i][j] == table[i+1][j+1]){

                        correct = false;
                        return correct;
                    }
                }
            }
        }

        //right-left diagonal
        for(int i = table.length; i <= 0; i--){

            for(int j = 0; j < table.length-1; j++) {

                if (i + j == 8) {

                    if (table[i][j] == table[i - 1][j + 1]) {

                        correct = false;
                        return correct;
                    }
                }
            }
        }

        return correct;

    }

    /**
     * Funkcja przywraca domyślną wartość tablicy, usuwając wszystkie podane przez gracza wartości pól.
     * @return Zwraca domyślną, wczytaną z pliku tablicę z usuniętymi wcześniej polami.
     */
    public int [][] refreshPuzzle(){

        return this.puzzle;

    }

}
