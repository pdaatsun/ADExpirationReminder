package org.sch.issecurity.iam.tools.ADExpirationReminder.service;

public interface MailService {

	public void sendEmailToManager(final Object object);

    public void sendEmailToUser(final Object object);
}
