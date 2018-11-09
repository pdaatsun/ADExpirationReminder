package org.sch.issecurity.iam.tools.ADExpirationReminder.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiChen on 4/3/2017.
 */
public class ExpiringUserDAOImpl implements ExpiringUserDAO{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public ArrayList<ExpiringUser> listExpiringUsersByManager() {
        ArrayList<ExpiringUser> expiringUserList = new ArrayList<ExpiringUser>();

        String SQL = "select U.sAMAccountName as userID, U.givenName as userFirstName, U.sn as userLastName, U.accountExpires as endDate, U.mail as userEmail, U.title as jobTitle, M.givenName as mgrFirstName, M.sn as mgrLastName, M.mail as mgrEmail from dbo.AD_USER U, dbo.AD_USER M where U.accountExpires > getdate() and U.accountExpires < Dateadd (day , 14 , getdate()) and U.sn is not NULL and U.manager is not NULL and U.userAccountControl not in ('514','546','262658','66050','66082','66080','66178','4194818') and U.manager=M.distinguishedName and M.userAccountControl not in ('514','546','262658','66050','66082','66080','66178','4194818') and M.mail is not NULL and U.MSOID is NULL and (M.sAMAccountName!='MaryChen' and M.sAMAccountName!='NSweeters' and M.sAMAccountName!='JBuckingham' and M.sAMAccountName!='HOmarzad') order by U.manager";
        expiringUserList = (ArrayList<ExpiringUser>) jdbcTemplateObject.query(SQL, new ExpiringUserMapper ());

        return expiringUserList;
    }

    public ArrayList<ExpiringUser> listExpiringUsers() {
        ArrayList<ExpiringUser> expiringUserList = new ArrayList<ExpiringUser>();

        String SQL = "select U.sAMAccountName as userID, U.givenName as userFirstName, U.sn as userLastName, U.accountExpires as endDate, U.mail as userEmail, U.title as jobTitle, M.givenName as mgrFirstName, M.sn as mgrLastName, M.mail as mgrEmail from dbo.AD_USER U left join dbo.AD_USER M on U.manager=M.distinguishedName where U.accountExpires > getdate() and U.accountExpires < Dateadd (day , 14 , getdate()) and U.sn is not NULL and U.givenName is not NULL and U.userAccountControl not in ('514','546','262658','66050','66082','66080','66178','4194818') and U.mail is not NULL and U.MSOID is NULL and ((M.sAMAccountName!='MaryChen' and M.sAMAccountName!='NSweeters' and M.sAMAccountName!='JBuckingham' and M.sAMAccountName!='HOmarzad') OR (U.manager is null)) order by U.mail";
        expiringUserList = (ArrayList<ExpiringUser>) jdbcTemplateObject.query(SQL, new ExpiringUserMapper ());

        return expiringUserList;
    }
}
