package edu.nps.cs.parker.gait;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	String databaseName; //databaseName variable to use internal to class
	private final static int    databaseVersion = 1;
	 
	public DatabaseOpenHelper(Context context, String dbName) {
	    super(context, dbName, null, databaseVersion);
	    this.databaseName = dbName; //set internal variable value to passed in dbName
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		//sql query
		//CREATE TABLE databaseName ( variable list );
		//TODO: Pass in variable list from constructor
		String createSql = "CREATE TABLE " + databaseName +
	            " (_id integer primary key autoincrement, " + 
	            "x real not null, y real not null, z real not null);";
	    arg0.execSQL(createSql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		//onUpgrade DROP TABLE IF EXISTS databaseName
		//then create new data with values
		arg0.execSQL("DROP TABLE IF EXISTS " + databaseName);
	    onCreate(arg0);

	}
	

}
