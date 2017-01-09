package com.example.jarek.sudoku.view.sudokugrid;

import android.content.Context;
import android.view.View;

/**
 * Klasa podstawowej obsługi komórki sudoku, zawierająca jej wartość, możliwość modfikowania oraz informację o tym,
 * kto wprowadził jej wartość.
 */
public class BaseSudokuCell extends View{

	
	private int value;
	private boolean modifiable = true;
	private boolean user = true;
	
	public BaseSudokuCell(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
	
	public void setNotModifiable(){
		modifiable = false;
	}

	public void setUser() { user = false; }
	
	public void setInitValue(int value){
		this.value = value;
		invalidate();
	}
	
	public void setValue( int value ){
		if( modifiable ){
			this.value = value;
		}
		
		invalidate();
	}
	
	public int getValue(){
		return value;
	}
	public boolean ifUser() { return user; }
}
