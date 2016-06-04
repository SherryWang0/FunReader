package com.example.funreader.Activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.funreader.R;
import com.example.funreader.Utils.MyBridAdapter;
import com.example.funreader.modle.FileClass;
import com.example.funreader.modle.MySQLiteOpenHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private ImageButton add;
	private GridView gridView;
	private List<FileClass> list = null;
	private MyBridAdapter mbAdapter = null;
	private MySQLiteOpenHelper msoh = null;
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		list = new ArrayList<FileClass>();
		gridView = (GridView)findViewById(R.id.grid_view);
		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		Display d = wm.getDefaultDisplay();
		msoh = new MySQLiteOpenHelper(this, "fileclass.db", null, 1);
		db = msoh.getReadableDatabase();
		gridView.setColumnWidth(d.getWidth()/3 - 30);
		add = (ImageButton)findViewById(R.id.add_button);
		msoh = new MySQLiteOpenHelper(this, "fileclass.db", null, 1);
		getList();
		mbAdapter = new MyBridAdapter(this, list);
		gridView.setAdapter(mbAdapter);
		add.setOnClickListener(new android.view.View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, ListActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		
		gridView.setOnItemLongClickListener(new OnItemLongClickListener()
		{
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
			{
				Builder builder = new Builder(MainActivity.this);
				builder.setSingleChoiceItems(new String[] {"在列表中删除", "属性", "重命名"}, 0, new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.dismiss();
						switch(which)
						{
						case 0:
							db.delete("text", "_id = ?", new String[] {position + ""});
							System.out.println(list.size());
							list.remove(position);
							System.out.println(list.size());
							refreshAdapter();
							Toast.makeText(MainActivity.this, "已在列表中删除", Toast.LENGTH_SHORT).show();
							break;
						case 1:
							Builder bui = new AlertDialog.Builder(MainActivity.this);
							bui.setTitle("文件详细信息");
							bui.setMessage(list.get(position).toString());
							bui.setCancelable(true);
							bui.show();
							break;
						case 2:
							Builder bu = new AlertDialog.Builder(MainActivity.this);
							bu.setTitle("请输入新名称");
							bu.setView(new EditText(MainActivity.this));
							bu.setPositiveButton("确定", new OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									//goto
									Toast.makeText(MainActivity.this, "文件名已更改", Toast.LENGTH_SHORT).show();
								}
							});
							bu.setNegativeButton("取消", new OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
								}
							});
							bu.setCancelable(true);
							bu.show();
							break;
						default:
							break;
						}
					}
				});
				builder.show();
				return true;
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
			{
				Intent intent = new Intent(MainActivity.this, TextActivity.class);
				intent.putExtra("textPath", list.get(position).getPath());
				startActivity(intent);
			}
		});
	}
	private void getList()
	{
		
		Cursor cursor = db.query("text", null, null, null, null, null, null);
		while(cursor.moveToNext())
		{
			FileClass fc = new FileClass();
			fc.setName(cursor.getString(cursor.getColumnIndex("name")));
			fc.setPath(cursor.getString(cursor.getColumnIndex("path")));
			fc.setSpace(cursor.getLong(cursor.getColumnIndex("space")));
			fc.setProgress(cursor.getLong(cursor.getColumnIndex("progress")));
			list.add(fc);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
		{
			if (resultCode == RESULT_CANCELED)
				Toast.makeText(this, "文件已存在于列表", Toast.LENGTH_SHORT).show();
			else if (resultCode == RESULT_OK)
			{
				System.out.println("sdhkwk");
				String p = data.getStringExtra("addFile");
				File fi = new File(p);
				FileClass fc = new FileClass();
				fc.setName(fi.getName());
				fc.setPath(fi.getPath());
				fc.setSpace(fi.length());
				ContentValues cv = new ContentValues();
				cv.put("name", fc.getName());
				cv.put("path", fc.getPath());
				cv.put("space", fc.getSpace());
				cv.put("progress", fc.getProgress());
				db.insert("text", null, cv);
				list.add(fc);
				refreshAdapter();
			}
		}
	}
	private void refreshAdapter()
	{
		mbAdapter = null;
		mbAdapter = new MyBridAdapter(MainActivity.this, list);
		gridView.setAdapter(mbAdapter);
	}
}