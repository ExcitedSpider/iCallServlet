package dbserver.qe.com.bean;

public class User {
    private int id;
    private String account;
    private String password;
    private String school;
    private String job;

    public User(int id,String account,String password,String school,String job){
        this.id = id;
        this.account = account;
        this.password = password;
        this.school = school;
        this.job = job;
    }

    public User(String account,String password,String school,String job){
        this.account = account;
        this.password = password;
        this.school = school;
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public String getSchool() {
        return school;
    }

    public String getPassword() {
        return password;
    }

    public String getJob() {
        return job;
    }

    public String getAccount() {
        return account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return id+": "+account+" "+password+" "+school+" "+job;
    }
}
