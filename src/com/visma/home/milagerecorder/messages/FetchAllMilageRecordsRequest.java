package com.visma.home.milagerecorder.messages;

import com.visma.home.milagerecorder.service.ServiceNames;

public class FetchAllMilageRecordsRequest extends AbstractRequest {

	@Override
	public ServiceNames getServiceName() {
		return ServiceNames.FETCH_ALL_MILAGE_RECORDS;
	}

}
