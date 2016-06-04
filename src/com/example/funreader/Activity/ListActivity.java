package com.example.funreader.Activity;

import java.io.File;
import java.util.ArrayList;

import com.example.funreader.R;
import com.example.funreader.Utils.MyListViewAdapter;
import com.example.funreader.modle.FileClass;
import com.example.funreader.modle.MySQLiteOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class ListActivity extends Activity
{
	private ImageButton add;
	private ArrayList<FileClass> list = null;
	private MyListViewAdapter adapter = null;
	private ListView listView;
	private MySQLiteOpenHelper msoh = null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_layout);
		add = (ImageButton)findViewById(R.id.add_button);
		add.setVisibility(View.GONE);
		list = new ArrayList<FileClass>();
		listView = (ListView)findViewById(R.id.list_view);
		getAllTXT();
		adapter = new MyListViewAdapter(this, list);
		listView.setAdapter(adapter);
		msoh = new MySQLiteOpenHelper(this, "fileclass.db", null, 1);
		final SQLiteDatabase db = msoh.getReadableDatabase();
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent i = new Intent(ListActivity.this, MainActivity.class);
				String p = list.get(position).getPath();
				Cursor c = db.query("text", null, "path = ?", new String[]{p}, null, null, null);
				boolean isExit = false;
				while(c.moveToNext())
				{
					if(p.equals(c.getString(c.getColumnIndex("path"))))
					{
						isExit = true;
						break;
					}
				}
				int re = RESULT_OK;
				if(isExit)
					re = RESULT_CANCELED;
				i.putExtra("addFile", p);
				setResult(re, i);
				finish();
			}
		});
	}
	private void getAllTXT()
	{
		File f = null;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			f = Environment.getExternalStorageDirectory();
		File[] file = f.listFiles();
		getFileMessage(file);
		
	}
	private void getFileMessage(File[] file)
	{
		for(File f : file)
		{
			if(f.isDirectory())
				getFileMessage(f.listFiles());
			else
			{
				String fileName = f.getName();
				if(fileName.endsWith(".txt"))
				{
					FileClass fc = new FileClass();
					fc.setName(f.getName());
					fc.setPath(f.getPath());
					fc.setSpace(f.length());
					list.add(fc);
				}
			}
		}
	}
}
