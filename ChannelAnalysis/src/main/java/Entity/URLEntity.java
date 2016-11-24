package Entity;

import DBHelper.DBManager;
import Utils.IRLog;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ouhiroshi on 2016/11/23.
 */
public class URLEntity {
    private String stringMD5;
    private String stringURL;
    private String channel;

    /**
     * Sets string md 5.
     *
     * @param stringMD5 the string md 5
     */
    public void setStringMD5(String stringMD5) {
        this.stringMD5 = stringMD5;
    }

    /**
     * Gets string md 5.
     *
     * @return the string md 5
     */
    public String getStringMD5() {

        return stringMD5;
    }

    /**
     * Gets channel.
     *
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Gets string url.
     *
     * @return the string url
     */
    public String getStringURL() {

        return stringURL;
    }

    /**
     * Sets channel.
     *
     * @param channel the channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * Sets string url.
     *
     * @param stringURL the string url
     */
    public void setStringURL(String stringURL) {

        this.stringURL = stringURL;
    }

    /**
     * Excute boolean.
     *
     * @return the boolean
     */
    public boolean excute() {
        IRLog.log(getStringMD5() + " 开始更新URL信息");
        String sql = "SELECT 1 FROM driver_app WHERE urlmd5 = '" + getStringMD5() + "';";
        DBManager dbManager = new DBManager(sql);
        try {
            ResultSet resultSet = dbManager.pst.executeQuery();
            String dataSQL = null;
            if (resultSet.next() == false) {
                dataSQL = "INSERT INTO driver_app (urlmd5,url,channel) VALUES ('" + getStringMD5() + "','" + getStringURL() + "','" + getChannel() + "');";
            } else {
                dataSQL = "UPDATE driver_app SET url = '" + getStringURL() + "' , channel = '" + getChannel() + "' WHERE urlmd5 = '" + getStringMD5() + "';";
            }
            DBManager db = new DBManager(dataSQL);
            db.pst.executeUpdate();
            db.close();
            resultSet.close();
        } catch (SQLException e) {
            IRLog.log(getStringMD5() + " 更新URL信息失败 " + e);
            dbManager.close();
            return false;
        }
        dbManager.close();
        IRLog.log(getStringMD5() + " 更新URL信息成功 ");
        return true;
    }
}
