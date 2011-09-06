package com.visma.home.milagerecorder.service;

import android.content.Context;

import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.db.MilageRecordRepo;

public class ServiceFactory {
	private Context mCtx;

	public ServiceFactory(Context ctx) {
		mCtx = ctx;
	}

	private AddMilageRecordService addMilageService = null;
	private MilageRecordRepo repo;
	private FetchAllMilageRecordsService fetchAllMilageRecordsService;

	public Object getService(ServiceNames name) throws MilageRecorderException {
		switch (name) {
		case ADD_MILAGE_RECORD:
			if (addMilageService == null) {
				addMilageService = new AddMilageRecordService(this.getRepo());
			}
			return addMilageService;
		case FETCH_ALL_MILAGE_RECORDS:
			if (fetchAllMilageRecordsService == null) {
				fetchAllMilageRecordsService = new FetchAllMilageRecordsService(getRepo());
			}
			return fetchAllMilageRecordsService;
		default:
			throw new MilageRecorderException("Cant happen");
		}
	}

	public MilageRecordRepo getRepo() {
		if (repo == null) {
			repo = new MilageRecordRepo(mCtx);
		}
		return repo;
	}

}
