package pj.martorell;

import java.util.ArrayList;
import java.util.Collections;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

// L'activity té problemes de concurrencia. Cal controlar que quan s'estigui comprovant 
// els id's de les cartes no hi puguin haver altres events que modifiquin aquests id's.
public class MemoryActivity extends Activity {

	private final String tag = "MEM";
	private ArrayList<Drawable> imatges;
	private Drawable backImage;
	private int firstCard;
	private int secondCard;
	int tries;
	int costat;
	int id;
	ImageButton card1;
	ImageButton card2;
	int card1ID;
	int card2ID;
	int complete;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mem_layout);
		carregaImatges();
		backImage = getResources().getDrawable(R.drawable.back);
		Spinner sp = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.game_mode, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}

	// Shuffle i carrega de les cartes
	private void carregaImatges() {
		imatges = new ArrayList<Drawable>();
		Resources res = getResources();

		imatges.add(res.getDrawable(R.drawable.sushi01));
		imatges.add(res.getDrawable(R.drawable.sushi02));
		imatges.add(res.getDrawable(R.drawable.sushi03));
		imatges.add(res.getDrawable(R.drawable.sushi04));
		imatges.add(res.getDrawable(R.drawable.sushi05));
		imatges.add(res.getDrawable(R.drawable.sushi06));
		imatges.add(res.getDrawable(R.drawable.sushi07));
		imatges.add(res.getDrawable(R.drawable.sushi08));
		imatges.add(res.getDrawable(R.drawable.sushi09));
		imatges.add(res.getDrawable(R.drawable.sushi10));
		imatges.add(res.getDrawable(R.drawable.sushi11));
		imatges.add(res.getDrawable(R.drawable.sushi12));
		imatges.add(res.getDrawable(R.drawable.sushi13));
		imatges.add(res.getDrawable(R.drawable.sushi14));
		imatges.add(res.getDrawable(R.drawable.sushi15));
		imatges.add(res.getDrawable(R.drawable.sushi16));
		imatges.add(res.getDrawable(R.drawable.sushi17));
		imatges.add(res.getDrawable(R.drawable.sushi18));
		imatges.add(res.getDrawable(R.drawable.sushi19));
		imatges.add(res.getDrawable(R.drawable.sushi20));

		Collections.shuffle(imatges);
	}

	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View view, int pos,
				long id) {
			int x;
			switch (pos) {
			case 0:
				x = 4;
				Log.v(tag, "mode: 4 x 4");
				break;
			case 1:
				Log.v(tag, "mode: 5 x 5");
				x = 5;
				break;
			case 2:
				Log.v(tag, "mode: 6 x 6");
				x = 6;
				break;
			default:
				return;
			}
			newGame(x);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// Nothing
		}
	}

	// Dibuixem el tauler i reinicialitzem el comptador de jugades
	public void newGame(int Tsize) {
		firstCard = -1;
		secondCard = -1;
		tries = 0;
		complete = Tsize * Tsize / 2;
		costat = Tsize;
		int counter = 0;
		carregaImatges();
		((TextView) findViewById(R.id.tries)).setText("Intents: " + tries);
		TableRow rowTauler = (TableRow) findViewById(R.id.rowTauler);
		rowTauler.removeAllViews(); // Esborrem tauler anterior
		TableLayout tauler = new TableLayout(getApplicationContext());
		rowTauler.setGravity(Gravity.CENTER);
		rowTauler.addView(tauler);
		for (int i = 0; i < costat; i++) {
			TableRow tr = new TableRow(getApplicationContext());
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			for (int j = 0; j < costat; j++) {
				ImageButton button = new ImageButton(getApplicationContext());
				button.setAdjustViewBounds(true);
				button.setMaxHeight(40);
				button.setMaxWidth(40);
				button.setImageDrawable(backImage);
				button.setId(counter);
				button.setOnClickListener(new CardListener());
				counter++;
				tr.addView(button);

			}
			tauler.addView(tr);
			tauler.setGravity(Gravity.CENTER);
		}
	}

	// Listener per les cartes
	public class CardListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			id = v.getId();
			if (firstCard != -1 && secondCard != -1) return;
			int imgID = id % (costat * costat / 2);
			// Cas primer carta
			if (firstCard == -1) {
				firstCard = imgID;
				card1ID = id;
				card1 = (ImageButton) v;
				card1.setImageDrawable(imatges.get(imgID));
			}
			// Cas segona carta
			else {
				secondCard = imgID;
				card2ID = id;
				card2 = (ImageButton) v;
				card2.setImageDrawable(imatges.get(imgID));
				// Cas de cartes iguals
				if (firstCard == secondCard && card1ID != card2ID) {
					card1.postDelayed(new Runnable() {
						public void run() {
							card1.setVisibility(View.INVISIBLE);
							card2.setVisibility(View.INVISIBLE);
							firstCard = -1;
							secondCard = -1;
							Log.v("Thread_Invisible", "awake");
						}
					}, 2000);
					complete--;
					if (complete == 0) {
						Toast.makeText(getApplicationContext(),
								"Partida completada!", Toast.LENGTH_LONG)
								.show();
					}
				}
				// Cas de cartes diferents
				else {
					card1.postDelayed(new Runnable() {
						public void run() {
							card1.setImageDrawable(backImage);
							card2.setImageDrawable(backImage);
							firstCard = -1;
							secondCard = -1;
							Log.v("Thread diferent cards", "awake");
						}
					}, 2000);
				}

				tries++;
			}
			((TextView) findViewById(R.id.tries)).setText("Intents: " + tries);
			Log.v(tag, "firstID: " + firstCard + " | secondID: " + secondCard);
		}

	}
}