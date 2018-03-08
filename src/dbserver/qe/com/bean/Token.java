package dbserver.qe.com.bean;

public class Token {
    private int id;
    private String account;
    private String imei;
    private long timestamp;

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getImei() {
        return imei;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Token(String account, String imei, long timestamp){
        this.account = account;
        this.imei = imei;
        this.timestamp = timestamp;
    }

    public Token(){}

    @Override
    public String toString() {
        return id+" "+account+" "+imei+" "+timestamp;
    }
}
