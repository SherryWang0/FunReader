package com.example.funreader.Utils;

import java.util.List;

import com.example.funreader.R;
import com.example.funreader.modle.FileClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter
{
	private Context context;
	private List<FileClass> l = null;
	
	public MyListViewAdapter(Context context, List<FileClass> l)
	{
		this.l = l;
		this.context = context;
	}
	@Override
	public int getCount()
	{
		return l.size();
	}
	@Override
	public Object getItem(int position)
	{
		return l.get(position);
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
			v = LayoutInflater.from(context).inflate(R.layout.list_view_layout, null);
		else
			v = convertView;
		TextView titleText = (TextView)v.findViewById(R.id.title_list);
		TextView sizeText = (TextView)v.findViewById(R.id.size);
		titleText.setText(l.get(position).getName());
		sizeText.setText(l.get(position).getSpace()+"");
		return v;
	}
}
