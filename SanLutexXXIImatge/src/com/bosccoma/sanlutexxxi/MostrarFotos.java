package com.bosccoma.sanlutexxxi;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarFotos extends Activity {

	ImageView imagenSeleccionada;

	public final static String POSICIO = "POSICIO";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_fotos);

		final Integer[] imagenes = { R.drawable.ima0, R.drawable.ima1,
				R.drawable.ima2, R.drawable.ima3, R.drawable.ima4,
				R.drawable.ima5, R.drawable.ima6, R.drawable.ima7,
				R.drawable.ima8, R.drawable.ima9, R.drawable.ima10,
				R.drawable.ima11, R.drawable.ima12, R.drawable.ima13,
				R.drawable.ima14, R.drawable.ima15, R.drawable.ima16,
				R.drawable.ima17, R.drawable.ima18, R.drawable.ima19,
				R.drawable.ima20 };

		GridView gridView = (GridView) findViewById(R.id.grid);

		// el número de columnas se calculará en función del tamaño de pantalla
		// y la posición
		boolean bigScreen = (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			if (bigScreen) {
				gridView.setNumColumns(4);
			} else {
				gridView.setNumColumns(3);
			}
		} else {
			if (bigScreen) {
				gridView.setNumColumns(3);
			} else {
				gridView.setNumColumns(2);
			}
		}

		gridView.setAdapter(new GalleryAdapter(this, imagenes));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(
						MostrarFotos.this,
						((TextView) view.findViewById(R.id.grid_text))
								.getText() + " seleccionada",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MostrarFotos.this, AmpliarFoto.class);

				intent.putExtra(POSICIO, position);

				startActivity(intent);

			}
		});

	}
}