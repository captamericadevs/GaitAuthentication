package edu.nps.cs.parker.gait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DatabaseAdapter {
	private Context context;
	private SQLiteDatabase database;
	private DatabaseOpenHelper dbHelper;
	String databaseName;
	 
	public DatabaseAdapter(Context context, String dbName) {
	    this.context = context;
	    this.databaseName = dbName; //Pass in dbName varaible
	}
	
	public DatabaseAdapter open() throws SQLException {
	    dbHelper = new DatabaseOpenHelper(context, databaseName); //pass dbName to DbOpenHelper
	    database = dbHelper.getWritableDatabase();
	    return this;
	}
	 
	public void copyFile()
    {
        try 
        {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            
            String currentDBPath = data.getAbsolutePath() + File.separator + "/edu.nps.cs.parker.gait/databases/"+ databaseName;
            String backupDBPath = databaseName;
            File currentDB = new File(data, currentDBPath);
            File backupDB = new File(sd.getAbsolutePath(), backupDBPath);
            backupDB.createNewFile();

            if(currentDB.exists()) {
            	FileChannel src = new FileInputStream(currentDB).getChannel();
            	FileChannel dst = new FileOutputStream(backupDB).getChannel();
            	dst.transferFrom(src, 0, src.size());
            	src.close();
            	dst.close();
            }
            
                           
        } 
        catch (Exception e) {
            Log.w("Settings Backup", e);
        }
    }
	
	public void close() {
	    dbHelper.close();
	}
		
	//Creates record to insert into Gait table
	public long createRecordGait(float xvalue, float yvalue, float zvalue) {
	    ContentValues contentValue = new ContentValues();      
	    contentValue.put("x", xvalue);      
	    contentValue.put("y", yvalue);
	    contentValue.put("z", zvalue);
	    return database.insert(databaseName, null, contentValue);
	}
	
	//Creates record to insert into Text collection table
	public long createRecordText(int temp) {
		ContentValues contentValue = new ContentValues();     
		//TODO: add put statements for each feature (column) desired in database
		contentValue.put("temp", temp);
		return database.insert(databaseName, null, contentValue);
	}
		
	//Creates record to insert into Signal collection table
	public long createRecordSig(float temp) {
		ContentValues contentValue = new ContentValues();    
		//TODO: add put statements for each feature (column) desired in database
		contentValue.put("temp", temp); 
		return database.insert(databaseName, null, contentValue);
	}
	
	
}
