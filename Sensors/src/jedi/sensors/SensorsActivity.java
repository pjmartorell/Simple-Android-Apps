package jedi.sensors;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
//import android.os.Vibrator;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SensorsActivity extends Activity {
	
	private Sensor sensor;
	private SensorManager sm;
	private ProgressBar pb;
	TextView t1;
	TextView t2;
	TextView t3;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        t1 = (TextView)findViewById(R.id.textView1);
        t2 = (TextView)findViewById(R.id.textView2);
        t3 = (TextView)findViewById(R.id.textView3);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        
        if(sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
        	sensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }
    }
    
    @Override
    protected void onPause(){
    	super.onPause();
    	if(sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
    		sm.unregisterListener(mySensorListener);
    	}
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	if(sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
    		sm.registerListener(mySensorListener, sensor, SensorManager.SENSOR_DELAY_UI);
    	}
    }
    
    SensorEventListener mySensorListener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			DecimalFormat d = new DecimalFormat("000");
			Double modulo = Math.sqrt(event.values[0] * event.values[0] + 
					event.values[1] * event.values[1] + event.values[2] * event.values[2]);
			pb = (ProgressBar)findViewById(R.id.progressBar);
			Double val1 = Double.valueOf(d.format((double)event.values[0]));
			Double val2 = Double.valueOf(d.format((double)event.values[1]));
			Double val3 = Double.valueOf(d.format((double)event.values[2]));
			t1.setText("x" + val1.toString());
			t2.setText("y" + val2.toString());
			t3.setText("z" + val3.toString());
			pb.setProgress(modulo.intValue());
			
//			if(modulo > 100){
//				Vibrator v = (Vibrator)getSystemService(getApplicationContext().VIBRATOR_SERVICE);
//				v.vibrate(20);
//			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
}