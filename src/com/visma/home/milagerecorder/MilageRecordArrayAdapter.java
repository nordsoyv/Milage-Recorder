package com.visma.home.milagerecorder;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.visma.home.milagerecorder.db.MilageRecord;

public class MilageRecordArrayAdapter extends ArrayAdapter<MilageRecord> {

	public MilageRecordArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);

	}

	public MilageRecordArrayAdapter(DisplayRecords displayRecords,
			int recordview, int recordoutput) {
		// TODO Auto-generated constructor stub
		super(displayRecords, recordview, recordoutput);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView;
		MilageRecord currRecord = this.getItem(position);
		if (convertView == null) {
			textView = new TextView(this.getContext());
		} else {
			textView = (TextView) convertView;
		}
		String result;
		if (currRecord.getDato() != null) {
			result = (String) DateFormat.format("dd/MM/yyyy", currRecord
					.getDato());
		} else {
			result = "###";
		}

		result += " , " + Float.toString(currRecord.getDistance());
		textView.setText(result);
		return textView;
	}

}
