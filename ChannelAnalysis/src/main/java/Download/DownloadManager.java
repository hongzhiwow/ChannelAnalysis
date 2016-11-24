package Download;

/**
 * Created by ouhiroshi on 2016/11/24.
 */
public class DownloadManager {

    public static boolean donwload(String urlString, String filePatch) {
        return DownloadCore.downLoad(urlString,filePatch + ".apk");
    }

}
