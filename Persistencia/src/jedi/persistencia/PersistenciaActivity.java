package jedi.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PersistenciaActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MyListener l1 = new MyListener();
		Button bt0 = (Button) findViewById(R.id.store);
		Button bt1 = (Button) findViewById(R.id.read);
		bt0.setOnClickListener(l1);
		bt1.setOnClickListener(l1);
    }
    
  //Internal Storage Sample
  	public void storage(){
  		try{
  			String FILENAME = "file1";
  			String hello = "hello world";
  			
  			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
  			fos.write(hello.getBytes());
  			fos.close();
  		}
  		catch (Exception e) {
  			// TODO: handle exception
  		}

  	}
  	
  	public String read_internal() {
  		String FILENAME = "file1";
  		String hello = "hello world";
  		try{
  			FileInputStream fos = openFileInput(FILENAME);
  			fos.read(hello.getBytes());
  			fos.close();
  		}
  		catch (Exception e){
  			
  		}
  		return hello;
  	}
  	
  	public class MyListener implements OnClickListener {
		TextView tv = (TextView) findViewById(R.id.hello);

		@Override
		public void onClick(View v){
			switch (v.getId()) {
			case R.id.store:
				storage();
				break;
			case R.id.read:
				String hello = read_internal();
				tv.setText(hello);
				break;

			}
		}
  	}
}