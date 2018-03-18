package dbserver.qe.com.bean;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 这只是一个javabean
 */
public class Notification {
    public static final int NORMAL_TYPE=0;

    private int id;
    private Date createTime;
    private Timestamp lastCallTime;
    private Date failTime;
    private int creater;
    private int type;
    private int groupId;
    private String content;

    public Notification() {
    }

    public Notification(int creater, int type, int groupId, String content) {
        this.creater = creater;
        this.type = type;
        this.groupId = groupId;
        this.content = content;
    }

    public Notification(Date failTime, int creater, int type, int groupId, String content) {
        this.failTime = failTime;
        this.creater = creater;
        this.type = type;
        this.groupId = groupId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", lastCallTime=" + lastCallTime +
                ", failTime=" + failTime +
                ", creater=" + creater +
                ", type=" + type +
                ", groupId=" + groupId +
                ", content='" + content + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastCallTime() {
        return lastCallTime;
    }

    public void setLastCallTime(Timestamp lastCallTime) {
        this.lastCallTime = lastCallTime;
    }

    public Date getFailTime() {
        return failTime;
    }

    public void setFailTime(Date failTime) {
        this.failTime = failTime;
    }

    public int getCreater() {
        return creater;
    }

    public void setCreater(int creater) {
        this.creater = creater;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws Exception{
        if(content.length()<300)
            this.content = content;
        else{
            throw new Exception("通知长度必须小于300");
        }
    }
}
