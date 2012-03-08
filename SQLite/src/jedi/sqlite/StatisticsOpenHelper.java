package jedi.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StatisticsOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "project";
    private static final String STATISTICS_TABLE_NAME = "statistics";
    private static final String STATISTICS_TABLE_CREATE = "CREATE TABLE " + STATISTICS_TABLE_NAME + " (user TEXT, tries INTEGER)";

    StatisticsOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STATISTICS_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + STATISTICS_TABLE_NAME);
		db.execSQL(STATISTICS_TABLE_CREATE);
	}
}