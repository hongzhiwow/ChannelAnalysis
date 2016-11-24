package Entity;

import DBHelper.DBManager;
import Utils.IRLog;

import java.sql.SQLException;

/**
 * Created by ouhiroshi on 2016/11/23.
 */
public class APKEntity {

    private String stringMD5;
    private String packageName;
    private String packageSize;
    private String applicationName;

    /**
     * Sets application name.
     *
     * @param applicationName the application name
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * Sets package size.
     *
     * @param packageSize the package size
     */
    public void setPackageSize(String packageSize) {

        this.packageSize = packageSize;
    }

    /**
     * Sets package name.
     *
     * @param packageName the package name
     */
    public void setPackageName(String packageName) {

        this.packageName = packageName;
    }

    /**
     * Sets string md 5.
     *
     * @param stringMD5 the string md 5
     */
    public void setStringMD5(String stringMD5) {

        this.stringMD5 = stringMD5;
    }

    /**
     * Gets application name.
     *
     * @return the application name
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Gets package size.
     *
     * @return the package size
     */
    public String getPackageSize() {

        return packageSize;
    }

    /**
     * Gets package name.
     *
     * @return the package name
     */
    public String getPackageName() {

        return packageName;
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
     * Excute boolean.
     *
     * @return the boolean
     */
    public boolean excute() {
        IRLog.log(getStringMD5() + "开始更新应用信息");
        String sql = "UPDATE driver_app SET packagename = '" + getPackageName() + "' , appname = '" + getApplicationName() + "' , packagesize = '" + getPackageSize() + "' WHERE urlmd5 = '" + getStringMD5() + "';";
        DBManager dbManager = new DBManager(sql);
        try {
            dbManager.pst.executeUpdate();
        } catch (SQLException e) {
            dbManager.close();
            IRLog.log(getStringMD5() + " 更新应用信息失败 " + e);
            return false;
        }
        dbManager.close();
        IRLog.log(getStringMD5() + "更新应用信息成功");
        return true;
    }


}
