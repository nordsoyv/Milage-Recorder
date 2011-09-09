package com.visma.home.milagerecorder.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "milagerecords";
	private static final int DATABASE_VERSION = 6;

	// the DAO object we use to access the SimpleData table
	private Dao<MilageRecord, Integer> milageRecordDao = null;

	 public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
	 }
	
//	public DatabaseHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion) {
//		super(context, DATABASE_NAME, null, DATABASE_VERSION);
//	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, MilageRecord.class);

		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, MilageRecord.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	public Dao<MilageRecord, Integer> getMilageRecordDao() throws SQLException {
		if (milageRecordDao == null) {
			milageRecordDao = getDao(MilageRecord.class);
		}
		return milageRecordDao;
	}

	@Override
	public void close() {
		super.close();
		milageRecordDao = null;
	}

}
