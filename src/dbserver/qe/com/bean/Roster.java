package dbserver.qe.com.bean;

import java.sql.Date;

/**
 * 这是一个代表名单的javabean
 */
public class Roster {
    public static final int ADMIN_TYPE = 0;
    public static final int NORMAL_TYPE = 1;

    private int groupId;
    private int userID;
    private int type;
    private Date rosterTime;

    public Roster(int groupId, int userID, int type) {
        this.groupId = groupId;
        this.userID = userID;
        this.type = type;
        this.rosterTime = rosterTime;
    }

    public Roster() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getRosterTime() {
        return rosterTime;
    }

    @Override
    public String toString() {
        return "Roster{" +
                "groupId=" + groupId +
                ", userID=" + userID +
                ", type=" + type +
                ", rosterTime=" + rosterTime +
                '}';
    }
}
