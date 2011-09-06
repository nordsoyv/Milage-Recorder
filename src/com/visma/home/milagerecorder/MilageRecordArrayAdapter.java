package com.visma.home.milagerecorder;

import java.text.DecimalFormat;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.visma.home.milagerecorder.activity.DisplayRecords;
import com.visma.home.milagerecorder.activity.R;
import com.visma.home.milagerecorder.db.MilageRecord;

public class MilageRecordArrayAdapter extends ArrayAdapter<MilageRecord> {

	public MilageRecordArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);

	}

	public MilageRecordArrayAdapter(DisplayRecords displayRecords, int recordview, int recordoutput) {
		super(displayRecords, recordview, recordoutput);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View recordView;
		recordView = inflater.inflate(R.layout.recordview, null);
		MilageRecord currRecord = this.getItem(position);

		TextView dateOutputView = (TextView) recordView.findViewById(R.id.DateOutput);
		TextView literOutputView = (TextView) recordView.findViewById(R.id.LiterOutput);
		TextView milageOutputView = (TextView) recordView.findViewById(R.id.MilageOutput);
		TextView distanceOutputView = (TextView) recordView.findViewById(R.id.DistanceOutput);

		String formattedDate;
		if (currRecord.getDato() != null) {
			formattedDate = (String) DateFormat.format("dd/MM/yyyy", currRecord.getDato());
		} else {
			formattedDate = "###";
		}
		DecimalFormat myFormatter = new DecimalFormat("###.##");

		dateOutputView.setText(formattedDate);

		literOutputView.setText(myFormatter.format(currRecord.getLiters()) + "l");
		
		distanceOutputView.setText(myFormatter.format(currRecord.getDistance()) + "km");

		milageOutputView.setText(myFormatter.format(currRecord.getLiters() / currRecord.getDistance() * 10) + " l/10km");

		return recordView;
	}

}
