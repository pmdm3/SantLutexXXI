package com.bosccoma.sanlutexxxi;

import android.content.Context;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryAdapter extends BaseAdapter {
	Context context;
	Integer[] imagenes;
	String[] texto = { 	"Platja Burró", 
						"Platja Garbet", 
						"Platja Les Barques",
						"Platja Els Morts", 
						"Cala Rovellada", 
						"Platja Macarella",
						"Cala Raona", 
						"Cap Ras", 
						"Platja Atzuzenes",
						"Parc Infantil Bambi", 
						"Platja Burró", 
						"Urbanització Sant Miquel",
						"Parc Infantil Patufets",
						"Pista Padell",
						"Platja Sucre",
						"Platja Albera",
						"Molinás",
						"Serra Albera",
						"Museu Dalí",
						"Castell Perelada",
						"Platja Albera"};

	// guardamos las imágenes reescaladas para mejorar el rendimiento ya que
	// estas operaciones son costosas
	// se usa SparseArray siguiendo la recomendación de Android Lint
	static SparseArray<Bitmap> imagenesEscaladas = new SparseArray<Bitmap>(7);

	public GalleryAdapter(Context context, Integer[] imagenes) {
		super();
		this.imagenes = imagenes;
		this.context = context;
	}

	@Override
	public int getCount() {
		return imagenes.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;

		if (convertView == null) {
			holder = new Holder();
			LayoutInflater ltInflate = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = ltInflate.inflate(R.layout.grid_single, null);

			holder.setTextView((TextView) convertView
					.findViewById(R.id.grid_text));
			holder.setImage((ImageView) convertView
					.findViewById(R.id.grid_image));

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		if (imagenesEscaladas.get(position) == null) {
			Bitmap bitmap = BitmapUtils.decodeSampledBitmapFromResource(
					context.getResources(), imagenes[position], 100, 0);
			imagenesEscaladas.put(position, bitmap);
		}

		holder.getImage().setImageBitmap(imagenesEscaladas.get(position));
		// holder.getTextView().setText(position + "");
		holder.getTextView().setText(texto [position]+ "");
		return convertView;
	}

	class Holder {
		ImageView image;

		TextView textView;

		public ImageView getImage() {
			return image;
		}

		public void setImage(ImageView image) {
			this.image = image;
		}

		public TextView getTextView() {
			return textView;
		}

		public void setTextView(TextView textView) {
			this.textView = textView;
		}

	}

}
