package jedi.bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BoundService extends Service {
	
	private static final String TAG = "Bound";
	public int codigo = 2;
	private final IBinder binder = new MyBinder();
	
	public class MyBinder extends Binder {
		BoundService getService() {
			return BoundService.this;
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Service binded", Toast.LENGTH_SHORT).show();
		Log.v(TAG, "Servei binded");
		return binder;
	}
	@Override
	public void onDestroy() {
		//TODO código para liberar recursos
		Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show();
		Log.v(TAG, "Servei destroyed");
	}
	public int multiplica() {
		return codigo*5;
	}
	

}
