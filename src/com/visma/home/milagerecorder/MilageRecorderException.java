package com.visma.home.milagerecorder;

import com.visma.home.milagerecorder.db.RecordStorageException;

public class MilageRecorderException extends Exception {

	public MilageRecorderException(String string) {
		super(string);
	}

	public MilageRecorderException(RecordStorageException e) {
		super(e);
	}

}
