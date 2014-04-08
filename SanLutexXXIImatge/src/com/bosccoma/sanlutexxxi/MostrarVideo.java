package com.bosccoma.sanlutexxxi;

import android.net.Uri;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MostrarVideo extends Activity implements OnClickListener {
	private VideoView videoView;
	private MediaController mediaController;
	Boolean panell = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_video);
		ImageView visorImatge = (ImageView) findViewById(R.id.imgpanell);
		visorImatge.setOnClickListener(this);
		TextView txtSalutacio = (TextView) findViewById(R.id.txtSalutacio_mv);
		Bundle bundle = this.getIntent().getExtras();

		txtSalutacio.setText(bundle.getString("SALUTACIO"));
		videoView = (VideoView) findViewById(R.id.videoView1);
		mediaController = new MediaController(this);
		videoView.setMediaController(mediaController);
		videoView.setVideoURI(Uri.parse("android.resource://"
				+ this.getPackageName() + "/" + R.raw.video));

		// videoView.setVideoPath("\\sdcard\\Download\\apartamento.mp4");

		videoView.start();
		mediaController.show();
		videoView.requestFocus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_video, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		

		ImageView visorImatge = (ImageView) findViewById(R.id.imgpanell);
		if (panell) {
			visorImatge.setImageResource(R.drawable.vista);
			panell=false;
		} else {
			visorImatge.setImageResource(R.drawable.planell);
			panell=true;
		}
	}

}
