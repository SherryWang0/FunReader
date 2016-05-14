package com.example.funreader.Activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.funreader.R;
import com.example.funreader.Utils.MyBridAdapter;
import com.example.funreader.modle.FileClass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageButton;

public class MainActivity extends Activity
{
	private ImageButton add;
	private GridView gridView;
	private List<FileClass> list = null;
	private Object File;
	private MyBridAdapter mbAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		add = (ImageButton)findViewById(R.id.add_button);
		add.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, ListActivity.class);
				startActivity(intent);
			}
		});
		gridView = (GridView)findViewById(R.id.grid_view);
		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		Display d = wm.getDefaultDisplay();
		gridView.setColumnWidth(d.getWidth()/3 - 30);
		getAllTXT();
		mbAdapter = new MyBridAdapter(this, list);
		gridView.setAdapter(mbAdapter);
	}
	private void getAllTXT()
	{
		list = new ArrayList<FileClass>();
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
				String name =f.getName();
				if(name.endsWith(".txt"))
				{
					String path = f.getPath();
					long space = f.getTotalSpace();
					FileClass fc = new FileClass();
					fc.setName(name);
					fc.setPath(path);
					fc.setSpace(space);
					list.add(fc);
				}
			}
		}
	}
}
