package aplicacio.prova;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity3 extends Activity {
    /** Called when the activity is first created. */
	protected static final String TAG = "MYAPP";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view3);
        Button bt3 = (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
					Log.v(TAG, "Anem a iniciar l'Activity1");
					startActivity(new Intent(getApplicationContext(), Activity1.class));
			}
		});
    }
    
    public void onStart(){
    	super.onStart();
    	Log.v(TAG, "Activity3 està visible");
    }
    
    public void onResume(){
    	super.onResume();
    	Log.v(TAG, "Tornant a Activity3");
    }
    
    public void onPause(){
    	super.onPause();
    	Log.v(TAG, "Activity3 perd focus");
    }
    
    public void onStop(){
    	super.onStop();
    	Log.v(TAG, "Activity3 s'ha parat");
    }
    
    public void onDestroy(){
    	super.onDestroy();
    	Log.v(TAG, "Activity3 s'ha destruit");
    	
    }
}