package aplicacio.prova;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity2 extends Activity {
    /** Called when the activity is first created. */
	protected static final String TAG = "MYAPP";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view2);
        Button bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				Log.v(TAG, "Anem a iniciar l'Activity3");
				startActivity(new Intent(getApplicationContext(), Activity3.class));
			}
		});
    }
    
    public void onStart(){
    	super.onStart();
    	Context context = getApplicationContext();
    	CharSequence text = "Hola Toast!";
    	int duration = Toast.LENGTH_SHORT;
    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    	Log.v(TAG, "Activity2 està visible");
    }
    
    public void onResume(){
    	super.onResume();
    	Log.v(TAG, "Tornant a Activity2");
    }
    
    public void onPause(){
    	super.onPause();
    	Log.v(TAG, "Activity2 perd focus");
    }
    
    public void onStop(){
    	super.onStop();
    	Log.v(TAG, "Activity2 s'ha parat");
    }
    
    public void onDestroy(){
    	super.onDestroy();
    	Log.v(TAG, "Activity2 s'ha destruit");
    	
    }
}