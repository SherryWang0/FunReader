package com.example.funreader.modle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper
{
	public MySQLiteOpenHelper(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("create table text(_id integer primary key autoincrement, name text, path text, space int, progress int)");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}
}
