package com.visma.home.milagerecorder.messages;

import com.visma.home.milagerecorder.service.ServiceNames;

public class AddMilageRecordResponse extends AbstractResponse {

	@Override
	public ServiceNames getServiceName() {
		return ServiceNames.ADD_MILAGE_RECORD;
	}

}
