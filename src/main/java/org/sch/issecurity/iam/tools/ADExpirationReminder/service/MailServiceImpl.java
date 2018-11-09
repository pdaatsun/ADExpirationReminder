package org.sch.issecurity.iam.tools.ADExpirationReminder.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.mail.internet.MimeMessage;

import org.sch.issecurity.iam.tools.ADExpirationReminder.model.ExpiringUser;
import org.sch.issecurity.iam.tools.ADExpirationReminder.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.apache.log4j.Logger;
import freemarker.template.Configuration;

@SuppressWarnings("deprecation")
@Service("mailService")
public class MailServiceImpl implements MailService{

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	Configuration freemarkerConfiguration;

	final static Logger logger = Logger.getLogger(MailServiceImpl.class);

	@Override
	public void sendEmailToManager(Object object) {

		HashMap<String, ArrayList<UserInfo>> userInfoListHM = (HashMap<String, ArrayList<UserInfo>>)object;
		ArrayList<UserInfo> userInfoList;

		for (String mgrEmail : userInfoListHM.keySet()) {
			userInfoList = userInfoListHM.get(mgrEmail);
			logger.info("Sending message to: " + mgrEmail);
			if ((userInfoList != null) && (!userInfoList.isEmpty())) {
				//mgrEmail = "xichen@stanfordchildrens.org";
				MimeMessagePreparator preparator = getMessagePreparatorForManager(userInfoList, mgrEmail);
				for (UserInfo userInfo : userInfoList) {
					logger.info("Expiring User: " + userInfo);
				}
				try {
					mailSender.send(preparator);
					logger.info("Message has been sent to: " + mgrEmail);
					Thread.sleep(30000);
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
					try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

    @Override
    public void sendEmailToUser(Object object) {

        HashMap<String, ArrayList<UserInfo>> userInfoListHM = (HashMap<String, ArrayList<UserInfo>>)object;
        ArrayList<UserInfo> userInfoList;

        for (String userEmail : userInfoListHM.keySet()) {
            userInfoList = userInfoListHM.get(userEmail);
            logger.info("Sending message to: " + userEmail);
            if ((userInfoList != null) && (!userInfoList.isEmpty())) {
                //userEmail = "xichen@stanfordchildrens.org";
                MimeMessagePreparator preparator = getMessagePreparatorForUser(userInfoList, userEmail);
                for (UserInfo userInfo : userInfoList) {
                    logger.info("Expiring User: " + userInfo);
                }
                try {
                    mailSender.send(preparator);
                    logger.info("Message has been sent to: " + userEmail);
                    Thread.sleep(30000);
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

	private MimeMessagePreparator getMessagePreparatorForManager(final ArrayList<UserInfo> userInfoList, final String mgrEmail){
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
            	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
 
               	helper.setSubject("Reminder - Network access of your direct report(s) is going to expire");
               	helper.setFrom("DoNotReply-AccessControl@stanfordchildrens.org");
               	helper.setTo(mgrEmail);
               	helper.setBcc("DoNotReply-AccessControl@stanfordchildrens.org");
     
               	Map<String, Object> model = new HashMap<String, Object>();
                model.put("userInfoList", userInfoList);
                
            	String text = getFreeMarkerTemplateContentForManager(model);
                //System.out.println("Template content : "+text);

                // use the true flag to indicate you need a multipart message
            	helper.setText(text, true);

            }
        };
        return preparator;
	}

    private MimeMessagePreparator getMessagePreparatorForUser(final ArrayList<UserInfo> userInfoList, final String userEmail){

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject("Reminder - Your Stanford Children's Health network access is going to expire");
                helper.setFrom("DoNotReply-AccessControl@stanfordchildrens.org");
                helper.setTo(userEmail);
                helper.setBcc("DoNotReply-AccessControl@stanfordchildrens.org");

                Map<String, Object> model = new HashMap<String, Object>();
                model.put("userInfoList", userInfoList);

                String text = getFreeMarkerTemplateContentForUser(model);
                //System.out.println("Template content : "+text);

                // use the true flag to indicate you need a multipart message
                helper.setText(text, true);

            }
        };
        return preparator;
    }

	public String getFreeMarkerTemplateContentForManager(Map<String, Object> model){
		StringBuffer content = new StringBuffer();
		try{
         content.append(FreeMarkerTemplateUtils.processTemplateIntoString( 
        		 freemarkerConfiguration.getTemplate("fm_mailTemplate.txt"),model));
         return content.toString();
		}catch(Exception e){
			System.out.println("Exception occured while processing fmtemplate:"+e.getMessage());
		}
	      return "";
	}

    public String getFreeMarkerTemplateContentForUser(Map<String, Object> model){
        StringBuffer content = new StringBuffer();
        try{
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate("fm_mailTemplateUser.txt"),model));
            return content.toString();
        }catch(Exception e){
            System.out.println("Exception occured while processing fmtemplate:"+e.getMessage());
        }
        return "";
    }

}
