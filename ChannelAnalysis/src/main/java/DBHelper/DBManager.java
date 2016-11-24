package DBHelper;

import Utils.Common;

import java.sql.*;

/**
 * Created by ouhiroshi on 2016/11/16.
 */
public class DBManager {

//    jdbc:mysql://localhost:端口
    /**
     * The constant NAME.
     */
    private static final String NAME = "com.mysql.jdbc.Driver";
    /**
     * The constant USER.
     */
    private static final String USER = "root";
    /**
     * The constant PWD.
     */
    private static final String PWD = "root";

    /**
     * The Conn.
     */
    public Connection conn = null;
    /**
     * The Pst.
     */
    public PreparedStatement pst = null;

    /**
     * Instantiates a new Db manager.
     *
     * @param sql the sql
     */
    public DBManager(String sql) {

        try {
            Class.forName(NAME);//指定连接类型
            conn = DriverManager.getConnection(Common.DATA_BASE_URL, USER, PWD);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close.
     */
    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
