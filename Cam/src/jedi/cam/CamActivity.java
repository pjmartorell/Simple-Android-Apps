package jedi.cam;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CamActivity extends Activity {

	/** Called when the activity is first created. */
	private static int TAKE_PICTURE = 1;
	private static int SELECT_PICTURE = 2;
	private String name = "";

	OnClickListener tkseClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			int code = 0;

			if (v.getId() == R.id.button1) {
				Uri output = Uri.fromFile(new File(name));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
				code = TAKE_PICTURE;

			} else if (v.getId() == R.id.button2) {
				intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
				code = SELECT_PICTURE;
			}

			startActivityForResult(intent, code);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == TAKE_PICTURE) {

			/**
			 * A partir del nombre del archivo ya definido lo buscamos y creamos
			 * el bitmap
			 */

			if (data != null) {
				/**
				 * Si tenemos el dato, lo mostramos por el image view
				 */
				if (data.hasExtra("data")) {
					try{
						ImageView iv = (ImageView) findViewById(R.id.imageView1);
						iv.setImageBitmap(Bitmap.createScaledBitmap(
								(Bitmap) data.getParcelableExtra("data"), 250, 250,
								true));
					}
					catch (Exception e) {
						Toast.makeText(getApplication(), "Cal que facis una foto!", Toast.LENGTH_SHORT).show();
					}

				}
			}

			ImageView iv = (ImageView) findViewById(R.id.imageView1);
			Bitmap avatar = BitmapFactory.decodeFile(name);
			// crea la imagen en
			// la ruta
			// especificada
			// Super ineficiente por usar la imagen grande
			iv.setImageBitmap(Bitmap.createScaledBitmap(avatar, 250, 250, true));

			/**
			 * Recibimos el URI de la imagen y construimos un Bitmap a partir de
			 * un stream de Bytes
			 */
		} else if (requestCode == SELECT_PICTURE) {
			Uri selectedImage = data.getData();
			InputStream is;
			try {
				is = getContentResolver().openInputStream(selectedImage);
				BufferedInputStream bis = new BufferedInputStream(is);
				Bitmap bitmap = BitmapFactory.decodeStream(bis);
				bitmap = Bitmap.createScaledBitmap(bitmap, 250, 250, true);
				ImageView iv = (ImageView) findViewById(R.id.imageView1);
				iv.setImageBitmap(bitmap);

			} catch (FileNotFoundException e) {
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		name = Environment.getExternalStorageDirectory()
				+ "/JediProy/imgJEdi.jpg";

		/* If primera ejecuci—n main2, else main1 (seleccionar o crear user) */
		Button bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(tkseClickListener);
		Button bt2 = (Button) findViewById(R.id.button2);
		bt2.setOnClickListener(tkseClickListener);
	}


}