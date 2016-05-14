package com.example.funreader.Activity;

import com.example.funreader.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeActivity extends Activity
{
	private ImageView image;
	private Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome_layout);
		image = (ImageView)findViewById(R.id.text);
		Animation aa = AnimationUtils.loadAnimation(this, R.anim.image_alpha);
		image.setAnimation(aa);
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		}, 3000);
	}
}
