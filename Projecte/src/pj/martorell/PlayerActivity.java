package pj.martorell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class PlayerActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);
        MyListener1 l1 = new MyListener1();
		Button bt0 = (Button) findViewById(R.id.play);
		Button bt1 = (Button) findViewById(R.id.stop);
		bt0.setOnClickListener(l1);
		bt1.setOnClickListener(l1);
    }
    

    public class MyListener1 implements OnClickListener {
    	
    	Intent intent = new Intent(PlayerActivity.this, MyService.class);
    	Button bt0 = (Button) findViewById(R.id.play);
    	boolean playing = true;
    	
    	@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.play:
				startService(intent);
				if(playing) bt0.setText("Pause");
				else bt0.setText("Play");
				playing = !playing;
				break;
			case R.id.stop:
				stopService(intent);
				bt0.setText("Play");
				playing = true;
				break;
			default:
			}
    	}
    }
}
