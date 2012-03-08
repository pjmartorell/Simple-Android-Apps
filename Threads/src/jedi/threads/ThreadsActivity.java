package jedi.threads;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ThreadsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Thread th = new Thread(new Runnable() {
        	public void run(){
        		int i = 0;
        		while(i < 10000){
        			try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			Log.v("Thread", "awake");
        			i += 1000;
        		}
        	}
        });
        th.start();
    }
}