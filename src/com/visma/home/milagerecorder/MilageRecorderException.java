package com.visma.home.milagerecorder;

import com.visma.home.milagerecorder.db.RecordStorageException;

public class MilageRecorderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -16720870441087069L;

	public MilageRecorderException(String string) {
		super(string);
	}

	public MilageRecorderException(RecordStorageException e) {
		super(e);
	}

}
