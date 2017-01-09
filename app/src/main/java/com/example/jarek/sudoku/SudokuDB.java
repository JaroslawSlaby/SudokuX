package com.example.jarek.sudoku;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Klasa obsługująca bazę danych plansz.
 */
public class SudokuDB extends SQLiteOpenHelper {

    private static final String DBName = "Sudoku.db";
    private static final int DBVersion = 1;
    private static final String DBTableName = "boards";
    private static final String DBTableValueName = "numbers";
    private static final String DBid = "id";

    public SudokuDB(Context context) {

        super(context, DBName, null, DBVersion);
        
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + DBTableName + "(" + DBid + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBTableValueName + " TEXT NOT NULL UNIQUE ON CONFLICT IGNORE " + ")";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DBTableName);
        onCreate(db);
        
    }

    /**
     * Funkcja dodająca do bazy planszę w formie ciągu string.
     * @param value Plansza w formie ciągu znaków.
     * @throws SQLiteConstraintException W przypadku wyjątku dotyczącego próby dodania ponownie istniejącej planszy.
     */

    public void addBoard(String value) throws SQLiteConstraintException{

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBTableValueName, value);
        db.insertOrThrow(DBTableName, null, values);
        db.close();

    }

    /**
     * Funkcja zwracająca gotową planszę w formie zmiennej typu string.
     * @param id Numer wiersza zawierający wybraną przez użytkownika planszę.
     * @return value - string zawierający ciąg znaków planszy.
     */

    public String getBoard(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor read = db.query(DBTableName, new String[] { DBid,
                        DBTableValueName }, DBid + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if(read != null)
            read.moveToFirst();
        String value = read.getString(1);
        db.close();
        return value;

    }

    /**
     * Funkcja  zwracająca ilość rekordów w bazie.
     * @return count - integer zawierający ilość rekordów.
     */

    public int getBoardsCount(){

        String query = "SELECT * FROM " + DBTableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor counter = db.rawQuery(query, null);

        int count = counter.getCount();
        counter.close();
        db.close();
        return count;

    }

    /**
     * Funkcja dodająca do pustej bazy 4 gotowe plansze
     */
    public void add4Boards(){

        this.addBoard("645397281713582946289641573832476195491825367567913824158264739976138452324759618");
        this.addBoard("671498523435261789982375461763842195854913276129657348347586912298134657516729834");
        this.addBoard("273416859951287346684593127846752931529631784137849265495128673368975412712364598");
        this.addBoard("348762519965413287217598364593846721726351498481279635834927156672185943159634872");

    }

}
