package com.example.jarek.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static android.widget.Toast.LENGTH_LONG;

/**
 * Klasa obsługująca aktywność służącą do dodawania planszy użytkonika do bazy danych.
 */
public class AddBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_board);
        final Toast infoExist = Toast.makeText(this, "Plansza już istnieje! Wprowadź inną!", LENGTH_LONG);
        final Toast infoRight = Toast.makeText(this, "Plansza nie jest poprawną planszą SudokuX!", LENGTH_LONG);
        final Toast infoNumber = Toast.makeText(this, "Plansza musi mieć 81 znaków!", LENGTH_LONG);
        final Button back = (Button) findViewById(R.id.backButtonAdd);
        Button save = (Button) findViewById(R.id.saveButtonAdd);
        final Sudoku sudoku = new Sudoku();

        assert save != null;
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText board = (EditText) findViewById(R.id.addBoardText);
                SudokuDB db = new SudokuDB(getApplicationContext());
                db.getReadableDatabase();

                assert board != null;
                String table = board.getText().toString();

                if(table.length() < 81) {
                    infoNumber.show();
                }

                int i = 0; int position = 0;
                int[][] puzzle = new int[9][9];

                do
                {
                    for (int j = 0; j < 9; j++) {
                        puzzle[i][j] = table.charAt(position);
                        position++;
                    }
                    i++;

                }while (position < 81);


                if (table.length() == 81) {

                    if(sudoku.check(puzzle)) {
                        try {

                            db.addBoard(table);

                        } catch (android.database.sqlite.SQLiteConstraintException e) {

                            infoExist.show();

                        }
                    }
                    else
                        infoRight.show();
                }
                
                db.close();
            }
        });

        assert back != null;
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();

            }
        });
    }
}
