package com.bosccoma.sanlutexxxi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuInicial extends Activity {
	private MediaPlayer so = null;
	// on visualitzarem la imatge capturada
	private ImageView imatge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		TextView txtSalutacio = (TextView) findViewById(R.id.txtSalutacio);
		String nomFitxer = null;
		File tempImageFile = null;
		Bundle bundle = this.getIntent().getExtras();

		imatge = (ImageView) findViewById(R.id.imgusuari);

		txtSalutacio.setText(bundle.getString("NOM") + "  "
				+ bundle.getString("COGNOM"));

		so = MediaPlayer.create(this, R.raw.blowing);
		so.start();
		

		nomFitxer = (bundle.getString("FITXER"));
		File path = new File(Environment.getExternalStorageDirectory(),
				this.getPackageName());
		tempImageFile = new File (path,nomFitxer);

		
		try {
			// mostrar el fitxer que ha desat l'app de captura
			imatge.setImageBitmap(Media.getBitmap(getContentResolver(),
					Uri.fromFile(tempImageFile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO he de gestionar els errors
			e.printStackTrace();
		}
	}

	public void mostrarVideo(View view) throws IOException {
		final TextView txtSalutacio = (TextView) findViewById(R.id.txtSalutacio);
		so.stop();

		// Creamos el Intent
		Intent intent = new Intent(MenuInicial.this, MostrarVideo.class);

		// Creamos la información a pasar entre actividades
		Bundle b = new Bundle();

		b.putString("SALUTACIO", txtSalutacio.getText().toString());

		// Añadimos la información al intent
		intent.putExtras(b);

		// Iniciamos la nueva actividad
		startActivity(intent);
	}

	public void mostrarFotos(View view) throws IOException {
		final TextView txtSalutacio = (TextView) findViewById(R.id.txtSalutacio);

		// Create an image file name
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher_bck);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] aImatge = baos.toByteArray();

		// Creamos el Intent
		Intent intent = new Intent(MenuInicial.this, MostrarFotos.class);

		// Creamos la información a pasar entre actividades
		Bundle b = new Bundle();

		b.putString("SALUTACIO", txtSalutacio.getText().toString());

		// intent.putExtra("IMATGE",
		// Uri.fromFile(tempImageFile));

		// Añadimos la información al intent
		intent.putExtras(b);

		// Iniciamos la nueva actividad
		startActivity(intent);
	}

	public void gravarMissatge(View view) throws IOException {
		final TextView txtSalutacio = (TextView) findViewById(R.id.txtSalutacio);

		so.stop();
		// Create an image file name
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher_bck);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] aImatge = baos.toByteArray();

		// Creamos el Intent
		Intent intent = new Intent(MenuInicial.this, GravarMsg.class);

		// Creamos la información a pasar entre actividades
		Bundle b = new Bundle();

		b.putString("SALUTACIO", txtSalutacio.getText().toString());

		// Añadimos la información al intent
		intent.putExtras(b);

		// Iniciamos la nueva actividad
		startActivity(intent);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
