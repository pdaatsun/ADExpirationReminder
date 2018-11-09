package org.sch.issecurity.iam.tools.ADExpirationReminder.model;

import java.util.List;
import javax.sql.DataSource;

/**
 * Created by XiChen on 4/3/2017.
 */
public interface ExpiringUserDAO {

    public List<ExpiringUser> listExpiringUsersByManager();

    public List<ExpiringUser> listExpiringUsers();

}
