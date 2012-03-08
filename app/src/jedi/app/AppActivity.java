package jedi.app;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AppActivity extends Activity {
    private static final String TAG = "Contact";
	private static final int HELLO_ID = 1;
	Menu menuapp;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Get content provider and solver
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        
        //Let activity manage cursor
        startManagingCursor(cursor);
        Log.d(TAG, "cursor.getcount()=" + cursor.getCount());
        
        //Get de list view
        ListView listView = (ListView) findViewById(R.id.listView1);
        String[] from = { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID };
        int[] to = { R.id.textName, R.id.textValue };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.main, cursor, from, to);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu1, menu);
    	menuapp = menu;
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	//Gestió de la selecció
    	switch(item.getItemId()){
    	case R.id.op1:
    		op1();
    		return true;
    	case R.id.op2:
    	    NotificationManager myManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	    int icon = R.drawable.ic_launcher;
    	    CharSequence texto1 = "En moviment";
    	    long when = System.currentTimeMillis();
    	    Notification notification = new Notification(icon, texto1, when);
    	    Context context = getApplicationContext();
    	    CharSequence contentTitle = "Notificació";
    	    CharSequence contentText = "Hola món!";
    	    // Activity a la que apunta la notificacio
    	    Intent notificationIntent = new Intent(this, AppActivity.class);
    	    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
    	    notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
    	    notification.flags |= Notification.FLAG_AUTO_CANCEL;
    	    //notification.defaults |= Notification.DEFAULT_VIBRATE;
    	    long[] vibration = {0, 100, 200, 300};
    	    notification.vibrate = vibration;
    	    myManager.notify(HELLO_ID, notification);
    	    return true;
    	case R.id.op3:
    		//op3();
    		return true;
    	case R.id.help:
    		//help();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    public void op1(){
    	menuapp.add("OpcioExtra");
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu2, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
    	switch(item.getItemId()){
    	case R.id.edit:
    		//edit(info.id);
    		return true;
    	case R.id.del:
    		//del(info.id);
    		return true;
    	default:
    		return super.onContextItemSelected(item);
    	}
    }
    
    
    
}