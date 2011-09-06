package com.visma.home.milagerecorder.messages;

import com.visma.home.milagerecorder.service.ServiceNames;

public abstract class AbstractRequest {
	public abstract ServiceNames getServiceName();
}
