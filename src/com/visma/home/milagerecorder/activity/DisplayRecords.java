package com.visma.home.milagerecorder.activity;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;

import com.visma.home.milagerecorder.MilageRecordArrayAdapter;
import com.visma.home.milagerecorder.db.MilageRecord;
import com.visma.home.milagerecorder.db.MilageRecordRepo;

public class DisplayRecords extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.displayrecord);
		
		//ListView listView = (ListView) findViewById(R.id.RecordList);
		
		fillData();
		
	}

	private void fillData() {
		MilageRecordRepo repo = new MilageRecordRepo(this);
		repo.open();
		MilageRecordArrayAdapter listAdapter = new MilageRecordArrayAdapter(this, R.layout.recordview);
		List<MilageRecord> records =   repo.fetchAllMilageRecords();
		for (MilageRecord milageRecord : records) {
			listAdapter.add(milageRecord);
		}
		repo.close();

		setListAdapter(listAdapter);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fillData();
	}

}
