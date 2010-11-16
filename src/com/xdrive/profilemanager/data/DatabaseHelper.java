package com.xdrive.profilemanager.data;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.xdrive.profilemanager.rule.TimeRule;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	// name of the database file for your application -- change to something appropriate for your app
	private static final String DATABASE_NAME = "profileManager.db";
	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 1;
	
	// the DAO object we use to access the SimpleData table
	private Dao<TimeRule, Object> timeRuleDAO = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	
	/**
	 * This is called when the app will be installed on device
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getSimpleName(), "creating database");
			TableUtils.createTable(connectionSource, TimeRule.class);
		} catch(SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when the app will be upgraded
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "upgrading database");
			TableUtils.dropTable(connectionSource, TimeRule.class, true);
			onCreate(db);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the DAO for TimeRule class. It will create it or just give the cached
	 * value.
	 */
	public Dao<TimeRule, Object> getTimeRuleDAO() throws SQLException {
		if (timeRuleDAO == null) {
			timeRuleDAO = BaseDaoImpl.createDao(getConnectionSource(), TimeRule.class);
		}
		return timeRuleDAO;
	}
	
	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		timeRuleDAO = null;
	}
}
