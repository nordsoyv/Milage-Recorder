package com.visma.home.milagerecorder.activity;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.db.DatabaseHelper;
import com.visma.home.milagerecorder.db.MilageRecord;
import com.visma.home.milagerecorder.messages.AddMilageRecordRequest;
import com.visma.home.milagerecorder.service.ServiceFactorySingelton;

public class RecordInput extends OrmLiteBaseActivity<DatabaseHelper>  {
	private EditText kilometerField;
	private EditText litersFilledField;
	private DatePicker datetimePicker;

	Dao<MilageRecord,Integer> dao = null ;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			dao = this.getHelper().getMilageRecordDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 Log.e("Recordinput", "Database exception", e);
			throw new RuntimeException(e);
		}
		setContentView(R.layout.inputrecord);
		setupControls();

	}

	private void setupControls() {
		kilometerField = (EditText) findViewById(R.id.KilometerInput);
		litersFilledField = (EditText) findViewById(R.id.LitersFilledInput);
		datetimePicker = (DatePicker) findViewById(R.id.DatePicker);
		Button submitButton = (Button) findViewById(R.id.SubmitButton);

		submitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				handleButtonClick();
			}
		});

	}

	private void handleButtonClick() {

		try {
			float kilometers = Float.parseFloat(kilometerField.getText().toString());
			float liters = Float.parseFloat(litersFilledField.getText().toString());
			Date currentDate = getEnteredDate();

			MilageRecord rec = new MilageRecord();
			
			rec.setDistance(kilometers);
			rec.setDato(currentDate);
			rec.setLiters(liters);
			dao.create(rec);
//			AddMilageRecordRequest request = new AddMilageRecordRequest();
//			request.setDate(currentDate);
//			request.setKilometers(kilometers);
//			request.setLiters(liters);
//			ServiceFactorySingelton.getInstance().getServiceFactory(this).dispatchRequest(request);
		} catch (SQLException e) {
			 Log.e(this.getClass().getName(), "Database exception when saving", e);
			throw new RuntimeException(e);
		}

		clearControls();

	}

	private void clearControls() {
		kilometerField.setText("");
		litersFilledField.setText("");

	}

	private Date getEnteredDate() {
		Calendar current = Calendar.getInstance();
		current.set(datetimePicker.getYear(), datetimePicker.getMonth(), datetimePicker.getDayOfMonth());
		return current.getTime();
	}

}
