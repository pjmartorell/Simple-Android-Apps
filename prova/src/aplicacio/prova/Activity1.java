package aplicacio.prova;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Activity1 extends Activity {
    /** Called when the activity is first created. */
	protected static final String TAG = "MYAPP";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view1);
        Button bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				Log.v(TAG, "Anem a iniciar l'Activity2");
				startActivity(new Intent(getApplicationContext(), Activity2.class));
			}
		});
    }
    
    public void onStart(){
    	super.onStart();
    	Log.v(TAG, "Activity1 està visible");
    }
    
    public void onResume(){
    	super.onResume();
    	Log.v(TAG, "Tornant a Activity1");
    }
    
    public void onPause(){
    	super.onPause();
    	Log.v(TAG, "Activity1 perd focus");
    }
    
    public void onStop(){
    	super.onStop();
    	Log.v(TAG, "Activity1 s'ha parat");
    }
    
    public void onDestroy(){
    	super.onDestroy();
    	Log.v(TAG, "Activity1 s'ha destruit");
    	
    }
}