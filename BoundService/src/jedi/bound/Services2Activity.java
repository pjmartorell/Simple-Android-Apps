package jedi.bound;

import jedi.bound.BoundService.MyBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//Exemple de Bound Service
public class Services2Activity extends Activity {
    
    private static final String TAG = "Bound";
	BoundService BService;
    boolean bound = false;
    Button bt0, bt1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MyListener1 l1 = new MyListener1();
		bt0 = (Button) findViewById(R.id.connect);
		bt1 = (Button) findViewById(R.id.stop);
		bt0.setOnClickListener(l1);
		bt1.setOnClickListener(l1);
		
        //Intent intent = new Intent("BOUNDSERVICE");
        Intent intent = new Intent(Services2Activity.this, BoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    
	private ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log.v(TAG, "Connexio amb el servei");
			bound = true;
			MyBinder binder = (MyBinder) service;
			BService = binder.getService();
		}
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			bound = false;
		}
	};
	
    public class MyListener1 implements OnClickListener {
    	@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.connect:
				 if (bound) {
			       	BService.codigo = 3;
			      	int res = BService.multiplica();
			       	Toast.makeText(getApplicationContext(), "resultat (codigo * 5) = " + res, Toast.LENGTH_SHORT).show();
			      	Log.v(TAG, "resultat (codigo * 5) = " + res);
			      	Log.v(TAG, "Crida a servei");
			    }
				break;
			case R.id.stop:
				if (bound) {
					//el servei mor i cal tornar a fer un bind
					unbindService(connection);
					bt0.setClickable(false);
					bt1.setClickable(false);
				}
				break;
			default:
			}
    	}
    }
}