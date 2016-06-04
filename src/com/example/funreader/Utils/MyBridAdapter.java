package com.example.funreader.Utils;

import java.util.ArrayList;
import java.util.List;

import com.example.funreader.R;
import com.example.funreader.modle.FileClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyBridAdapter extends BaseAdapter
{
	private Context context;
	private List<FileClass> list;
	
	public MyBridAdapter(Context context, List<FileClass> l)
	{
		this.context = context;
		list = new ArrayList<FileClass>();
		for(int i = 0; i <l.size(); i++)
	//		if(l.get(i).isInBridView())
				list.add(l.get(i));
	}
	@Override
	public int getCount()
	{
		return list.size();
	}
	@Override
	public Object getItem(int position)
	{
		return list.get(position);
	}
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = null;
		if(convertView == null)
			v = LayoutInflater.from(context).inflate(R.layout.brid_view, null);
		else
			v = convertView;
		TextView bookName = (TextView)v.findViewById(R.id.grid_book_name);
		TextView gridAuthor = (TextView)v.findViewById(R.id.grid_author);
		ProgressBar pb = (ProgressBar)v.findViewById(R.id.progress_bar);
		TextView progress = (TextView)v.findViewById(R.id.progress);
		bookName.setText(list.get(position).getName());
		long pro = list.get(position).getProgress();
		long space = list.get(position).getSpace();
		pb.setMax((int)space);
		pb.setProgress((int)pro);
		progress.setText(pro/space + "");
		return v;
	}
}
