package com.example.funreader.Activity;

import com.example.funreader.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class ListActivity extends Activity
{
	private ImageButton add;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_layout);
		add = (ImageButton)findViewById(R.id.add_button);
		add.setVisibility(View.GONE);
	}
}
