package org.sch.issecurity.iam.tools.ADExpirationReminder.model;

/**
 * Created by XiChen on 4/3/2017.
 */
public class ExpiringUser {
    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String userID;

    private String endDate;

    private String jobTitle;

    private String mgrFirstName;

    private String mgrLastName;

    private String mgrEmail;

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setuserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getMgrFirstName() {
        return mgrFirstName;
    }

    public void setMgrFirstName(String mgrFirstName) {
        this.mgrFirstName = mgrFirstName;
    }

    public String getMgrLastName() {
        return mgrLastName;
    }

    public void setMgrLastName(String mgrLastName) {
        this.mgrLastName = mgrLastName;
    }

    public String getMgrEmail() {
        return mgrEmail;
    }

    public void setMgrEmail(String mgrEmail) {
        this.mgrEmail = mgrEmail;
    }

    @Override
    public String toString() {
        return "ExpiringUser [User ID=" + userID + ", User Name=" + userFirstName + " " + userLastName + ", End Date=" + endDate + ", Job Title=" + jobTitle
                + ", Manager Name=" + mgrFirstName + " " + mgrLastName + ", Manager Email=" + mgrEmail + "]";
    }

}
