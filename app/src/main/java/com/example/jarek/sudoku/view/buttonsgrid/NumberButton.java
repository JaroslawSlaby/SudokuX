package com.example.jarek.sudoku.view.buttonsgrid;

import com.example.jarek.sudoku.AddBoard;
import com.example.jarek.sudoku.GameEngine;
import com.example.jarek.sudoku.Sudoku;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Klasa implementująca częściowo onClick listenera dla przycisków odpowiedzialnych za wstawianie danych.
 */
public class NumberButton extends Button implements OnClickListener{

	private int number;
	
	public NumberButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(number < 10)
		GameEngine.getInstance().setNumber(number);

	}
	
	public void setNumber(int number){
		this.number = number;

	}
}
