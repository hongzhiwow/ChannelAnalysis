package Core;

import Decode.DecodePackage;
import Entity.APKEntity;
import Utils.Common;
import Utils.IRLog;
import Utils.MD5;

import java.util.concurrent.BlockingQueue;


/**
 * Created by ouhiroshi on 2016/11/23.
 */
public class FileConsumer implements Runnable {

    /**
     * The Queue.
     */
    BlockingQueue<String> queue;

    /**
     * Instantiates a new File consumer.
     *
     * @param queue the queue
     */
    public FileConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                String urlString = queue.take();
                APKEntity entity = new APKEntity();
                String stringMD5 = MD5.getMD5(urlString);
                if (DecodePackage.decodeFile(Common.APKS_FILE_PATCH + stringMD5)) {
                    entity.setStringMD5(stringMD5);
                    String packageName = DecodePackage.getPackageName(Common.APKS_FILE_PATCH + stringMD5);
                    entity.setPackageName(packageName);
                    String appName = DecodePackage.getApplicationName(Common.APKS_FILE_PATCH + stringMD5);
                    entity.setApplicationName(appName);
                    String packageSize = DecodePackage.getPackageSize(Common.APKS_FILE_PATCH + stringMD5);
                    entity.setPackageSize(packageSize);
                    entity.excute();
                }
            }

        } catch (Exception ex) {
            IRLog.log(" " + ex);
        }
    }

}
