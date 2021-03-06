package Core;

import DBHelper.DBManager;
import Download.DownloadManager;
import Entity.URLEntity;
import ReadSource.ReadSourceFile;
import Utils.Common;
import Utils.IRLog;
import Utils.IdentifyURL;
import Utils.MD5;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ouhiroshi on 2016/11/23.
 */
public class FileProducer implements Runnable {

    /*定义类原子变量*/
    private static AtomicInteger lineNumber = new AtomicInteger(0);
    /**
     * The Queue.
     */
    BlockingQueue<String> queue;

    /**
     * Instantiates a new File producer.
     *
     * @param queue the queue
     */
    public FileProducer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                int line = lineNumber.getAndIncrement();
                IRLog.log("开始处理数据  -->> " + Integer.toString(line) + " ");
                String urlString = ReadSourceFile.getSourceURL(line);
                String downloadURL = handleURL(urlString);
                URLEntity entity = new URLEntity();
                if (downloadURL == null) {
                    continue;
                }
                String stringMD5 = MD5.getMD5(downloadURL);
                entity.setStringURL(urlString);
                entity.setStringMD5(stringMD5);
                if (isExist(stringMD5)) {
                    continue;
                }
                if (!DownloadManager.donwload(downloadURL, Common.APKS_FILE_PATCH + stringMD5)) {
                    continue;
                }
                String channel = IdentifyURL.handleURLString(downloadURL);
                entity.setChannel(channel);
                entity.excute();
                queue.put(downloadURL);
            }

        } catch (Exception ex) {
            IRLog.log(" " + ex);
        }
    }

    private String handleURL(String urlString) {
        if (urlString.length() < 10)
            return null;
        if (!urlString.startsWith("http")) {
            urlString = "http://" + urlString;
        }
        if (urlString.contains("?")) {
            String[] components = urlString.split("\\?");
            urlString = components[0];
        }
        return urlString;
    }
    //判断是否已经存在 若已经存在则不下载
    private boolean isExist(String stringMD5) {
        String sql = "SELECT 1 FROM driver_app WHERE urlmd5 = '" + stringMD5 + "';";
        DBManager db = new DBManager(sql);
        try {
            ResultSet rs = db.pst.executeQuery();
            if (rs.next() == false) {
                return false;
            } else  {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }
}
