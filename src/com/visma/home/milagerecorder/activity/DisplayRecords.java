package com.visma.home.milagerecorder.activity;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;

import com.visma.home.milagerecorder.MilageRecordArrayAdapter;
import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.db.MilageRecord;
import com.visma.home.milagerecorder.messages.FetchAllMilageRecordsRequest;
import com.visma.home.milagerecorder.messages.FetchAllMilageRecordsResponse;
import com.visma.home.milagerecorder.service.FetchAllMilageRecordsService;
import com.visma.home.milagerecorder.service.ServiceFactorySingelton;
import com.visma.home.milagerecorder.service.ServiceNames;

public class DisplayRecords extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.displayrecord);

		// ListView listView = (ListView) findViewById(R.id.RecordList);

		fillData();

	}

	private void fillData() {
		MilageRecordArrayAdapter listAdapter = new MilageRecordArrayAdapter(this, R.layout.recordview);
		try {
			FetchAllMilageRecordsService service = (FetchAllMilageRecordsService) ServiceFactorySingelton.getInstance().getServiceFactory()
			.getService(ServiceNames.FETCH_ALL_MILAGE_RECORDS);
			FetchAllMilageRecordsRequest request = new FetchAllMilageRecordsRequest();
			FetchAllMilageRecordsResponse response = service.perform(request);
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
