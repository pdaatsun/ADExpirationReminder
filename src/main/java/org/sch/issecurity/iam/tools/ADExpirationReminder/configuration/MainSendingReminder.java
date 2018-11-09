package org.sch.issecurity.iam.tools.ADExpirationReminder.configuration;

import org.sch.issecurity.iam.tools.ADExpirationReminder.model.UserInfo;
import org.sch.issecurity.iam.tools.ADExpirationReminder.service.ExpiringUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class MainSendingReminder {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		ExpiringUserService expiringUserService = (ExpiringUserService) context.getBean("expiringUserService");
		expiringUserService.sendADExpirationReminder();
		((AbstractApplicationContext) context).close();
	}
	
	public static ArrayList<UserInfo> getDummyUserList(){
		ArrayList<UserInfo> userInfoList = new ArrayList<UserInfo>();

		UserInfo user1 = new UserInfo();
		user1.setUserID("USERID1");
		user1.setUserName("USERNAME1");
		user1.setEndDate("ENDDATE1");
		user1.setJobTitle("JOBTITLE1");
		userInfoList.add(user1);

		UserInfo user2 = new UserInfo();
		user2.setUserID("USERID2");
		user2.setUserName("USERNAME2");
		user2.setEndDate("ENDDATE2");
		user2.setJobTitle("JOBTITLE2");
		userInfoList.add(user2);

		UserInfo user3 = new UserInfo();
		user3.setUserID("USERID3");
		user3.setUserName("USERNAME3");
		user3.setEndDate("ENDDATE3");
		user3.setJobTitle("JOBTITLE3");
		userInfoList.add(user3);

		return userInfoList;
	}

}
