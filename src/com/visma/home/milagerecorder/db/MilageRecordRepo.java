package com.visma.home.milagerecorder.db;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MilageRecordRepo {

	public static final String KEY_LITERS = "liters";
	public static final String KEY_RECORD_DATE = "record_date";
	public static final String KEY_ROWID = "id";
	public static final String KEY_DISTANCE = "distance";

	/**
	 * Database creation sql statement
	 */
	private static final String DATABASE_CREATE = "create table records (id integer primary key autoincrement, "
			+ "record_date text not null, "
			+ "distance real not null, "
			+ "liters real not null );";

	private static final String DATABASE_NAME = "milagerecords";
	private static final String DATABASE_TABLE = "records";
	private static final int DATABASE_VERSION = 4;
	private static final String TAG = "MilageRecordRepo";

	private final Context mCtx;

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS records");
			onCreate(db);
		}
	}

	public MilageRecordRepo(Context ctx) {
		this.mCtx = ctx;
	}

	public MilageRecordRepo open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public long insertMilageRecord(MilageRecord r)
			throws RecordStorageException {
		if (r.getId() != -1)
			throw new RecordStorageException(
					"Cant insert record with existing id");
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_RECORD_DATE, java.text.DateFormat.getInstance().format(r.getDato()));
		initialValues.put(KEY_DISTANCE, r.getDistance());
		initialValues.put(KEY_LITERS, r.getLiters());
		return mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean deleteMilageRecord(MilageRecord r)
			throws RecordStorageException {
		if (r.getId() == -1)
			throw new RecordStorageException(
					"Cant delete record without existing id");
		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + r.getId(), null) > 0;
	}

	/**
	 * Return a Cursor positioned at the note that matches the given rowId
	 * 
	 * @param rowId
	 *            id of note to retrieve
	 * @return Cursor positioned to matching note, if found
	 * @throws SQLException
	 *             if note could not be found/retrieved
	 */
	public MilageRecord fetchMilageRecord(MilageRecord r) throws SQLException {

		Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_DISTANCE, KEY_LITERS, KEY_RECORD_DATE },
				KEY_ROWID + "=" + r.getId(), null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}

		r = createMilageRecordFromCursor(mCursor);

		mCursor.deactivate();

		return r;
	}

	public List<MilageRecord> fetchAllMilageRecords() {
		List<MilageRecord> res = new ArrayList<MilageRecord>();

		Cursor mCursor = mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_DISTANCE, KEY_LITERS, KEY_RECORD_DATE }, null, null, null,
				null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}

		while (!mCursor.isAfterLast()) {
			MilageRecord r = createMilageRecordFromCursor(mCursor);
			res.add(r);

			mCursor.moveToNext();
		}
		return res;
	}

	public boolean updateMilageRecord(MilageRecord r) {
		ContentValues args = new ContentValues();
		args.put(KEY_ROWID, r.getId());
		args.put(KEY_RECORD_DATE,   java.text.DateFormat.getInstance().format(r.getDato()));
		args.put(KEY_LITERS, r.getLiters());
		args.put(KEY_DISTANCE, r.getDistance());
		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + r.getId(), null) > 0;
	}

	private MilageRecord createMilageRecordFromCursor(Cursor c) {
		MilageRecord r = new MilageRecord();

		try {
			String date = c.getString(c.getColumnIndexOrThrow(KEY_RECORD_DATE));
			Date d = java.text.DateFormat.getInstance().parse(date);
			r.setDato(d);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.setDistance(c.getFloat(c.getColumnIndex(KEY_DISTANCE)));
		r.setLiters(c.getFloat(c.getColumnIndex(KEY_LITERS)));
		r.setId(c.getInt(c.getColumnIndex(KEY_ROWID)));

		return r;
	}

}
