package com.example.funreader.Activity;

import java.io.File;

import com.example.funreader.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class TextActivity extends Activity
{
	private ImageButton add;
	private TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.text_layout);
		add = (ImageButton)findViewById(R.id.add_button);
		text = (TextView)findViewById(R.id.text);
		add.setVisibility(View.GONE);
		Intent intent = getIntent();
		String path = intent.getStringExtra("textPath");
		File file = new File(path);
	}
}
