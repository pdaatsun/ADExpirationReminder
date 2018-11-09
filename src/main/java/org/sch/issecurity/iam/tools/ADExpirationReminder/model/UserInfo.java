package org.sch.issecurity.iam.tools.ADExpirationReminder.model;

/**
 * Created by XiChen on 4/2/2017.
 */
public class UserInfo {

    private String userName;

    private String userID;

    private String endDate;

    private String jobTitle;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "UserInfo [User ID=" + userID + ", User Name=" + userName + ", End Date=" + endDate + ", Job Title=" + jobTitle + "]";
    }

}
