package pj.martorell;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("Perfil d'Usuari");
        setContentView(textview);
	}
}