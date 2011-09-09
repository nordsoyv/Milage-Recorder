package com.visma.home.milagerecorder.service;

import android.content.Context;

import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.db.MilageRecordRepo;
import com.visma.home.milagerecorder.messages.AbstractRequest;
import com.visma.home.milagerecorder.messages.AbstractResponse;

public class ServiceFactory {
	private Context mCtx;

	public ServiceFactory() {
	}

	public void setContext(Context ctx){
		mCtx = ctx;
	}
	
	private AddMilageRecordService addMilageService = null;
	private FetchAllMilageRecordsService fetchAllMilageRecordsService;

	@SuppressWarnings("unchecked")
	public AbstractResponse dispatchRequest(AbstractRequest request) throws MilageRecorderException{
		AbstractService<AbstractRequest, AbstractResponse> service = (AbstractService<AbstractRequest, AbstractResponse>) getService(request.getServiceName());
		return service.perform(request);
	}
	
	private Object getService(ServiceNames name) throws MilageRecorderException {
		switch (name) {
		case ADD_MILAGE_RECORD:
			if (addMilageService == null) {
				addMilageService = new AddMilageRecordService(new MilageRecordRepo(mCtx));
			}
			return addMilageService;
		case FETCH_ALL_MILAGE_RECORDS:
			if (fetchAllMilageRecordsService == null) {
				fetchAllMilageRecordsService = new FetchAllMilageRecordsService(new MilageRecordRepo(mCtx));
			}
			return fetchAllMilageRecordsService;
		default:
			throw new MilageRecorderException("Cant happen");
		}
	}

}
