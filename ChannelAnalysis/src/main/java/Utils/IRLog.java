package Utils;

import Main.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Created by ouhiroshi on 2016/11/23.
 */
public class IRLog {
    /**
     * Log.
     *
     * @param msg the msg
     */
    public static void log(String msg) {

        Logger logger = Logger.getLogger(Main.class);
        String newMsg = getCurrentTime() + " " + msg;
        logger.info(newMsg);
    }

    private static String getCurrentTime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        return dateFormat.format(now);
    }
}
