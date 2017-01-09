package com.example.jarek.sudoku.view.sudokugrid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

/**
 * Klasa rozszerzająca BaseSudokuCell o możliwość rysowania.
 */
public class SudokuCell extends BaseSudokuCell {

	private Paint mPaint;
	private boolean fat = false;
	
	public SudokuCell( Context context ){
		super(context);
		
		mPaint = new Paint();
		
	}

	public void isFat() { fat = true; }

	private boolean getFat() { return fat; }

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawNumber(canvas);
		drawLines(canvas);
	}

	/**
	 * Metoda rysująca numery wartości poszczególnych komórek.
	 * @param canvas - płótno, po którym rysuje.
	 */
	private void drawNumber(Canvas canvas){
		mPaint.setColor(Color.GRAY);
		mPaint.setTextSize(60);
		mPaint.setStyle(Style.FILL);
		
		Rect bounds = new Rect();
		mPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);


		if(ifUser()) {
			mPaint.setColor(Color.RED);
			if (getValue() != 0) {
				canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width()) / 2, (getHeight() + bounds.height()) / 2, mPaint);
			}
		}
		else{
			if (getValue() != 0) {
			canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width()) / 2, (getHeight() + bounds.height()) / 2, mPaint);
			}
		}
	}

	/**
	 * Metoda rysująca obwody komórek.
	 * @param canvas - płótno, po którym rysuje.
     */
	private void drawLines(Canvas canvas) {
		if(getFat() == false) {
			mPaint.setColor(Color.BLUE);
			mPaint.setStrokeWidth(3);
			mPaint.setStyle(Style.STROKE);
			canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		}
		else {

			mPaint.setColor(Color.GREEN);
			mPaint.setStrokeWidth(10);
			mPaint.setStyle(Style.STROKE);
			canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		}

	}
}
