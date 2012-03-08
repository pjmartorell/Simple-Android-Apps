package jedi.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SQLiteActivity extends Activity {
	
	int tries = 0;
	String user = "pepito";
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btInsert = (Button) findViewById(R.id.insert);
        Button btSelect = (Button) findViewById(R.id.select);
        
        btInsert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//INSERT
				StatisticsOpenHelper soh = new StatisticsOpenHelper(getApplicationContext());
				SQLiteDatabase db = soh.getWritableDatabase();
				if (db != null) {
				    db.execSQL("INSERT INTO statistics (user, tries) VALUES ('" + user + "'," + tries + ")");
				    db.close();
				}
				tries++;
			}
		});
        
        btSelect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				TextView tv = (TextView) findViewById(R.id.textView1);
				String text = "";
				//SELECT
				StatisticsOpenHelper soh = new StatisticsOpenHelper(getApplicationContext());
				SQLiteDatabase db = soh.getReadableDatabase();
				Cursor cursor;
				if (db != null) {
					cursor = db.rawQuery("SELECT user, tries FROM statistics WHERE user ='" + user + "' ORDER BY tries DESC",null);
				    while (cursor.moveToNext()) {
				    	text = text + String.valueOf(cursor.getString(cursor.getColumnIndex("user"))) + " " + String.valueOf(cursor.getInt(cursor.getColumnIndex("tries"))) + "\n";
						Log.v("db query", String.valueOf(cursor.getString(cursor.getColumnIndex("user"))) + " " + String.valueOf(cursor.getInt(cursor.getColumnIndex("tries"))));
Log.v("caca", text);
				    }
				    tv.setText(text);
				    db.close();
				}
			}
		});
    }
}


