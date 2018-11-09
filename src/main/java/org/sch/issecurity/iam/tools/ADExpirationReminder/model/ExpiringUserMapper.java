package org.sch.issecurity.iam.tools.ADExpirationReminder.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by XiChen on 4/3/2017.
 */
public class ExpiringUserMapper implements RowMapper<ExpiringUser> {
    public ExpiringUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExpiringUser expiringUser = new ExpiringUser();
        expiringUser.setUserFirstName(rs.getString("userFirstName"));
        expiringUser.setUserLastName(rs.getString("userLastName"));
        expiringUser.setuserEmail(rs.getString("userEmail"));
        expiringUser.setUserID(rs.getString("userID"));
        expiringUser.setEndDate(rs.getString("endDate"));
        expiringUser.setJobTitle(rs.getString("jobTitle"));
        expiringUser.setMgrFirstName(rs.getString("mgrFirstName"));
        expiringUser.setMgrLastName(rs.getString("mgrLastName"));
        expiringUser.setMgrEmail(rs.getString("mgrEmail"));

        return expiringUser;
    }
}