package dbserver.qe.com.bean;

import java.sql.Date;

/**
 * 这是一个代表通知群的javabean
 */
public class Group {
    public static final int CLASS_TYPE = 0;
    public static final int SOCIETY_TYPE = 1;
    public static final int FREE_TYPE = 2;

    private int id;
    private String name;
    private int host;
    private String about;
    private int type;
    private Date regist_date;



    public Group(String name, int host, String about, int type) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.about = about;
        this.type = type;
    }

    public Group() { };

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public String getAbout() {
        return about;
    }

    public Date getRegistDate() {
        return regist_date;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id:\t"+getId()+"\n")
                .append("name:\t"+getName()+"\n")
                .append("host:\t"+getHost()+"\n")
                .append("type:\t"+getType()+"\n")
                .append("rDate:\t"+getRegistDate()+"\n")
                .toString();
    }
}
