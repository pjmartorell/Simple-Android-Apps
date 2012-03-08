package jedi.thdsandtsks;

import android.app.Activity;
import android.net.sip.SipSession.Listener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThreadsAndTasksActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MyListener l1 = new MyListener();
		Button bt0 = (Button) findViewById(R.id.task);
		bt0.setOnClickListener(l1);
        
    }
    
    private class MyTask extends AsyncTask <Integer, Integer, String> {

		@Override
		protected String doInBackground(Integer... arg0) {
			// TODO Auto-generated method stub
			publishProgress(int);
			return resultado;
		}
		
    	@Override
		protected void onPostExecute(String result){
			tView.setText(result);
			super.onPostExecute();
			
		}
    }
    
    public class MyListener implements OnClickListener {

		Button op1 = (Button) findViewById(R.id.task);
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.task:
				MyTask task = new MyTask();
				task.execute(4);
				task.cancel();
				Log.v("Task", "Sumant");
				break;
			default:
			}
					
		}
    }

}