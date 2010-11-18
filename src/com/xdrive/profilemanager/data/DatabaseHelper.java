package com.xdrive.profilemanager.data;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.xdrive.profilemanager.condition.Condition;
import com.xdrive.profilemanager.rule.Rule;
import com.xdrive.profilemanager.rule.TimeRule;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	// name of the database file for your application -- change to something appropriate for your app
	private static final String DATABASE_NAME = "profileManager.db";
	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 1;
	
	private final String LOG_TAG = getClass().getName(); 
	
	// all DAO objects we using in application
	private Dao<TimeRule, Integer> timeRuleDAO = null;
	private Dao<Condition, Integer> conditionDAO = null;

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
			TableUtils.createTable(connectionSource, Condition.class);
		} catch(SQLException e) {
			Log.e(LOG_TAG, "Can't create database", e);
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
			TableUtils.dropTable(connectionSource, Condition.class, true);
			onCreate(db);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the DAO for TimeRule class. It will create it or just give the cached
	 * value.
	 */
	public Dao<TimeRule, Integer> getTimeRuleDAO() throws SQLException {
		if (timeRuleDAO == null) {
			timeRuleDAO = BaseDaoImpl.createDao(getConnectionSource(), TimeRule.class);
		}
		return timeRuleDAO;
	}
	
	/**
	 * Returns the DAO for Condition class. It will create it or just give the cached
	 * value.
	 */
	public Dao<Condition, Integer> getConditionDAO() throws SQLException {
		if (conditionDAO == null) {
			conditionDAO = BaseDaoImpl.createDao(getConnectionSource(), Condition.class);
		}
		return conditionDAO;
	}
	
	/**
	 * Fetches all rules from DB that have the same value of condition_id 
	 * DB field that the passed Condition object's id property 
	 * @param Condition condition Condition for which rules should be fetched
	 * @return Set<Rule> set of rules for passed Condition
	 */
	public Set<Rule> getConditionRules(Condition condition) {
		Set<Rule> rules = null;
		if (condition != null) {
			try {
				QueryBuilder<TimeRule, Integer> builder = this.getTimeRuleDAO().queryBuilder();
				builder.where().eq(TimeRule.CONDITION_ID_FIELD, condition.getId());
				rules = new HashSet<Rule>(getTimeRuleDAO().query(builder.prepare()));
			} catch (SQLException e) {
				Log.e(LOG_TAG, "Can't select rules. Database error");
			}
		}
		return rules;
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
