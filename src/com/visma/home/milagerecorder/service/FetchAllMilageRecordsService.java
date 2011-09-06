package com.visma.home.milagerecorder.service;

import java.util.List;

import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.db.MilageRecord;
import com.visma.home.milagerecorder.db.MilageRecordRepo;
import com.visma.home.milagerecorder.messages.FetchAllMilageRecordsRequest;
import com.visma.home.milagerecorder.messages.FetchAllMilageRecordsResponse;

public class FetchAllMilageRecordsService extends AbstractService<FetchAllMilageRecordsRequest, FetchAllMilageRecordsResponse> {

	private MilageRecordRepo repo;

	public FetchAllMilageRecordsService(MilageRecordRepo repo) {
		this.repo = repo;
	}

	@Override
	public FetchAllMilageRecordsResponse perform(FetchAllMilageRecordsRequest r) throws MilageRecorderException {
		FetchAllMilageRecordsResponse response = new FetchAllMilageRecordsResponse();
		repo.open();
		List<MilageRecord> records = repo.fetchAllMilageRecords();
		repo.close();
		response.setMilageRecords(records);

		return response;
	}

}
