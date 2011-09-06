package com.visma.home.milagerecorder.service;

import com.visma.home.milagerecorder.MilageRecorderException;
import com.visma.home.milagerecorder.messages.AbstractRequest;
import com.visma.home.milagerecorder.messages.AbstractResponse;

public abstract class AbstractService<Request extends AbstractRequest , Response extends AbstractResponse > {
		
	public abstract Response perform(Request r) throws MilageRecorderException;

}
