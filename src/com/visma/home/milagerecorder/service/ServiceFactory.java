package com.visma.home.milagerecorder.service;

import android.content.Context;

import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.db.MilageRecordRepo;

public class ServiceFactory {
	private Context mCtx;

	public ServiceFactory(Context ctx) {
		mCtx = ctx;
	}

	private AddMilageRecordService mAddMilageService = null;
	private MilageRecordRepo mRepo;

	public Object getService(ServiceNames name) throws MilageRecorderException {
		switch (name) {
		case ADD_MILAGE_RECORD:
			if (mAddMilageService == null) {
				mAddMilageService = new AddMilageRecordService(this.getRepo());
			}
			return mAddMilageService;
		default:
			throw new MilageRecorderException("Cant happen");
		}
	}

	public MilageRecordRepo getRepo() {
		if (mRepo == null) {
			mRepo = new MilageRecordRepo(mCtx);
		}
		return mRepo;
	}

}
