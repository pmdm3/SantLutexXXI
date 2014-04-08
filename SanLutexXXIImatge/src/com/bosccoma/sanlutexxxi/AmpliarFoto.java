package com.bosccoma.sanlutexxxi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AmpliarFoto extends Activity {
	private Drawable foto;
	private Integer posicio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ampliar_foto);
		ImageView imatge = (ImageView) findViewById(R.id.imga);

		Bundle extras = getIntent().getExtras();

		posicio = extras.getInt(MostrarFotos.POSICIO);

		if (posicio != null) {
			foto = obtenirImatge(posicio);
		} else {

			Toast.makeText(AmpliarFoto.this,
					(" No existeix l'ampliació d'aquesta imatge"),
					Toast.LENGTH_SHORT).show();
		}
		imatge.setImageDrawable(foto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ampliar_foto, menu);
		return true;
	}

	public Drawable obtenirImatge(Integer p) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Drawable foto = null;
		if (posicio == 0)
			foto = getResources().getDrawable(R.drawable.ima0);
		if (posicio == 1)
			foto = getResources().getDrawable(R.drawable.ima1);
		else if (posicio == 2)
			foto = getResources().getDrawable(R.drawable.ima2);
		else if (posicio == 3)
			foto = getResources().getDrawable(R.drawable.ima3);
		else if (posicio == 4)
			foto = getResources().getDrawable(R.drawable.ima4);
		else if (posicio == 5)
			foto = getResources().getDrawable(R.drawable.ima5);
		else if (posicio == 6)
			foto = getResources().getDrawable(R.drawable.ima6);
		else if (posicio == 7)
			foto = getResources().getDrawable(R.drawable.ima7);
		else if (posicio == 8)
			foto = getResources().getDrawable(R.drawable.ima8);
		else if (posicio == 9)
			foto = getResources().getDrawable(R.drawable.ima9);
		else if (posicio == 10)
			foto = getResources().getDrawable(R.drawable.ima10);
		else if (posicio == 11)
			foto = getResources().getDrawable(R.drawable.ima11);
		else if (posicio == 12)
			foto = getResources().getDrawable(R.drawable.ima12);
		else if (posicio == 13)
			foto = getResources().getDrawable(R.drawable.ima13);
		else if (posicio == 14)
			foto = getResources().getDrawable(R.drawable.ima14);
		else if (posicio == 15)
			foto = getResources().getDrawable(R.drawable.ima15);
		else if (posicio == 16)
			foto = getResources().getDrawable(R.drawable.ima16);
		else if (posicio == 17)
			foto = getResources().getDrawable(R.drawable.ima17);
		else if (posicio == 18)
			foto = getResources().getDrawable(R.drawable.ima18);
		else if (posicio == 19)
			foto = getResources().getDrawable(R.drawable.ima19);
		else if (posicio == 20)
			foto = getResources().getDrawable(R.drawable.ima20);

		return foto;

	}

}
