package jedi.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DialogsActivity extends Activity {
	
	static final int DIALOG_PAUSED_ID = 0;
	static final int DIALOG_GAMEOVER_ID = 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MyListener l1 = new MyListener();
        Button bt0 = (Button) findViewById(R.id.button1);
		bt0.setOnClickListener(l1);
        Button bt1 = (Button) findViewById(R.id.button2);
		bt1.setOnClickListener(l1);
    }
    
//    @Override
//    public void OnBackPressed(){
//    }
    
    protected Dialog onCreateDialog(int id){
    	Dialog dialog;
    	switch(id){
    	case DIALOG_PAUSED_ID:
    		dialog = createDialog();
    		break;
    	case DIALOG_GAMEOVER_ID:
    		//dialog = createDialog2();
    		dialog = createDialog3();
    	    //showDialog(DIALOG_PAUSED_ID)
    	    //dialog.dismiss();
    	    //dialog.removeDialog();
    		break;
    	default:
    		dialog = null;
    	}
    	return dialog;
    }

    public AlertDialog createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estàs segur que vols sortir?").setCancelable(false)
        		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						DialogsActivity.this.finish();
					}
				}).setNegativeButton("No", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.cancel();
					}
				}).setNeutralButton("Res", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).setTitle("Un dialog qualsevol");
        return builder.create();
    }
    
    public AlertDialog createDialog2(){
    	final CharSequence[] items = {"Red", "Green", "Blue" };
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Pick a color");
    	builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast t = Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT);
				t.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
				t.show();
			}
		});
    	return builder.create();
    }
    
    public AlertDialog createDialog3(){
    	final CharSequence[] items = {"Red", "Green", "Blue" };
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Pick a color");
    	builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
			}
		});
		return builder.create();
    }
    
    public class MyListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button1:
				showDialog(DIALOG_PAUSED_ID);
				break;
			case R.id.button2:
				showDialog(DIALOG_GAMEOVER_ID);
				break;
			default:
			}
		}
    }
}   