package com.visma.home.milagerecorder;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.InflateException;
import android.view.LayoutInflater;
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
		LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService
	      (Context.LAYOUT_INFLATER_SERVICE);
		View recordView;
		try{
		 recordView =  inflater.inflate(R.layout.recordview, null);
		
		}catch(InflateException ex){
			ex.printStackTrace();
			return null;
		}
		MilageRecord currRecord = this.getItem(position);
		/*if (convertView == null) {
			textView = new TextView(this.getContext());
		} else {
			textView = (TextView) convertView;
		} */
		TextView recordOutputView =  (TextView) recordView.findViewById(R.id.RecordOutput);
		
		String result;
		if (currRecord.getDato() != null) {
			result = (String) DateFormat.format("dd/MM/yyyy", currRecord
					.getDato());
		} else {
			result = "###";
		}

		result += " , " + Float.toString(currRecord.getDistance());
		recordOutputView.setText(result);
		return recordView;
	}

}
