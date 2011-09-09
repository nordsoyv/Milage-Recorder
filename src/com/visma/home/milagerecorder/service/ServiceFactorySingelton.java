package com.visma.home.milagerecorder.service;

import android.content.Context;

import com.visma.home.milagerecorder.MilageRecorderException;

public class ServiceFactorySingelton {

	private static ServiceFactorySingelton instance = null;

	private ServiceFactory serviceFactory;

	private ServiceFactorySingelton() {

	}

	public static ServiceFactorySingelton getInstance() {
		if (instance == null) {
			instance = new ServiceFactorySingelton();
		}
		return instance;
	}

	public ServiceFactory getServiceFactory(Context ctx) throws MilageRecorderException {
		if (serviceFactory == null) {
			serviceFactory = new ServiceFactory();
		}
		serviceFactory.setContext(ctx);
		return serviceFactory;
	}

//	public void CreateServiceFactory(Context ctx) {
//		serviceFactory = new ServiceFactory(ctx);
//	}

}
