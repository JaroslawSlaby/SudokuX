package com.example.jarek.sudoku.view.buttonsgrid;

import com.example.jarek.sudoku.About;
import com.example.jarek.sudoku.AddBoard;
import com.example.jarek.sudoku.MainActivity;
import com.example.jarek.sudoku.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Klasa umożliwiająca stworzenie części ekranu odpowiedzialnej za wstawianie danych.
 */
public class ButtonsGridView extends GridView{
	
	
	public ButtonsGridView( Context context , AttributeSet attrs ){
		super(context , attrs);
		
		ButtonsGridViewAdapter gridViewAdapter = new ButtonsGridViewAdapter(context);
		
		setAdapter(gridViewAdapter);
	}
	
	class ButtonsGridViewAdapter extends BaseAdapter {
		
		private Context context;
		
		public ButtonsGridViewAdapter(Context context) {
			this.context = context;
		}
		
		@Override
		public int getCount() {
			return 14;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		/**
		 * Funkcja tworząca interfejs użytkownika z przyciskami
		 */
		public View getView(int position, final View convertView, ViewGroup parent) {
			View v = convertView;
			
			if( v == null ){
				final LayoutInflater inflater = ((Activity) context).getLayoutInflater();
				v = inflater.inflate(R.layout.button, parent , false);
				
				NumberButton btn;
				btn = (NumberButton)v;
				btn.setTextSize(12);
				btn.setId(position);
				
				if( position < 9 ){
					btn.setText(String.valueOf(position + 1));
					btn.setNumber(position + 1);
				}else if (position == 9){
					btn.setText("Usuń");
					btn.setNumber(0);
				}
				else if (position == 10){
					btn.setText("Odśw.");
					btn.setNumber(10);
					btn.setOnClickListener(new View.OnClickListener()
					{
						public void onClick(View v) {
							Intent intent = new Intent(context, MainActivity.class);
							context.startActivity(intent);

						}});
				}
				else if (position == 11){
					btn.setText("Dodaj");
					btn.setNumber(11);

					btn.setOnClickListener(new View.OnClickListener()
					{
						public void onClick(View v) {
							Intent intent = new Intent(context, AddBoard.class);
							context.startActivity(intent);

						}});

				}
				else if (position == 12){
					btn.setText("Autor");
					btn.setNumber(12);

					btn.setOnClickListener(new View.OnClickListener()
					{
						public void onClick(View v) {
							Intent intent = new Intent(context, About.class);
							context.startActivity(intent);

						}});
				}
				else {
					btn.setText("Wyjdź");
					btn.setNumber(13);

					btn.setOnClickListener(new View.OnClickListener()
						{
						public void onClick(View v) {
							System.exit(0);

						}});

				}
				return btn;
			}
			
			return v;
		}
		
	}
}
