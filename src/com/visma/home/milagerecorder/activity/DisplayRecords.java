package com.visma.home.milagerecorder.activity;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;

import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import com.j256.ormlite.dao.Dao;
import com.visma.home.milagerecorder.MilageRecordArrayAdapter;
import com.visma.home.milagerecorder.db.DatabaseHelper;
import com.visma.home.milagerecorder.db.MilageRecord;

public class DisplayRecords extends OrmLiteBaseListActivity<DatabaseHelper> {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fillData();

	}

	private void fillData() {
		MilageRecordArrayAdapter listAdapter = new MilageRecordArrayAdapter(this, R.layout.recordview);
		try {
			Dao<MilageRecord, Integer> dao = this.getHelper().getMilageRecordDao();
			List<MilageRecord> records = dao.queryForAll();
//			/FetchAllMilageRecordsResponse response = (FetchAllMilageRecordsResponse) ServiceFactorySingelton.getInstance().getServiceFactory(this)
//					.dispatchRequest(new FetchAllMilageRecordsRequest());
			for (MilageRecord milageRecord : records) {
				listAdapter.add(milageRecord);
			}
			setListAdapter(listAdapter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fillData();
	}

}
