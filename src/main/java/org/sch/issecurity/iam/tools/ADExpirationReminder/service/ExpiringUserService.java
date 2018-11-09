package org.sch.issecurity.iam.tools.ADExpirationReminder.service;

import org.sch.issecurity.iam.tools.ADExpirationReminder.model.UserInfo;

import java.util.ArrayList;

public interface ExpiringUserService {

	public void sendADExpirationReminder();
	
}
