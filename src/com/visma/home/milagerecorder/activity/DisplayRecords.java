package com.visma.home.milagerecorder.activity;

import android.app.ListActivity;
import android.os.Bundle;

import com.visma.home.milagerecorder.MilageRecordArrayAdapter;
import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.db.MilageRecord;
import com.visma.home.milagerecorder.messages.FetchAllMilageRecordsRequest;
import com.visma.home.milagerecorder.messages.FetchAllMilageRecordsResponse;
import com.visma.home.milagerecorder.service.ServiceFactorySingelton;

public class DisplayRecords extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		fillData();

	}

	private void fillData() {
		MilageRecordArrayAdapter listAdapter = new MilageRecordArrayAdapter(this, R.layout.recordview);
		try {
			FetchAllMilageRecordsResponse response = (FetchAllMilageRecordsResponse) ServiceFactorySingelton.getInstance().getServiceFactory(this)
					.dispatchRequest(new FetchAllMilageRecordsRequest());
			for (MilageRecord milageRecord : response.getMilageRecords()) {
				listAdapter.add(milageRecord);
			}
			setListAdapter(listAdapter);
		} catch (MilageRecorderException e) {
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
