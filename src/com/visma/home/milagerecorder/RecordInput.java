package com.visma.home.milagerecorder;

import java.util.Calendar;
import java.util.Date;

import com.visma.home.milagerecorder.db.MilageRecord;
import com.visma.home.milagerecorder.db.MilageRecordRepo;
import com.visma.home.milagerecorder.db.RecordStorageException;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class RecordInput extends Activity {
	private EditText kilometerField;
	private EditText litersFilledField;
	private TextView outputField;
	private DatePicker datetimePicker;
	
	private  MilageRecordRepo repo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputrecord);
		repo =  new MilageRecordRepo(this);
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
		float kilometers = Float
				.parseFloat(kilometerField.getText().toString());
		float liters = Float.parseFloat(litersFilledField.getText().toString());
		Date currentDate = getEnteredDate();
		float milage = (liters / kilometers) * 10;

		String result = (String) DateFormat.format("dd/MM/yyyy", currentDate);
		result += ": " + milage;
		//outputField.setText(result);

		MilageRecord record = new MilageRecord();
		record.setDistance(kilometers);
		record.setLiters(liters);
		record.setDato(currentDate);
		try {
			repo.open();
			repo.insertMilageRecord(record);
			repo.close();
		} catch (RecordStorageException e) {
			// TODO Auto-generated catch block
			outputField.setText("ERROR");
			//e.printStackTrace();
		}
		outputField.setText("SUCCSESS");
		clearControls();
		
	}

	private void clearControls() {
		kilometerField.setText("");
		litersFilledField.setText("");
		
		
	}

	private Date getEnteredDate() {
		Calendar current = Calendar.getInstance();
		current.set(datetimePicker.getYear(), datetimePicker.getMonth(),
				datetimePicker.getDayOfMonth());
		return current.getTime();
	}

}
