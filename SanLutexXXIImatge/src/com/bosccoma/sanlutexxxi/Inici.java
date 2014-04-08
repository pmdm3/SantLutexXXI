package com.bosccoma.sanlutexxxi;

import java.io.File;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Inici extends Activity {

	// un codi per l'aplicaci� a obrir
	static final int CAMERA_APP_CODE = 100;

	// on visualitzarem la imatge capturada
	private ImageView imatge;
	// el fitxer on es guardar� la imatge
	private File tempImageFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ferfoto);

		imatge = (ImageView) findViewById(R.id.imatgeCap);
	}

	public void continuar(View view) {
		final EditText txtNom = (EditText) findViewById(R.id.editTextNom);
		final EditText txtCognom = (EditText) findViewById(R.id.editTextCognom);

		// Creamos el Intent
		Intent intent = new Intent(Inici.this, MenuInicial.class);

		// Creamos la informaci�n a pasar entre actividades
		Bundle b = new Bundle();

		b.putString("NOM", txtNom.getText().toString());
		b.putString("COGNOM", txtCognom.getText().toString());
		b.putString("FITXER", tempImageFile.getName());
		b.putString("PATH", tempImageFile.getPath());
		// A�adimos la informaci�n al intent
		intent.putExtras(b);

		// Iniciamos la nueva actividad
		startActivity(intent);
	}

	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	public void ferUnaFoto(View view) throws IOException {
		if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
			// intenci� de fer una foto
			Intent takePictureIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			// crear la ruta del fitxer on desar la foto
			tempImageFile = crearFitxer();
			// li passem par�metres a l'Inent per indicar que es vol guarda
			// la captura en un fitxer
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(tempImageFile));
			// inciar l'intent
			startActivityForResult(takePictureIntent, CAMERA_APP_CODE);
		} else {
			Toast.makeText(this, "No hi ha cap aplicaci� per capturar fotos",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_APP_CODE) {
			if (resultCode == RESULT_OK) {
				// mostrar els bytes rebuts de l'intent
				// imatge.setImageBitmap((Bitmap) data.getExtras().get("data"));
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
		}
	}

	private File crearFitxer() {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String imageFileName = "foto" + timeStamp + ".jpg";
		File path = new File(Environment.getExternalStorageDirectory(),
				this.getPackageName());
		if (!path.exists())
			path.mkdirs();

		return new File(path, imageFileName);
	}

}
