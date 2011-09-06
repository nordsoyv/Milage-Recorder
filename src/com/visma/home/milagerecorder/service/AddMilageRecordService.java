package com.visma.home.milagerecorder.service;

import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.db.MilageRecord;
import com.visma.home.milagerecorder.db.MilageRecordRepo;
import com.visma.home.milagerecorder.db.RecordStorageException;
import com.visma.home.milagerecorder.messages.AddMilageRecordRequest;
import com.visma.home.milagerecorder.messages.AddMilageRecordResponse;

public class AddMilageRecordService extends AbstractService<AddMilageRecordRequest	, AddMilageRecordResponse> {

	private MilageRecordRepo repo;

	public AddMilageRecordService(MilageRecordRepo r){
		repo = r;
	}
	
	@Override
	public AddMilageRecordResponse perform(AddMilageRecordRequest r) throws MilageRecorderException {

		MilageRecord record = new MilageRecord();
		record.setDistance(r.getKilometers());
		record.setLiters(r.getLiters());
		record.setDato(r.getDate());
		try {
			repo.open();
			repo.insertMilageRecord(record);
			repo.close();
		} catch (RecordStorageException e) {
			throw new MilageRecorderException(e);
		}
		return new AddMilageRecordResponse();
	}

}
