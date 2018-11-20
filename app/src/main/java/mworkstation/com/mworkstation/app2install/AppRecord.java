package mworkstation.com.mworkstation.app2install;

import java.io.Serializable;
import java.util.Date;

public class AppRecord implements Serializable {
    String appId;
    Date tigerTime;


    public AppRecord() {
    }

    public AppRecord(String appId, Date tigerTime) {
        this.appId = appId;
        this.tigerTime = tigerTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Date getTigerTime() {
        return tigerTime;
    }

    public void setTigerTime(Date tigerTime) {
        this.tigerTime = tigerTime;
    }
}
