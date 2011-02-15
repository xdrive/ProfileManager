package com.xdrive.profilemanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Provides access to the ProfileManage database.
 * 
 * @author Dmytro Kovalenko
 */
public class ProfileManagerDatabase extends SQLiteOpenHelper {
	/**
	 * The name of the database
	 */
	private static final String DATABASE_NAME = "profile_manager"; 
	
	/**
	 * Current database version. Don't forget to increment this value each time 
	 * you are doing any changes with schema.  
	 */
	private static final int DATABASE_VERSION = 1;
	
	/** 
	 * Keep track of context so that we can load SQL from string resources 
	 */
    private final Context context;

    
	public ProfileManagerDatabase(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String createDatabaseStmt = 
			new String("CREATE TABLE time_rule (" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"start_time DATETIME," +
				"end_time DATETIME," +  
				"week_days_mask TINYINT" +
				");");		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
