package org.sch.issecurity.iam.tools.ADExpirationReminder.service;

import org.sch.issecurity.iam.tools.ADExpirationReminder.model.ExpiringUser;
import org.sch.issecurity.iam.tools.ADExpirationReminder.model.ExpiringUserDAOImpl;
import org.sch.issecurity.iam.tools.ADExpirationReminder.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("expiringUserService")
public class ExpiringUserServiceImpl implements ExpiringUserService {

	@Autowired
	MailService mailService;

	@Autowired
	ExpiringUserDAOImpl expiringUserDAOImpl;

	@Override
	public void sendADExpirationReminder() {
		ArrayList<UserInfo> userInfoList = new ArrayList<UserInfo> ();
		ArrayList<ExpiringUser> expiringUserList = expiringUserDAOImpl.listExpiringUsersByManager();

		if ((expiringUserList != null) && (!expiringUserList.isEmpty())) {
			HashMap<String, ArrayList<UserInfo>> userInfoListHM = convertExpiringUserToUserInfoList(expiringUserList, true);
			mailService.sendEmailToManager(userInfoListHM);
		}

        userInfoList = new ArrayList<UserInfo> ();
        expiringUserList = expiringUserDAOImpl.listExpiringUsers();

        if ((expiringUserList != null) && (!expiringUserList.isEmpty())) {
            HashMap<String, ArrayList<UserInfo>> userInfoListHM = convertExpiringUserToUserInfoList(expiringUserList, false);
            mailService.sendEmailToUser(userInfoListHM);
        }
	}

	private HashMap<String, ArrayList<UserInfo>> convertExpiringUserToUserInfoList (ArrayList<ExpiringUser> expiringUserList, boolean forManager) {
		HashMap<String, ArrayList<UserInfo>> userInfoListHM = new HashMap<String, ArrayList<UserInfo>> ();
		ArrayList<UserInfo> userInfoList;
		UserInfo userInfo;
		String mgrEmail;
        String userEmail;

		for (ExpiringUser expiringUser : expiringUserList) {
			userInfo = new UserInfo();
			userInfo.setUserID(expiringUser.getUserID());
			userInfo.setUserName(expiringUser.getUserFirstName() + " " + expiringUser.getUserLastName());
			userInfo.setEndDate(expiringUser.getEndDate());
			userInfo.setJobTitle(expiringUser.getJobTitle());

			if (forManager) {
                mgrEmail = expiringUser.getMgrEmail();
                if (userInfoListHM.containsKey(mgrEmail)) {
                    userInfoList = userInfoListHM.get(mgrEmail);
                } else {
                    userInfoList = new ArrayList<UserInfo>();
                }
                userInfoList.add(userInfo);
                userInfoListHM.put(mgrEmail, userInfoList);
            } else {
                userEmail = expiringUser.getUserEmail();
                if (userInfoListHM.containsKey(userEmail)) {
                    userInfoList = userInfoListHM.get(userEmail);
                } else {
                    userInfoList = new ArrayList<UserInfo>();
                }
                userInfoList.add(userInfo);
                userInfoListHM.put(userEmail, userInfoList);
            }
		}

		return userInfoListHM;
	}
	
}
