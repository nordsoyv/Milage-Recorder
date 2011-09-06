package com.visma.home.milagerecorder.messages;

import java.util.Collection;

import com.visma.home.milagerecorder.db.MilageRecord;
import com.visma.home.milagerecorder.service.ServiceNames;

public class FetchAllMilageRecordsResponse extends AbstractResponse {

	@Override
	public ServiceNames getServiceName() {
		
		return ServiceNames.FETCH_ALL_MILAGE_RECORDS;
	}

	public void setMilageRecords(Collection<MilageRecord> milageRecords) {
		this.milageRecords = milageRecords;
	}

	public Collection<MilageRecord> getMilageRecords() {
		return milageRecords;
	}

	private Collection<MilageRecord> milageRecords;
	
}
