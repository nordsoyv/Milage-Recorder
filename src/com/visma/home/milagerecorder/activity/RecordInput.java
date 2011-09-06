package com.visma.home.milagerecorder.activity;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.db.MilageRecordRepo;
import com.visma.home.milagerecorder.messages.AddMilageRecordRequest;
import com.visma.home.milagerecorder.service.AddMilageRecordService;
import com.visma.home.milagerecorder.service.ServiceFactorySingelton;
import com.visma.home.milagerecorder.service.ServiceNames;

public class RecordInput extends Activity {
	private EditText kilometerField;
	private EditText litersFilledField;
	private TextView outputField;
	private DatePicker datetimePicker;

	private MilageRecordRepo repo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputrecord);
		repo = new MilageRecordRepo(this);
		setupControls();

	}

	private void setupControls() {
		kilometerField = (EditText) findViewById(R.id.KilometerInput);
		litersFilledField = (EditText) findViewById(R.id.LitersFilledInput);
		outputField = (TextView) findViewById(R.id.outputField);
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

		AddMilageRecordService service;
		try {
			service = (AddMilageRecordService) ServiceFactorySingelton.getInstance().getServiceFactory().getService(ServiceNames.ADD_MILAGE_RECORD);

			float kilometers = Float.parseFloat(kilometerField.getText().toString());
			float liters = Float.parseFloat(litersFilledField.getText().toString());
			Date currentDate = getEnteredDate();

			AddMilageRecordRequest request = new AddMilageRecordRequest();
			request.setDate(currentDate);
			request.setKilometers(kilometers);
			request.setLiters(liters);
			service.perform(request);
		} catch (MilageRecorderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
